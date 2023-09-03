package hello.advanced;

import hello.advanced.trace.logtrace.FieldLogTrace;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {
//    @Bean
//    public LogTrace logTrace(){
//        return new FieldLogTrace();
//    }
// 쓰레드 적용한 LogTrace로 변경을 위한 주석

    @Bean
    public LogTrace logTrace(){
        return new ThreadLocalLogTrace();
    }
}
