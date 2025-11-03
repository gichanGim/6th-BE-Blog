package com.leets.backend.blog.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다."),
    NO_UPDATE_PERMISSION(HttpStatus.FORBIDDEN, "수정 권한이 없습니다."),
    NO_DELETE_PERMISSION(HttpStatus.FORBIDDEN, "삭제 권한이 없습니다."),
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "중복된 이메일 입니다."),
    DUPLICATED_KAKAO_ID(HttpStatus.CONFLICT, "중복된 카카오 아이디입니다."),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "회원 정보 인증에 실패하였습니다.");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() { return status; }
    public String getMessage() { return message; }
}
