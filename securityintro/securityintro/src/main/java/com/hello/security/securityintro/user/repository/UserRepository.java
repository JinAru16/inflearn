package com.hello.security.securityintro.user.repository;

import com.hello.security.securityintro.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public UserEntity findByUserName(String username);
}
