package hello.advanced.app.v3;


import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {

    private final LogTrace trace;

    public void save(String itemId){
        TraceStatus status = null;

        try{
            status = trace.begin("OrderRepository.save()");
            //저장로직
            if(itemId.equals("ex")){
                throw new IllegalStateException("예외 발생");
            }
            sleep(1000);
            trace.end(status);
        }
        catch(Exception e){
            trace.exception(status, e);
            throw e;//예외를 꼭 다시 던져주어야 함. 이걸 안 던져주면 정상 로그로 나옴. 로그는 애플리케이션 흐름에 영향을 주어선 안되기 때문
            //실제 예외가 터진걸 가장하고 만드는 거임.
        }


    }
    private void sleep(int millis){
        try{
            Thread.sleep(millis);
        }catch(InterruptedException e){
            e.printStackTrace();;
        }
    }
}
