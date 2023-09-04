package hello.advanced.trace.template;

import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {
    @Test
    public void templateMethodV0(){
        logic1();
        logic2();
    }
    private void logic1() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        log.info("비즈니스 로직1 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);

    }
    private void logic2() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        log.info("비즈니스 로직2 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);

    }

    /**
     * 템플릿 메서드 패턴 적용
     */
    @Test
    public void templateMethodV1(){
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();

        AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();
        /**
         * 자바의 다형성을 이용한거임.
         * execute()는 AbstractTemplate에 있음
         * AbstractTemplate을 실행하다가 call()이 동작하면 자바에서 오버라이딩된 메서드가 있으면
         * 오버라이딩된 메서드를 무조건 실행하게 되는 원리를 이용한거임.
         * template1에선 SubClassLogic1()에 있는 call()을 실행한거임
         * template2에선 SubClassLogic2()에서 오버라이딩 한 call()을 실행한거고. ㅇㅋ?
         */
    }


}
