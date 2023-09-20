package com.hello.security.securityintro.board.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String title;
    private String content;

    @Builder
    public BoardEntity(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;
    }

    @Builder
    public BoardEntity(BoardDetailDto dto){
        this.username = dto.getUsername();
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }

    public BoardEntity() {

    }
}
