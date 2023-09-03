package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

public class HelloTraceV2Test {

   @Test
   public void begin_end(){
       //given
       HelloTraceV2 trace = new HelloTraceV2();
       TraceStatus status1 = trace.begin("hello1");
       TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
       //when
       trace.end(status2);
       trace.end(status1);

       //then
   }

   @Test
   public void begin_exception(){
       //given
       HelloTraceV2 trace = new HelloTraceV2();
       TraceStatus status1 = trace.begin("hello");
       TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
       //when
       trace.exception(status2, new IllegalStateException());
       trace.exception(status1, new IllegalStateException());

       //then
   }

}