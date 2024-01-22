package hello.login.web.filter;

import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {
    // default라는게 생기면서 이게 붙은 아이들은 인터페이스라도 구현하지 않아도 된다.

    private static final String[] whitelist = {"/", "/members/add", "/login", "/logout", "/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            log.info("인증 체크 필터 시작{}", requestURI);

            if(isLoginCheckPath(requestURI)){
                log.info("인증 체크 로직 실행{}", requestURI);
                HttpSession session = httpRequest.getSession(false);
                if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null){
                    log.info("미인증 사용자 요청{}", requestURI);
                    //로그인 페이지로 redirect
                    httpResponse.sendRedirect("/login?redirectURL=" + requestURI); //로그인 후에는 다시 원래 사용자가 보려던 페이지로 자동으로 보내주는 기능임.
                    return;
                }
            }
            chain.doFilter(request, response);

        }catch (Exception e){
            throw e; //예외를 로깅할 수 있지만 톰캣까지 예외를 보내줘야 함.
        }finally {
            log.info("인증 체크 필터 종료{}", requestURI);
        }
    }

    private boolean isLoginCheckPath(String requestURI){
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }

}
