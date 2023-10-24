package hello.aop.pointcut;

import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

@Slf4j
public class ExecutionTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut(); // 포인트컷 표현식을 처리해주는 클래스. 여기에 포인트컷 표현식을 지정하면 된다.
    Method helloMethod;

    @BeforeEach
    public void init()throws NoSuchMethodException{
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    void printMethod(){
        //
        log.info("helloMethod={}", helloMethod);// 출력결과 : helloMethod=public java.lang.String hello.aop.member.MemberServiceImpl.hello(java.lang.String)
        // execution으로 시작하는 포인트컷의 표현식은 이 메서드 정보를 매칭해서 포인트컷 대상을 찾아낸다.

    }

}
