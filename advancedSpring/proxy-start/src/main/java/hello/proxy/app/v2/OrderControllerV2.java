package hello.proxy.app.v2;

import hello.proxy.app.v1.OrderServiceV1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RequestMapping// RequestMapping은 컴포넌트 스캔의 대상이 아니라서 쓰는거임. 우리는 @Bean을 수동등록 해서 쓰고싶어서 이렇게 쓰는것.
@ResponseBody
public class OrderControllerV2 {
    private final OrderServiceV2 orderService;

    public OrderControllerV2(OrderServiceV2 orderService) {
        this.orderService = orderService;
    }

    @GetMapping("v2/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @GetMapping("v2/no-log")
    public String noLog() {
        return "ok";
    }
}
