package com.Toby.helloSpringBoot;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String hello(String name){
        if(name == null || name.length() == 0) throw new IllegalArgumentException();
        return helloService.sayHello(name); //Objects.requireNonNull : null이 아니면 객체를 반환하고, null이면 예외를 터트림

    }
}
