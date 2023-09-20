package com.hello.security.securityintro.admin.Domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NoticeCreate {
    private String title;
    private String content;

    @Builder
    public NoticeCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
