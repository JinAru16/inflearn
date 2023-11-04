package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV2 {

    //private final ApplicationContext applicationContext; application context는 너무 많은 기능을 제공해서 무거움. 이거 전체 다 들고오는것 보다는 ObjectProvider를 쓰자
    private final ObjectProvider<CallServiceV2> callServiceProvider;

    public CallServiceV2(ObjectProvider<CallServiceV2> callServiceProvider) {
        this.callServiceProvider = callServiceProvider;
    }

    public void external(){
        log.info("call external");
        CallServiceV2 callServiceV2 = callServiceProvider.getObject(CallServiceV2.class);// 빈이 다 생성된 후 나중에 꺼내서 쓴다 -> 지연생성 전략
        callServiceV2.internal();// setter로 주입받은 inertnal을 호출하여 내부함수 호출시 aop가 적용되지 않던 현상 해결.
    }

    public void internal(){
        log.info("call internal");
    }

}

