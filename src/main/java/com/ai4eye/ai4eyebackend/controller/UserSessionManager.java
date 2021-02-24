package com.ai4eye.ai4eyebackend.controller;

import com.ai4eye.ai4eyebackend.model.CustomerProfile;
import com.ai4eye.ai4eyebackend.model.UserSession;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserSessionManager {
    public Map<String, UserSession> userSessionMap = new HashMap<>();

    public UserSession getUserSession(String sessionId) throws Exception{

        UserSession userSession = userSessionMap.get(sessionId);
        validateSession(userSession);
        return userSession;
    }

    public UserSession createUserSession(CustomerProfile customerProfile){
        String sessionId = String.valueOf(System.currentTimeMillis());
        customerProfile.setSessionId(sessionId);
        UserSession userSession = UserSession.builder()
                .sessionId(sessionId)
                .customerProfile(customerProfile)
                .lastAccessTime(LocalDateTime.now())
                .challenges(new ArrayList<>())
                .challengeResponses(new ArrayList<>())
                .build();
        userSessionMap.put(sessionId,userSession);
        return userSession;
    }
    private void validateSession(UserSession userSession) throws Exception {

        Long diff = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - userSession.getLastAccessTime().toEpochSecond(ZoneOffset.UTC);
        if(diff> userSession.getCustomerProfile().getSessionTimeOut()*3600*1000 )
        {
            throw new Exception("Time out happened for this user ");
        }
        userSession.setLastAccessTime(LocalDateTime.now());
    }

}
