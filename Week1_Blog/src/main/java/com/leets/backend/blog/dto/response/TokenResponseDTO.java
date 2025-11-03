package com.leets.backend.blog.dto.response;

public class TokenResponseDTO {

    private String accessToken;
    private String refreshToken;
    private final String tokenType = "Bearer"; // 토큰 타입은 항상 Bearer로 고정

    public TokenResponseDTO(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}
