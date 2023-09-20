package com.hello.security.securityintro.board.service;

import com.hello.security.securityintro.board.domain.BoardCreate;
import com.hello.security.securityintro.board.domain.BoardDetailDto;
import com.hello.security.securityintro.board.domain.BoardEntity;
import com.hello.security.securityintro.board.domain.BoardListDto;
import com.hello.security.securityintro.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public BoardListDto getBoardList() {
        List<BoardEntity> req = boardRepository.findAll();
        for (BoardEntity boardEntity : req) {
            log.info("repository log has been appear : "+boardEntity.getTitle());
        }

        return new BoardListDto(req);
    }

    public BoardDetailDto getOneBoard(Long id) {
        Optional<BoardEntity> req = boardRepository.findById(id);
        return req.map(b -> new BoardDetailDto(req)).orElseThrow();
    }

    public Long insertBoard(BoardCreate board, String userName) {
        BoardEntity req = new BoardEntity(board, userName);
        BoardEntity save = boardRepository.save(req);
        return save.getId();
    }

    public Long updateBoard(BoardDetailDto board) {
        BoardEntity res = boardRepository.findById(board.getId()).orElseThrow();
        res.setTitle(board.getTitle());
        res.setContent(board.getContent());

        return  boardRepository.save(res).getId();
    }
}
