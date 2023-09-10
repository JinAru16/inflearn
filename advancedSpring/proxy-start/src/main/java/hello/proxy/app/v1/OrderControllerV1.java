package hello.proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping// 스프링은 @Controller 또는 @RequestMapping이 있어야 스프링 컨트롤러로 인식함. 단 RequestMapping의 경우엔 Component Scan의 대상이 아님 따로 등록해서 써줘야 함.
@ResponseBody
public interface OrderControllerV1 {

    @GetMapping("/v1/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v1/no-log")
    String noLog();
}
