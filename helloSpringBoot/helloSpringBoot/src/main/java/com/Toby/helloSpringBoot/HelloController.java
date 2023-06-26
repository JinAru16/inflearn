package com.Toby.helloSpringBoot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(String name){
        SimpleHelloService helloService = new SimpleHelloService();
        return helloService.sayHello(Objects.requireNonNull(name)); //Objects.requireNonNull : null이 아니면 객체를 반환하고, null이면 예외를 터트림
    }
}
