package com.petProject.blog.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.petProject.blog.api.ArticleResponse;
import com.petProject.blog.api.CommentTemplate;
import com.petProject.blog.api.CreateArticleRequest;
import com.petProject.blog.repository.ArticleRepository;
import com.petProject.blog.model.Article;
import com.petProject.blog.model.Comment;
import com.petProject.blog.model.User;
import com.petProject.blog.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    CommentRepository commentRepository;

    @Override
    public void createArticle(CreateArticleRequest createArticleRequest) {
    
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication required");
        }
        
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof User)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid user");
        }

        User author = (User) principal;

        Article article = new Article()
            .setAuthor(author)
            .setContent(createArticleRequest.getContent())
            .setTitle(createArticleRequest.getTitle());
            
        articleRepository.save(article);
    }

    @Override
    public List<ArticleResponse> getArticles() {
        return articleRepository.findAllWithComments()
            .stream()
            .map(ArticleResponse::buildArticleResponse)
            .collect(Collectors.toList())
            .reversed();
    }

    @Override
    public ArticleResponse getArticle(Integer articleId) {
        Optional<Article> articleOpt = articleRepository.findByIdWithComments(articleId);
        if (articleOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Article with such Id not found");
        }

        Article article = articleOpt.get();
        return ArticleResponse.buildArticleResponse(article);
    }

    @Override
    public List<ArticleResponse> search(String search) {
        return articleRepository.findByTitleOrContentContaining(search)
            .stream()
            .map(ArticleResponse::buildArticleResponse)
            .collect(Collectors.toList());
    }

    @Override
    public void createComment(CommentTemplate commentRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication required");
        }
        
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof User)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid user");
        }

        User author = (User) principal;
        Article article = articleRepository.findById(commentRequest.getArticleId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found"));

        Comment comment = new Comment()
            .setArticle(article)
            .setAuthor(author)
            .setContent(commentRequest.getContent());
        
        commentRepository.save(comment);
    }

    @Override
    public ArticleResponse updateComment(CommentTemplate commentRequest) {
        Comment comment = commentRepository.findById(commentRequest.getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment with such id not found"));

        comment.setContent(commentRequest.getContent());
        commentRepository.save(comment);
        return getArticle(commentRequest.getArticleId());
    }

    @Override
    public ArticleResponse deleteComment(CommentTemplate commentRequest) {
        Comment comment = commentRepository.findById(commentRequest.getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment with such id not found"));
        
        commentRepository.delete(comment);
        
        return getArticle(commentRequest.getArticleId());
    }

}
