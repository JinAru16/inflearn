package com.hello.security.securityintro.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private String phoneNumber;

    private String role;

    @Builder
    public UserEntity(String userName, String password, String phoneNumber, String role) {
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;

    }
    public UserEntity(UserCreate userCreate){
        this.userName = userCreate.getUserName();
        this.password = userCreate.getPassword();
        this.phoneNumber = userCreate.getPhoneNumber();
        this.role = "NORMAL";
    }

    public UserEntity() {

    }
}
