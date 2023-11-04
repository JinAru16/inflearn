package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import hello.aop.proxyvs.code.ProxyDiAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"}) //JDK동적 프록시
@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"}) //CGLIB프록시
@Import(ProxyDiAspect.class)
class ProxyDiAspectTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberServiceImpl memberServiceImpl;

    /**
     * JDK 동적 프록시는 대상 객체인 MemberServiceImpl 타입에 의존관계를 주입할 수 없다.
     * CGLIB 프록시는 대상 객체인 MemberServiceImpl 타입에 의존관계를 주입할 수 '있다'.

     -> 여기까지 보면 impl주입 방식이 젤 좋아보임.

     but DI의 의미가 많이 퇴색됨.
     DI의 장점은 클라이언트 코드의 변경 없이 구현클래스를 변경할 수 있다는것이다.
     구체타입으로 직접 의존관계를 주입받아버리면 향후 구현츨래스를 변경할 때 의존관계 주입을 받는 클라이언트의 코드도 함께 변경이 되어야한다.

     그럼에도 불구하고 여러가지 이유로 AOP 프록시가 적용된 구체클래스를 직접 구현클래스를 주입받아야하는 일이 생긴다.
     그때는 CGLIB를 통해 구체클래스를 기반으로 AOP를 적용면된다.
     */

    @Test
    void go(){
        log.info("memberService class={}", memberService.getClass());
        log.info("impl class = {}", memberServiceImpl.getClass());
        memberServiceImpl.hello("hello");
    }

}