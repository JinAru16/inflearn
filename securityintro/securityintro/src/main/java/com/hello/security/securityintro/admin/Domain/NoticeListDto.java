package com.hello.security.securityintro.admin.Domain;

import lombok.Getter;

import java.util.List;

@Getter
public class NoticeListDto {

     List<NoticeResponse> NoticeList;

    public NoticeListDto(List<NoticeResponse> noticeList) {
        NoticeList = noticeList;
    }
}
