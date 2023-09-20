package com.hello.security.securityintro.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class SignInResponse implements Serializable {
    private String accessToken;
    private String refreshToken;

    @Builder
    public SignInResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
