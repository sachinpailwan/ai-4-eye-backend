package com.ai4eye.ai4eyebackend.service;

import com.ai4eye.ai4eyebackend.model.Challenge;
import com.ai4eye.ai4eyebackend.model.ChallengeQuestion;
import com.ai4eye.ai4eyebackend.model.ChallengeResponse;
import com.ai4eye.ai4eyebackend.model.UserSession;
import org.springframework.stereotype.Service;

@Service
public class ChallengeService {

    public Challenge getNextChallenge(UserSession userSession){
        Challenge challenge = null;
        if(userSession.getChallenges().isEmpty()){
            challenge = Challenge.builder()
                    .question(ChallengeQuestion.ADD_5_TO_YOUR_PIN)
                    .sessionId("")
                    .build();
            userSession.getChallenges().add(challenge);
        }
        return challenge;
    }

    public ChallengeResponse validateChallenge(Challenge challenge, UserSession userSession){
        ChallengeResponse response = null;
        if(challenge.getQuestion().equals(ChallengeQuestion.ADD_3_TO_5TH_DIGIT_OF_PIN)){
           String validAnswer = String.valueOf((((int)String.valueOf(userSession.getCustomerProfile().getPin()).charAt(4))
           )+ 3);
            if(challenge.getAnswer().contains(validAnswer)){
                   return ChallengeResponse.builder().status("passed").build();
            }
        }else if(challenge.getQuestion().equals(ChallengeQuestion.ADD_10_TO_1ST_DIGIT_OF_PIN)){
            String validAnswer = String.valueOf((((int)
                    String.valueOf(userSession.getCustomerProfile().getPin()).charAt(0))
            )+ 10);
            if(challenge.getAnswer().contains(validAnswer)){
                return ChallengeResponse.builder().status("passed").build();
            }
        }else if(challenge.getQuestion().equals(ChallengeQuestion.ADD_5_TO_YOUR_PIN)){
            String validAnswer = String.valueOf(userSession.getCustomerProfile().getPin()+5);
            if(challenge.getAnswer().contains(validAnswer)){
                return ChallengeResponse.builder().status("passed").build();
            }
        }
        return ChallengeResponse.builder().status("Incorrect challenge response").build();
    }
}
