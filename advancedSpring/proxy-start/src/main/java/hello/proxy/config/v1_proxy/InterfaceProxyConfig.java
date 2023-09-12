package hello.proxy.config.v1_proxy;

import hello.proxy.app.v1.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {

    @Bean
    public OrderControllerV1 orderController(){
        return new OrderControllerV1Impl(orderService());
    }

    @Bean
    public OrderServiceV1 orderService(){
        return new OrderServiceV1Impl(orderRepository());
    }

    @Bean
    public OrderRepositoryV1 orderRepository(){
        return new OrderRepositoryV1Impl();
    }
}
