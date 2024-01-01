package com.Login_Service.services;

import com.Login_Service.constants.AppConstants;
import com.Login_Service.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    @Autowired
    private KafkaTemplate<String,String>    kafkaTemplate;

    private Logger logger = LoggerFactory.getLogger(KafkaService.class);
    /*public boolean sendToken(String id){
        kafkaTemplate.send(AppConstants.USER_EMAIL_TOPIC_NAME,id);
        logger.info("token produced");
        return true;
    }*/
}
