package hello.aop.pointcut;

import hello.aop.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest (properties = "spring.aop.proxy-target-class=true") //application.properties에 넣어주는건데 이렇게 해줘도 됨.
@Import(ThisTargetTest.ThisTargetAspect.class)

/*
spring.aop.proxy-target-class=true : CGLIB로 동작
spring.aop.proxy-target-class=false : JDK동적 프록시로 동작
 */
public class ThisTargetTest {

    @Autowired
    MemberService memberService;

    @Test
    void success(){
        log.info("memberService Proxy={}", memberService.getClass());
        memberService.hello("helloA");
    }

    @Slf4j
    @Aspect
    static class ThisTargetAspect{

        // this는 부모타입을 허용함.
        @Around("this(hello.aop.member.MemberService)")
        public Object doThisInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-interface]{}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
        // target도 부모타입을 허용함.
        @Around("target(hello.aop.member.MemberService)")
        public Object doTargetInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-interface]{}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
        @Around("this(hello.aop.member.MemberServiceImpl)")
        public Object doThisImpl(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-impl]{}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
        @Around("target(hello.aop.member.MemberServiceImpl)")
        public Object doTargetImpl(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-impl]{}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }
}
