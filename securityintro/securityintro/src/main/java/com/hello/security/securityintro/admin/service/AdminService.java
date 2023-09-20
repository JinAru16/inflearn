package com.hello.security.securityintro.admin.service;

import com.hello.security.securityintro.admin.Domain.NoticeCreate;
import com.hello.security.securityintro.admin.Domain.NoticeEntity;
import com.hello.security.securityintro.admin.Domain.NoticeListDto;
import com.hello.security.securityintro.admin.Domain.NoticeResponse;
import com.hello.security.securityintro.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    public NoticeListDto getNoticeList() {
        List<NoticeEntity> all = adminRepository.findAll();
        List<NoticeResponse> res = all.stream()
                .map(NoticeResponse::new)
                .collect(Collectors.toList());
        return new NoticeListDto(res);
    }

    public Long insertNotice(NoticeCreate noticeCreate){
        NoticeEntity noticeEntity = new NoticeEntity(noticeCreate);
        adminRepository.save(noticeEntity);
        return noticeEntity.getId();
    }
}
