package com.Login_Service.deserializable;

import com.Login_Service.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class CustomDeserializer implements Deserializer<User> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public User deserialize(String topic, byte[] user) {
        try {
            if (user == null){
                System.out.println("Null received at deserializing");
                return null;
            }
            System.out.println("Deserializing...");
            return objectMapper.readValue(new String(user, "UTF-8"), User.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to User");
        }
    }

    @Override
    public void close() {
    }
}