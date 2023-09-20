package com.hello.security.securityintro.auth.domain;

import lombok.Builder;
import lombok.Getter;
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
    public UserEntity(UserCreate userCreate, String encode){
        this.userName = userCreate.getUserName();
        this.password = encode;
        this.phoneNumber = userCreate.getPhoneNumber();
        this.role = "NORMAL";
    }

    public UserEntity() {

    }
}
