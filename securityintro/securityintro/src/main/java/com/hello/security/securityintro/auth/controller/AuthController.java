package com.hello.security.securityintro.auth.controller;

import com.hello.security.securityintro.config.JwtConfig;
import com.hello.security.securityintro.security.domain.UserDetailsImpl;
import com.hello.security.securityintro.auth.domain.SignInRequest;
import com.hello.security.securityintro.auth.domain.SignInResponse;
import com.hello.security.securityintro.auth.domain.UserCreate;
import com.hello.security.securityintro.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticationSignIn(@RequestBody SignInRequest req){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(req.getUserName(), req.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String accessToken = jwtConfig.generateJwtToken(userDetails);
        String refreshToken = jwtConfig.generateJwtRefreshToken(userDetails);

        SignInResponse response = SignInResponse.builder().accessToken(accessToken)
                .refreshToken(refreshToken).build();
        return ResponseEntity.ok().body(response);
    }
    @PostMapping("/signup")
    public String signUp(@RequestBody UserCreate userCreate){
        return authService.createUser(userCreate);
    }
}