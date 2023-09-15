package com.hello.security.securityintro.user.controller;

import com.hello.security.securityintro.user.domain.UserCreate;
import com.hello.security.securityintro.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/info/{id}")
    public void findUser(){

    }

    @PostMapping("/login")
    public void loginUser(){

    }

    @PostMapping("/signup")
    public void signUp(@RequestBody UserCreate userCreate){
        userService.createUser(userCreate);
    }
}
