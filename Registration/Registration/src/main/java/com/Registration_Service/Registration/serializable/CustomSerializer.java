package com.Registration_Service.Registration.serializable;

import com.Registration_Service.Registration.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class CustomSerializer implements Serializer<User> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, User user) {
        try {
            if (user == null){
                System.out.println("Null received at serializing");
                return null;
            }
            System.out.println("Serializing...");
            return objectMapper.writeValueAsBytes(user);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing User to byte[]");
        }
    }

    @Override
    public void close() {
    }
}