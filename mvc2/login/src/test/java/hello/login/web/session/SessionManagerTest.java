package hello.login.web.session;

import hello.login.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class SessionManagerTest {
    SessionManager sessionManager = new SessionManager();

    @Test
    @DisplayName("")
    public void test(){
        //세션 생성
        // HttpServletResponse는 인터페이스고 구현체는 톰켓이나 이런거에서 제공하는거라 테스트가 까다로움 -> 스프링에서 제공하는 MockHttpServeltResponse를 사용하자!
        MockHttpServletResponse response = new MockHttpServletResponse();

        Member member = new Member();
        sessionManager.createSesion(member, response);

        //요청에 응답 쿠키 저장.
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        //세션 조회
        Object result = sessionManager.getSession(request);

        assertThat(result).isEqualTo(member);

        //세션 만료
        sessionManager.expire(request);

        Object expired = sessionManager.getSession(request);
        assertThat(expired).isNull();


    }



}