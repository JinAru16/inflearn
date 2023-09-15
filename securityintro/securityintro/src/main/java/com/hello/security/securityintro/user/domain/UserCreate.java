package com.hello.security.securityintro.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserCreate {
    private String userName;
    private String password;
    private String phoneNumber;
}
