package com.hello.security.securityintro.board.domain;

import lombok.Getter;

@Getter
public class BoardCreate {
    private String username;
    private String title;
    private String content;

    public BoardCreate(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;
    }
}
