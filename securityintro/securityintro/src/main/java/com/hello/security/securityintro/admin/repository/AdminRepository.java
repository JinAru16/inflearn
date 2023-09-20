package com.hello.security.securityintro.admin.repository;

import com.hello.security.securityintro.admin.Domain.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<NoticeEntity, Long> {
}
