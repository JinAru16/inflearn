package hello.advanced.app.v3;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId) {

        TraceStatus status = null;
        try{
            status = trace.begin("OrderService.orderItem()");
            orderRepository.save(itemId);
            trace.end(status);
        }
        catch(Exception e){
            trace.exception(status, e);
            throw e;//예외를 꼭 다시 던져주어야 함. 이걸 안 던져주면 정상 로그로 나옴. 로그는 애플리케이션 흐름에 영향을 주어선 안되기 때문
            //실제 예외가 터진걸 가장하고 만드는 거임.
        }

    }
}
