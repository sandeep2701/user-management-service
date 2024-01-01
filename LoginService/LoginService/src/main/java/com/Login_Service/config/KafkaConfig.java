package com.Login_Service.config;

import com.Login_Service.constants.AppConstants;
import com.Login_Service.entities.User;
import com.Login_Service.services.UserService;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class KafkaConfig {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @KafkaListener(groupId = AppConstants.GROUP_ID,topics=AppConstants.USER_TOPIC_NAME)
    public void updatedUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        System.out.println(user);
    }

    @Bean
    public NewTopic topic(){
        return TopicBuilder
                .name(AppConstants.USER_EMAIL_TOPIC_NAME)
                .build();
    }
}
