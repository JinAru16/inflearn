package com.hello.security.securityintro.admin.Domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class NoticeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @Builder
    public NoticeEntity(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
    public NoticeEntity(NoticeCreate noticeCreate){
        this.title = noticeCreate.getTitle();
        this.content = noticeCreate.getContent();
    }
    public NoticeEntity() {

    }
}
