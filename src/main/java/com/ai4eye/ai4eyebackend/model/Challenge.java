package com.ai4eye.ai4eyebackend.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Challenge {
    private String sessionId;
    private ChallengeQuestion question;
    private String answer;
}
