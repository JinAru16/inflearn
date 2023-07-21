package com.Toby.helloSpringBoot.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConfigurationTest {
    @Test
    @DisplayName("순수 자바는 레퍼런스가 다르면 다른걸로 판단한다.")
    void test(){
        //given
        MyConfig myConfig = new MyConfig();
        Bean1 bean1 = myConfig.bean1();
        Bean2 bean2 = myConfig.bean2();
        //when

        //then
        Assertions.assertThat(bean1.common).isNotEqualTo (bean2.common);
    }
    @Test
    @DisplayName("@Configuration이 개입하는 경우 레퍼런스가 달라도 두개가 같다고 나온다 -> 싱글톤 패턴이기 때문.")
    void configuration(){
        //given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(MyConfig.class);
        ac.refresh();

        //when
        Bean1 bean1 = ac.getBean(Bean1.class);
        Bean2 bean2 = ac.getBean(Bean2.class);

        //then
        Assertions.assertThat(bean1.common).isSameAs(bean2.common);
        System.out.println("Bean1 : " + bean1.toString());
        System.out.println("Bean2 : " + bean2.toString());
    }

    @Test
    @DisplayName("")
    void proxyCommonMethod(){
        //given
        MyConfigProxy myConfigProxy = new MyConfigProxy();

        Bean1 bean1 = myConfigProxy.bean1();
        Bean2 bean2 = myConfigProxy.bean2();
        //when

        //then
        Assertions.assertThat(bean1.common).isSameAs(bean2.common);
    }

    static class MyConfigProxy extends MyConfig{
        private Common common;
        @Override
        Common common() {
            if(this.common == null) this.common = super.common();

            return common;
        }
    }

    @Configuration()//proxyBeanMethods = false로 선언된 경우엔 Bean으로 등록된 오브젝트를 직접 호출하는건 위험하다고 경고줌.
    static class MyConfig{
        @Bean
        Common common() {
            return new Common();
        }

        @Bean
        Bean1 bean1(){
            return new Bean1(common());
        }

        @Bean
        Bean2 bean2(){
            return new Bean2(common());
        }
    }

    static class Bean1{
        private final Common common;

        public Bean1(Common common) {
            this.common = common;
        }
    }

    static class Bean2{
        private final Common common;

        public Bean2(Common common) {
            this.common = common;
        }
    }

    static class Common{

    }
}
