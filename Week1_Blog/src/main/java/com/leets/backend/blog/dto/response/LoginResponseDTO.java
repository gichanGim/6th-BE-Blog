package com.leets.backend.blog.dto.response;

public class LoginResponseDTO {
    private Boolean success;
    private String message;
    private String nickname;
    private TokenResponseDTO token;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(Boolean success, String message, String nickname, TokenResponseDTO token) {
        this.nickname = nickname;
        this.message = message;
        this.success = success;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public String getNickname() {
        return nickname;
    }

    public Boolean getSuccess() {
        return success;
    }

    public TokenResponseDTO getToken() {
        return token;
    }
}
