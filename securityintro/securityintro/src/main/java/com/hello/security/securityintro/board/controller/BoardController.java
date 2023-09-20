package com.hello.security.securityintro.board.controller;

import com.hello.security.securityintro.board.domain.BoardCreate;
import com.hello.security.securityintro.board.domain.BoardDetailDto;
import com.hello.security.securityintro.board.domain.BoardListDto;
import com.hello.security.securityintro.board.service.BoardService;
import com.hello.security.securityintro.security.domain.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // Board 리스트 조회
    @GetMapping("/board")
    public ResponseEntity<?>  getBoardList(){
        BoardListDto boardList = boardService.getBoardList();
        return ResponseEntity.ok().body(boardList);
    }

    // Board 단건조회
    @GetMapping("/board/{id}")
    public BoardDetailDto getOneBoard(@PathVariable("id") Long id){
        return boardService.getOneBoard(id);
    }

    //단건 입력
    @PostMapping("/board/create")
    public Long postBoard(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody BoardCreate board){
        return boardService.insertBoard(board, userDetails.getUsername());
    }

    // 단건 수정
    @PatchMapping("/board/update")
    public Long patchBoard(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody BoardDetailDto board){
        return boardService.updateBoard(board);
    }

    @GetMapping("/router")
    public void router(@AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println("Only Special Role could access ");
    }

}
