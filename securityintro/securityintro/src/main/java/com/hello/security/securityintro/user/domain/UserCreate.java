package com.hello.security.securityintro.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserCreate {
    private String userName;
    private String password;
    private String phoneNumber;

    @Builder
    public UserCreate(String userName, String password, String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
