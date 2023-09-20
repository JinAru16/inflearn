package com.hello.security.securityintro.admin.Domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NoticeResponse {
    private Long id;
    private String title;
    private String content;

    @Builder
    public NoticeResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public NoticeResponse(NoticeEntity notice){
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.content = notice.getContent();
    }
}
