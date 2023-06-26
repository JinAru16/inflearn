package com.Toby.helloSpringBoot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(String name){

        return helloService.sayHello(Objects.requireNonNull(name)); //Objects.requireNonNull : null이 아니면 객체를 반환하고, null이면 예외를 터트림
    }
}
