package com.ai4eye.ai4eyebackend.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class UserSession {
    private String sessionId;
    private CustomerProfile customerProfile;
    private List<Challenge> challenges = new ArrayList<>();
    private List<ChallengeResponse> challengeResponses = new ArrayList<>();
    private LocalDateTime lastAccessTime;
    private int attempt;
}
