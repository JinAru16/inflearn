package hello.advanced.trace.threadlocal.test;

import hello.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {
    private FieldService fieldService = new FieldService();

    @Test
    public void field(){
        //given
        log.info("mail start");
        Runnable userA = () -> {
            fieldService.logic("userA");
        };
        Runnable userB = () -> {
            fieldService.logic("userB");
        };
        //when
        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        //then
        threadA.start();
        //sleep(2000);// 동시성 문제가 없는 코드.
        sleep(100);// 동시성 문제가 발생
        /**
         * threadA의 연산이 완전히 종료되기 전에 threadB의 연산이 실행되어서 nameStore에 벌써 userB로 바뀌어버려서 이렇게 되는거임
         * threadA가 userA를 꺼내 쓰기도 전에 userB로 바뀌어 버려서 발생하는 현상
         *
         */



        threadB.start();
        sleep(3000);// 메인 쓰레드 종료 대기
        log.info("main exit");

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
