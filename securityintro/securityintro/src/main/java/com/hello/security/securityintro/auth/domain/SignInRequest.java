package com.hello.security.securityintro.auth.domain;

import lombok.Builder;
import lombok.Getter;

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
