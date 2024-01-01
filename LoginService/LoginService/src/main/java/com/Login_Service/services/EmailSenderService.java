package com.Login_Service.services;

import com.Login_Service.controller.AuthController;
import com.Login_Service.entities.User;
import com.Login_Service.exception.PasswordIncorrectException;
import com.Login_Service.model.ForgotPasswordRequest;
import com.Login_Service.model.PasswordResetToken;
import com.Login_Service.repository.TokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class EmailSenderService {

    @Autowired
    private KafkaService kafkaService;
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TokenRepository tokenRepository;

    private Logger logger = LoggerFactory.getLogger(EmailSenderService.class);

    public String sendSimpleEmail(User user){
        String resetToken = generateResetToken(user);
        try{
            SimpleMailMessage msg = new SimpleMailMessage();
            logger.info("email id= "+user.getEmail());
            logger.info("email id= "+user.getName());
            msg.setFrom("java.email.from2204@gmail.com");
            msg.setTo(user.getEmail());
            msg.setText("welcome to body");
            msg.setSubject("Welcome To My Channel");
            msg.setText("Hello \n \n" + "link to reset your password " + resetToken + ".\n \n" +"Regards\n"+"ABC");
            logger.info("message to be sent...");
            mailSender.send(msg);
            logger.info("message sent...");
            return "success";
        }
        catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }
    public String generateResetToken(User user) {

        UUID uuid = UUID.randomUUID();
        String id = UUID.randomUUID().toString();
        LocalDateTime currentDateTime= LocalDateTime.now();
        LocalDateTime expiryDateTime= currentDateTime.plusMinutes(30);
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setId(id);
        resetToken.setUser(user);
        resetToken.setToken(uuid.toString());
        resetToken.setExpDateTime(expiryDateTime);
        resetToken.setUser(user);
        PasswordResetToken token = tokenRepository.save(resetToken);
        System.out.println("resetToken.user " + resetToken.getUser());
        System.out.println("SavedToken" + tokenRepository.findAll());
        if(token!=null){
            String endPointUri = "http://localhost:9021/auth/resetPassword";
            System.out.println(resetToken.getToken());
            return endPointUri + "/" + resetToken.getToken();
        }
        return "";

    }

     public Boolean hasExpired(LocalDateTime expiryDateTime){
        LocalDateTime currentDateTime = LocalDateTime.now();
        return expiryDateTime.isAfter(currentDateTime);
    }

}
