package com.hello.security.securityintro.auth.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    @Builder
    public UserEntity(Long id, String userName, String password, String phoneNumber, String role, LocalDateTime createdAt, LocalDateTime updateAt) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    @PrePersist
    public void onCreate(){
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate(){
        this.updateAt = LocalDateTime.now();
    }
<<<<<<< Updated upstream:securityintro/securityintro/src/main/java/com/hello/security/securityintro/auth/domain/UserEntity.java
    public UserEntity(UserCreate userCreate, String encode){
=======



    public UserEntity(UserCreate userCreate){
>>>>>>> Stashed changes:securityintro/securityintro/src/main/java/com/hello/security/securityintro/user/domain/UserEntity.java
        this.userName = userCreate.getUserName();
        this.password = encode;
        this.phoneNumber = userCreate.getPhoneNumber();
        this.role = "NORMAL";
    }

    public UserEntity() {

    }
}
