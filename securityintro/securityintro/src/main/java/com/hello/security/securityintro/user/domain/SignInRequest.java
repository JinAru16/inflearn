package com.hello.security.securityintro.user.domain;

import lombok.Getter;

@Getter
public class SignInRequest {
    private String userName;
    private String password;
}
