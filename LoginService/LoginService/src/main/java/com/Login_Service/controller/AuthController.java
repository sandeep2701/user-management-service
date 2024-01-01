package com.Login_Service.controller;

import com.Login_Service.entities.User;
import com.Login_Service.exception.PasswordIncorrectException;
import com.Login_Service.helper.JwtHelper;
import com.Login_Service.model.*;
import com.Login_Service.repository.TokenRepository;
import com.Login_Service.repository.UserRepository;
import com.Login_Service.services.CustomUserDetailService;
import com.Login_Service.services.EmailSenderService;
import com.Login_Service.services.KafkaService;
import com.Login_Service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

   /* @Autowired
    private KafkaService kafkaService;*/
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private CustomUserDetailService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);



    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getUsername(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

    @GetMapping("/forgotPassword/{email}")
    public ResponseEntity<?> sendMail(@PathVariable String email){
        User user = (User)userDetailsService.loadUserByUsername(email);
        //kafkaService.sendEmail(user.getEmail());
        emailSenderService.sendSimpleEmail(user);
        return ResponseEntity.ok("link sent to the given email !!");
    }
    @PostMapping("/resetPassword/{token}")
    public ResponseEntity<?> resetPassword(@PathVariable String token,
                                           @RequestBody ForgotPasswordRequest request){
        PasswordResetToken resetToken = tokenRepository.findByToken(token);
        if(resetToken!=null && emailSenderService.hasExpired(resetToken.getExpDateTime())){
            if(!request.getNewPassword().equals(request.getConfirmationPassword())){
                throw new PasswordIncorrectException("password does not match!!");
            }
            String email = resetToken.getUser().getEmail();
            User user= userService.getUserByEmail(email);
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userService.updateUser(user);
        }
        return ResponseEntity.ok("password changed");
    }

}
