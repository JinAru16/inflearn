package com.hello.security.securityintro.board.controller;

import com.hello.security.securityintro.board.domain.BoardCreate;
import com.hello.security.securityintro.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")
    public void getBoardList(){

    }

    @PostMapping("/board/create")
    public void postBoard(@RequestBody BoardCreate boardCreate){

    }

}
