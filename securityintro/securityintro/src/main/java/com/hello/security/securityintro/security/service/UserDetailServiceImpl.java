package com.hello.security.securityintro.security.service;

import com.hello.security.securityintro.security.domain.UserDetailsImpl;
import com.hello.security.securityintro.user.domain.UserEntity;
import com.hello.security.securityintro.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userInfo = userRepository.findByUserName(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .username(userInfo.getUserName())
                .password(userInfo.getPassword())
                .role((userInfo.getRole()))
                .phoneNumber(userInfo.getPhoneNumber())
                .build();
        return userDetails;
    }
}
