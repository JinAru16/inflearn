package com.hello.security.securityintro.admin.controller;

import com.hello.security.securityintro.admin.Domain.NoticeListDto;
import com.hello.security.securityintro.admin.service.AdminService;
import com.hello.security.securityintro.security.domain.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/notice")
    public ResponseEntity<?> getNoticeList(@AuthenticationPrincipal UserDetailsImpl userDetails){
        NoticeListDto res = adminService.getNoticeList();
        return ResponseEntity.ok().body(res);
    }
}
