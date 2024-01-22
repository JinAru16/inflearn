package hello.login.web.login;

import hello.login.web.filter.LogFilter;
import hello.login.web.filter.LoginCheckFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;

@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean logFilter(){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter());
        filterRegistrationBean.setOrder(1);//filter가 체인으로 엮여있어 여러개가 들어갈 수 있음 그때 이제 순서 정해주는것.
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean loginCheckFilter(){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginCheckFilter());
        filterRegistrationBean.setOrder(2);//filter가 체인으로 엮여있어 여러개가 들어갈 수 있음 그때 이제 순서 정해주는것.
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }
}
