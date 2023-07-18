package com.Toby.helloSpringBoot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {
    @Test
    void simpleHelloService(){
        SimpleHelloService helloService = new SimpleHelloService();

        String ret = helloService.sayHello("test");
        Assertions.assertThat(ret).isEqualTo("Hello test");

    }
    @Test
    @DisplayName("HelloDecorator가 동작하는지 테스트한다.")
    void helloDecorator(){
        //given
        HelloDecorator decorator = new HelloDecorator(name -> name);
        //when
        String ret = decorator.sayHello("Test");
        //then
        Assertions.assertThat(ret).isEqualTo("*Test*");
    }
}
