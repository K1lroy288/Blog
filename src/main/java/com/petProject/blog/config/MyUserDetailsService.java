package com.petProject.blog.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.petProject.blog.model.User;
import com.petProject.blog.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService  {
    
    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByName(username);

        return user.map(MyUserDetails::new)
            .orElseThrow(() -> new UsernameNotFoundException(username+"There is not such user"));
    }
}