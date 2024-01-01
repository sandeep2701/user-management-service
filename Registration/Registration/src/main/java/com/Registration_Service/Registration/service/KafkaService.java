package com.Registration_Service.Registration.service;

import com.Registration_Service.Registration.config.AppConstants;
import com.Registration_Service.Registration.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    @Autowired
    private KafkaTemplate<String,User>    kafkaTemplate;

    private Logger logger = LoggerFactory.getLogger(KafkaService.class);
    public boolean updateUser(User user){
        kafkaTemplate.send(AppConstants.USER_TOPIC_NAME,user);
        logger.info("message produced");
        return true;
    }
}
