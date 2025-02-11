package hello.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.BiConsumer;

@RestController
@RequiredArgsConstructor
public class OrderControllerV0 {
    private final OrderServiceV0 orderService;

    @GetMapping("/v0/request")
    public String request(String itemId){
        orderService.orderItem(itemId);




        return "ok";
    }
    public int[][] solution(int[] num_list, int n) {
        int size = num_list.length/n;
        int[][] answer = new int[size][n];

        answer[1][3] = 3;

    }


}
