package com.ai4eye.ai4eyebackend.service;

import com.ai4eye.ai4eyebackend.model.Channel;
import com.ai4eye.ai4eyebackend.model.CustomerProfile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CustomerProfileService {
    public CustomerProfile getValidateProfile(String cardNumber){
        return CustomerProfile.builder()
                .channel(Channel.AUDIO)
                .firstName("Somnath")
                .lastName("Pailwan")
                .customerId("1234")
                .sessionTimeOut(5l)
                .pin(1234l)
                .build();
    }
}
