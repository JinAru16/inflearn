package com.Toby.helloSpringBoot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@UnitTest
@interface FastTest{
}


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Test
@interface UnitTest{
}
public class HelloServiceTest {
    @Test
    //@UnitTest
    void simpleHelloService(){
        SimpleHelloService helloService = new SimpleHelloService();

        String ret = helloService.sayHello("test");
        Assertions.assertThat(ret).isEqualTo("Hello test");

    }
    @Test
    @DisplayName("HelloDecorator 가 동작하는지 테스트한다.")
    void helloDecorator(){
        //given
        //HelloDecorator decorator = new HelloDecorator(name -> name);

//        HelloDecorator decorator = new HelloDecorator(new HelloService() {
//            @Override
//            public String sayHello(String name) {
//                return null;
//            }
//        });

        HelloDecorator decorator = new HelloDecorator(name -> name);

        //when
        String ret = decorator.sayHello("Test");
        //then
        Assertions.assertThat(ret).isEqualTo("*Test*");
    }
}
