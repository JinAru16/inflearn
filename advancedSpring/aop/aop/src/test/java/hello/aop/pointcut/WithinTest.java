package hello.aop.pointcut;

import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class WithinTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut(); // 포인트컷 표현식을 처리해주는 클래스. 여기에 포인트컷 표현식을 지정하면 된다.
    Method helloMethod;

    @BeforeEach
    public void init()throws NoSuchMethodException{
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class); // 테스트 실행전에 ServiceImpl에 hello() 메타정보를 가져와서 넣어줌.
    }

    @Test
    void printMethod(){
        //
        log.info("helloMethod={}", helloMethod);// 출력결과 : helloMethod=public java.lang.String hello.aop.member.MemberServiceImpl.hello(java.lang.String)
        // execution으로 시작하는 포인트컷의 표현식은 이 메서드 정보를 매칭해서 포인트컷 대상을 찾아낸다.

    }

    @Test
    void withinExact(){// MemberSerivceImpl내 모든 매서드에 대해서 다 동작하게 함. Execution과 비슷한데 거기서 타입부분만 가져온다고 생각하면 편함.
        pointcut.setExpression("execution(public String hello.aop.member.MemberServiceImpl)");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void withinStar(){
        pointcut.setExpression("within(hello.aop.member.*Service*)"); // '*'과 '*'사이에 끼워서 Service가 들어가는 모든 타입에 대해서 적용도 가능함.
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }
    @Test
    void withinSubPackage(){
        pointcut.setExpression("within(hello.aop..*)"); // 서브패키지도 가능.
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    /*
    within사용시 중의사항 : 얘는 표현식에 부모타입을 지정하면 안된다. 정확하게 타입이 맞아야 함. 이부분이 execution과 다름
     */
    @Test
    @DisplayName("타겟의 타입에만 직접 적용, 인터페이스를 선정하면 안된다.")
    void withinSuperTypeFalse(){
        pointcut.setExpression("within(hello.aop.member.MemberService)");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    @DisplayName("execution은 타입기반, 인터페이스 선정도 가능하다..")
    void executionSuperTypeFalse(){
        pointcut.setExpression("within(hello.aop.member.MemberService.*(..)");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }


}
