package com.leets.backend.blog.dto.response;

public class LoginResponseDTO {
    private boolean success = true;
    private String nickname;
    private TokenResponseDTO token;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String nickname, TokenResponseDTO token) {
        this.nickname = nickname;
        this.token = token;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getNickname() {
        return nickname;
    }

    public TokenResponseDTO getToken() {
        return token;
    }
}
