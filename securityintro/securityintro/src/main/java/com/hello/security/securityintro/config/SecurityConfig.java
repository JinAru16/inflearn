package com.hello.security.securityintro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    // 인증 인가 설정.
    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        /*
        BCryptPasswordEncoder는 단방향 해시 함수로 랜덤 솔트를 이용해 비밀번호를 암호화한다.
        입력받은 비밀번호를 랜덤 솔트와 함께 저장하므로 Authentication 객체에 솔트 정보가 포함된다.
        JWT를 반환하고 다음 요청시에 해당 토큰에서 같은 솔트 정보를 꺼내 해쉬함수로 비교를 한다.
         */
        return new BCryptPasswordEncoder();
    }
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();// postman을 위해 개발기간만 꺼둠

        // form 인증 처리
        http.formLogin()
                .usernameParameter("userName")
                .passwordParameter("password")
                .loginProcessingUrl("/user/login")
                .successHandler((req, res, authentication) -> {
                    System.out.println("디버그 : 로그인성공 -> " );
                    res.sendRedirect("/");
                })
                .failureHandler((request, response, exception) -> {
                    System.out.println("디버그 : 로그인실패 ->"+exception.getMessage());
                });


        // 권한 필터 설정
        http.authorizeRequests((authorize) ->{
            //로그인이 필요한 페이지 설정
            authorize.antMatchers("/board").authenticated();
            authorize.antMatchers("/board/create").access("hasRole('ADMIN') or hasRole('MANAGER')")
                    .anyRequest().permitAll();
        });

        return http.build();
    }
}
