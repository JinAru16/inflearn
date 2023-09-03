package hello.advanced.trace.threadlocal.test;

import hello.advanced.trace.threadlocal.code.FieldService;
import hello.advanced.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {
    private ThreadLocalService fieldService = new ThreadLocalService();

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
        sleep(100);// ThreadA가 실행중에 ThreadB가 실행되어 userB를 nameStore에 저장하더라도 서로의 ThreadLocal이 분리되어 있으므로 동시성 이슈가 없다.


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
