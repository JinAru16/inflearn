package com.hello.security.securityintro.initdb;

import com.hello.security.securityintro.admin.Domain.NoticeCreate;
import com.hello.security.securityintro.admin.service.AdminService;
import com.hello.security.securityintro.board.domain.BoardDetailDto;
import com.hello.security.securityintro.board.service.BoardService;
import com.hello.security.securityintro.user.domain.UserCreate;
import com.hello.security.securityintro.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class defaultData {

    private final InitService initService;

    @PostConstruct
    public void init() throws Exception{
        initService.userInit();
        initService.boardInit();
        initService.noticeInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    public static class InitService{
        private final EntityManager em;
        private final UserService userService;
        private final BoardService boardService;
        private final AdminService adminService;


        public void userInit(){
            UserCreate user1 = UserCreate.builder()
                    .userName("admin1")
                    .password("1234")
                    .phoneNumber("010-1111-2222")
                    .build();

            UserCreate user2 = UserCreate.builder()
                            .userName("admin2")
                            .password("1234")
                            .phoneNumber("010-2222-3333")
                            .build();

            userService.createUser(user1);
            userService.createUser(user2);
        }

        public void boardInit(){
            BoardDetailDto detail = BoardDetailDto.builder()
                    .username("admin1")
                    .title("testing board")
                    .content("content created")
                    .build();

            BoardDetailDto detail2 = BoardDetailDto.builder()
                    .username("admin2")
                    .title("content for finding result")
                    .content("content has created")
                    .build();

            BoardDetailDto detail3 = BoardDetailDto.builder()
                    .username("admin2")
                    .title("content created 3")
                    .content("content has created")
                    .build();
            BoardDetailDto detail4 = BoardDetailDto.builder()
                    .username("admin1")
                    .title("content created 4")
                    .content("admin content has created")
                    .build();
            BoardDetailDto detail5 = BoardDetailDto.builder()
                    .username("admin2")
                    .title("admin2's contend has created")
                    .content("admin2's content has created")
                    .build();


            boardService.insertBoard(detail);
            boardService.insertBoard(detail2);
            boardService.insertBoard(detail3);
            boardService.insertBoard(detail4);
            boardService.insertBoard(detail5);
        }
        public void noticeInit(){
            NoticeCreate notice1 = NoticeCreate.builder()
                    .title("공지1")
                    .content("공지사항1")
                    .build();
            NoticeCreate notice2 = NoticeCreate.builder()
                    .title("공지2")
                    .content("공지사항2")
                    .build();
            NoticeCreate notice3 = NoticeCreate.builder()
                    .title("공지3")
                    .content("공지사항3")
                    .build();


            adminService.insertNotice(notice1);
            adminService.insertNotice(notice2);
            adminService.insertNotice(notice3);
        }

    }
}
