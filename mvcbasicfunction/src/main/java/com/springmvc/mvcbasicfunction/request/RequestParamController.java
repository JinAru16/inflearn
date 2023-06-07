package com.springmvc.mvcbasicfunction.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }
    /*
    http://loaclhost:8080/request-param-v1?username=kim&age=20 이런식으로 파라미터를 보내면됨.
     */


    @ResponseBody// 위에 @RestController가 아닌 일반 @Controller로 선언되었다면 @ResponseBody 선언해주면 return값이 그냥 String이더라도 html파일을 찾지 않고 바로 http boyd에 리턴값을 바로 박아서 보내줌
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                               @RequestParam("age") int memberAge
                               ) {
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";

    }
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username, //넘어오는 파라미터의 이름이 같다면 이렇게 ("")를 뺄 수 있다.
                                 @RequestParam int age
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
        /*
        이런식으로 어노테이션까지 없애는건 좀 과하다는 영한님의 생각
        @RequestParam 이 있으면 명확하게 요청 파리미터에서 데이터를 읽는 다는 것을 알 수 있다.
         */
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username, //required = true로 하게되면 필수로 날아와야 할 값으로 지정 가능. 없으면 안됨.
            @RequestParam(required = false) Integer age) {// Integer는 객체타입이라 null이 가능한데 int는 안됨.
        log.info("username={}, age={}", username, age);
        return "ok";
        /*
        null과 ""는 다름.
        /request-param?username=
        파라미터 이름만 있고 값이 없는 경우 빈문자로 통과 필수라고 했는데 빈문자열로 들어오면 통과되니 주의하자 -> default value를 사용하여 막자
         */
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
        /*
        defaultValue 는 빈 문자의 경우에도 설정한 기본 값이 적용된다.
        /request-param-default?username=
         */
    }
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
        /*
        @RequestParam Map ,
        Map(key=value)
        @RequestParam MultiValueMap
        MultiValueMap(key=[value1, value2, ...] ex) (key=userIds, value=[id1, id2])
        파라미터의 값이 1개가 확실하다면 Map 을 사용해도 되지만, 그렇지 않다면 MultiValueMap 을 사용하자.
         */
    }


}
