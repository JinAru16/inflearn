package com.hello.security.securityintro.auth.repository;

import com.hello.security.securityintro.auth.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface AuthRepository extends JpaRepository<UserEntity, Long> {
    public UserEntity findByUserName(String username);
}
