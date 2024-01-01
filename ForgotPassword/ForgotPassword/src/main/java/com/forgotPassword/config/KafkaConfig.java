package com.forgotPassword.config;

import com.forgotPassword.constants.AppConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConfig {

    @KafkaListener(groupId = AppConstants.GROUP_ID,topics=AppConstants.USER_EMAIL_TOPIC_NAME)
    public void getToken(String token){
        System.out.println(token);
    }
}
