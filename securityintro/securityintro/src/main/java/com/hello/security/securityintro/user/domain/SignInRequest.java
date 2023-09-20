package com.hello.security.securityintro.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class SignInRequest {
    private  String userName;
    private  String password;

    @Builder
    public SignInRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public SignInRequest() {
    }
}
