package com.hello.security.securityintro.board.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

@Getter
public class BoardDetailDto {
    private Long id;
    private String username;
    private String title;
    private String content;

    @Builder
    public BoardDetailDto(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;
    }
    @Builder
    public BoardDetailDto(Optional<BoardEntity> boardEntity){
        this.id = boardEntity.get().getId();
        this.username = boardEntity.get().getUsername();
        this.title = boardEntity.get().getTitle();
        this.content = boardEntity.get().getContent();
    }

}
