package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class ThreadLocalLogTraceTest {
    ThreadLocalLogTrace trace = new ThreadLocalLogTrace();

    @Test
    public void begin_end_level2(){
        //given
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");

        //when
        trace.end(status2);
        trace.end(status1);
        //then
    }
    @Test
    public void begin_exception_level2(){
        //given
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");

        //when
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
        //then
    }

}