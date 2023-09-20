package com.hello.security.securityintro.board.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class BoardListDto {
    private List<BoardEntity> boardList;

    public BoardListDto(List<BoardEntity> boardList) {
        this.boardList = boardList;
    }
}
