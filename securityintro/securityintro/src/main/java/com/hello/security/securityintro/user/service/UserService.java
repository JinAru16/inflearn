package com.hello.security.securityintro.user.service;

import com.hello.security.securityintro.user.domain.UserCreate;
import com.hello.security.securityintro.user.domain.UserEntity;
import com.hello.security.securityintro.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    public String createUser(UserCreate userCreate) {
        String encode = passwordEncoder.encode(userCreate.getPassword());
        UserEntity user = new UserEntity(userCreate, encode);

        UserEntity save = userRepository.save(user);

        return save.getUserName();
    }
}
