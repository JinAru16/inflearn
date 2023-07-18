package com.Toby.helloSpringBoot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HelloControllerTest {
    @Test
    @DisplayName("helloController를 테스트함")
    void helloController(){
        //given
        HelloController helloController = new HelloController(name -> name);
        //when
        String ret = helloController.hello("Test");
        //then
        Assertions.assertThat(ret).isEqualTo("Test");
    }
    @Test
    @DisplayName("helloController의 테스트에 실패한다")
    void failHelloController(){
        //given
        HelloController helloController = new HelloController(name -> name);
        //when
        Assertions.assertThatThrownBy(() -> {
            String ret = helloController.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> {
            String ret = helloController.hello("");
        }).isInstanceOf(IllegalArgumentException.class);

        //then
    }
}
