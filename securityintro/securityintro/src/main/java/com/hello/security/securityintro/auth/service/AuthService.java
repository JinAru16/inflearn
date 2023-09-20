package com.hello.security.securityintro.auth.service;

import com.hello.security.securityintro.auth.domain.UserCreate;
import com.hello.security.securityintro.auth.domain.UserEntity;
import com.hello.security.securityintro.auth.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    public String createUser(UserCreate userCreate) {
        String encode = passwordEncoder.encode(userCreate.getPassword());
        UserEntity user = new UserEntity(userCreate, encode);

        UserEntity save = authRepository.save(user);

        return save.getUserName();
    }
}
