package hello.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        //Object result = method.invoke(target, args);
        /*
        원래 TimeInvocationHandler에서는 우리가 target을 생성자주입 해줬는데
        이제는 안넣어줘도 됨
        프록시 팩토리를 만들때 타겟을 파라미터로 넣어줬음.
        invocation에 proceed라고 target이 이미 있음.
         */
        Object result = invocation.proceed();// preceed가 알아서 타겟을 찾아서 실행해줌

        long endTime = System.currentTimeMillis();
        long resultTime = endTime-startTime;
        log.info("TimeProxy 종료 resultTime={}", resultTime);
        return result;
    }
}
