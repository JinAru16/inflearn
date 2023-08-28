package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HelloTraceV1Test {

   @Test
   public void begin_end(){
       //given
       HelloTraceV1 trace = new HelloTraceV1();
       TraceStatus status = trace.begin("hello");
       //when
       trace.end(status);

       //then
   }

   @Test
   public void begin_exception(){
       //given
        HelloTraceV1 trace = new HelloTraceV1();
       TraceStatus staus = trace.begin("hello");
       //when
       trace.exception(staus, new IllegalStateException());

       //then
   }

}