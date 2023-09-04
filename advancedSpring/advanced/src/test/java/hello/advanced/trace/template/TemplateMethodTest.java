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

    /**
     * 익명 내부 클래스를 이용한 방법.
     */
    @Test
    public void templateMethodV2(){
        AbstractTemplate template1 = new AbstractTemplate(){

            @Override
            protected void call() {
                log.info("비즈니스 로직1 실행");
            }
        };
        AbstractTemplate template2 = new AbstractTemplate(){

            @Override
            protected void call() {
                log.info("비즈니스 로직2 실행");
            }
        };

        template1.execute();
        template2.execute();
        /**
         * 템플릿 메서드는 SubClassLogic1, SubClassLogic2처럼 클래스를 계속 만들어야 하는 단점이 있다.
         * 이를 익명 내부 클래스 함수를 만들면 보완이 가능하다.
         * 익명 내부 클래스를 이용하면 객체 인스턴스를 생성하면서 동시에 생성할 클래스를 상속바등ㄴ 자식 클래스를 정의 할 수 있다. 이 클래스는
         * SubClassLogic1처럼 직접 지정하는 이름이 없고 클래스 내부에서 선언되는 클래스여서 익명 내부 클래스라고 한다.
         * 익명 내부 클래스에 대한 자세한 내용은 자바 기본 문법을 참고하자.
         */
    }


}
