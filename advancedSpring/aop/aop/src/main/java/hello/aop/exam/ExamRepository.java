package hello.aop.exam;

import hello.aop.exam.annotation.Retry;
import hello.aop.exam.annotation.Trace;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Target;

@Repository
public class ExamRepository {
    private static int seq = 0;

    /**
     *  5번에 1번은 실패하는 요청
     */

    @Trace
    @Retry(value = 5)
    public String save(String itemid){
        seq++;
        if(seq % 5 == 0) {
            throw new IllegalStateException("예외 발생");
        }
        return "ok";
    }
}
