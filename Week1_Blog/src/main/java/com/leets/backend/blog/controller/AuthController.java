package com.leets.backend.blog.controller;

import com.leets.backend.blog.dto.request.LoginRequestDTO;
import com.leets.backend.blog.dto.request.RefreshTokenRequestDTO;
import com.leets.backend.blog.dto.request.UserCreateRequestDTO;
import com.leets.backend.blog.dto.response.LoginResponseDTO;
import com.leets.backend.blog.dto.response.TokenResponseDTO;
import com.leets.backend.blog.dto.response.UserResponseDTO;
import com.leets.backend.blog.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "회원 인증 및 토큰 refresh 요청")
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @Operation(
            summary = "로그인 인증",
            description = "로그인 요청, 성공 시 success: true, 닉네임, token반환, 실패 시 success: false"
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO request) {
        LoginResponseDTO response = service.userLogin(request);

        ResponseCookie cookie = ResponseCookie.from("accessToken",
                        response.getToken() != null ? response.getToken().getAccessToken() : "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(15 * 60)
                .build();

        return ResponseEntity.ok()
                .header("Set-Cookie", cookie.toString())
                .body(response);
    }

    @Operation(
            summary = "토큰 리프레쉬",
            description = "refresh토큰으로 클라이언트에서 access토큰 발급"
    )
    @PostMapping("/refresh")
    public ResponseEntity<TokenResponseDTO> refresh(@RequestBody RefreshTokenRequestDTO request) {
        TokenResponseDTO tokenResponse = service.refreshAccessToken(request);

        ResponseCookie cookie = ResponseCookie.from("accessToken", tokenResponse.getAccessToken())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(15 * 60) // 15분
                .build();

        return ResponseEntity.ok()
                .header("Set-Cookie", cookie.toString())
                .body(tokenResponse);
    }

    @Operation(
            summary = "로그아웃",
            description = "로그아웃 + refresh 토큰 삭제"
    )
    @PostMapping("/logout")
        public ResponseEntity<?> logout(@RequestBody RefreshTokenRequestDTO request) {
        service.logout(request.getRefreshToken());

        // 쿠키 만료
        ResponseCookie deleteCookie = ResponseCookie.from("accessToken", "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0) // 만료
                .build();

        return ResponseEntity.ok()
                .header("Set-Cookie", deleteCookie.toString())
                .body("로그아웃 완료");
    }


    @Operation(
            summary = "신규 회원 생성",
            description = "회원가입을 성공적으로 마친 회원의 정보에 따라 회원 새로 생성"
    )
    @PostMapping("/register")
    public UserResponseDTO createUser(@Valid @RequestBody UserCreateRequestDTO dto){
        return service.createUser(dto);
    }
}
