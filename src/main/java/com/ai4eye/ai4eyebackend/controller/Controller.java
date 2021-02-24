package com.ai4eye.ai4eyebackend.controller;

import com.ai4eye.ai4eyebackend.model.Challenge;
import com.ai4eye.ai4eyebackend.model.ChallengeResponse;
import com.ai4eye.ai4eyebackend.model.CustomerProfile;
import com.ai4eye.ai4eyebackend.model.UserSession;
import com.ai4eye.ai4eyebackend.service.ChallengeService;
import com.ai4eye.ai4eyebackend.service.CustomerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    private CustomerProfileService profileService;

    @Autowired
    private ChallengeService challengeService;

    @Autowired
    private UserSessionManager sessionManager;

    @GetMapping(value = "/identifyByCardNumber")
    public CustomerProfile identifyByCardNumber(@RequestParam String cardNumber){
        CustomerProfile profile = profileService.getValidateProfile(cardNumber);
        final UserSession userSession = sessionManager.createUserSession(profile);
        return profile;
    }

    @GetMapping(value = "/getChallenge")
    public Challenge getChallenge(@RequestParam String sessionId) throws Exception{
        final UserSession userSession = sessionManager.getUserSession(sessionId);
        return challengeService.getNextChallenge(userSession);
    }

    @PostMapping(value = "/submitChallenge")
    public ChallengeResponse submitChallenge(@RequestBody Challenge challenge, @RequestParam String sessionId)throws Exception{
        final UserSession userSession = sessionManager.getUserSession(sessionId);
        return challengeService.validateChallenge(challenge,userSession);
    }
}
