package hello.aop.pointcut;

import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class ExecutionTest {

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
    // 저 위에 helloMethod를 찾는 가장 정확한 포인트컷을 execution 문법으로 찾아볼 계획임.
    void exactMatch(){

        pointcut.setExpression("execution(public String hello.aop.member.MemberServiceImpl.hello(String))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue(); //테스트 실행전 MemberServiceImpl에서 가져온 hello()와 execution으로 찾아온 포인트컷 정보와 같은지 확인함
        /*
        public : 접근제어자
        String : 반환타입
        선언타입 : hello.~~~
        메서드 : hello()
        파라미터 : (String)
         */
    }

    @Test
    // execution 표현식 중 가장 생략을 많이 한 문법으로 찾아볼 계획
    void allMatch(){
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue()
;        /*
        접근제어자? : 생략가능
        반환타입 : * (암거나 반환가능)
        선언타입 : 생략가능
        메서드이름 : *
        파라미터 : (..); 파라미터에서 '..'은 파라미터 타임과 수가 상광벗다는 뜻이다.
        예외 : 없음
         */
    }

    @Test
    void nameMatch(){//메서드 명으로 찾기
        pointcut.setExpression("execution(* hello(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }
    @Test
    void nameMatch1(){//메서드 명으로 찾는건 같지만 패턴 매치
        pointcut.setExpression("execution(* hel*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }
    @Test
    void nameMatch2(){//메서드 명으로 찾는건 같지만 패턴 매치
        pointcut.setExpression("execution(* *el*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }
    @Test
    void nameMatchFail(){//메서드 명으로 찾는건 같지만 패턴 매치
        pointcut.setExpression("execution(* none(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    void packageExactMatch1(){//메서드 명으로 찾는건 같지만 패키지 매치
        pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.hello(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }
    @Test
    void packageExactMatch2(){//메서드 명으로 찾는건 같지만 패턴 매치
        pointcut.setExpression("execution(* hello.aop.member.*.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageExactMatchFail(){//메서드 명으로 찾는건 같지만 패턴 매치
        pointcut.setExpression("execution(* hello.aop.*.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    void packageMatchSubPackage1(){//메서드 명으로 찾는건 같지만 패턴 매치
        pointcut.setExpression("execution(* hello.aop.member..*.*(..))");// member패키지 하위의 모든 패키지들은 aop적용 대상이 됨
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageMatchSubPackage2(){//메서드 명으로 찾는건 같지만 패턴 매치
        pointcut.setExpression("execution(* hello.aop..*.*(..))");// aop패키지 하위의 모든 패키지들은 aop적용 대상이 됨
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }


}
