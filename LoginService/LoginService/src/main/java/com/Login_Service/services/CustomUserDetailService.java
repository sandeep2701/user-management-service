package com.Login_Service.services;

import com.Login_Service.entities.User;
import com.Login_Service.exception.PasswordIncorrectException;
import com.Login_Service.exception.ResourceNotFoundException;
import com.Login_Service.entities.User;
import com.Login_Service.exception.ResourceNotFoundException;
import com.Login_Service.helper.JwtHelper;
import com.Login_Service.model.ChangePasswordRequest;
//import com.loginApp.loginApp.repository.UserRepository;
import com.Login_Service.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    /*@Autowired
    private JavaMailSender javaMailSender;*/

    private Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class);
    @Autowired
    private JwtHelper helper;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        //System.out.println(user);
        return user;
    }

}


