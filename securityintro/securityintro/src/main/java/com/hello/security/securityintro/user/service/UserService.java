package com.hello.security.securityintro.user.service;

import com.hello.security.securityintro.user.domain.UserCreate;
import com.hello.security.securityintro.user.domain.UserEntity;
import com.hello.security.securityintro.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public void createUser(UserCreate userCreate) {
        UserEntity user = new UserEntity(userCreate);
        userRepository.save(user);
    }
}
