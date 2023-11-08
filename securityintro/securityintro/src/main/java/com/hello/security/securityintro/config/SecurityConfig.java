package com.hello.security.securityintro.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Base64;

@Configuration
@Slf4j
@EnableWebSecurity(debug = true)
@Getter
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

//        //로그인폼 방법
//        http.formLogin()
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .loginPage("/login")//클라이언트에서 보는 로그인 주소
//                .loginProcessingUrl("/signin")// 로그인 처리를 받는 api의 url
//                .defaultSuccessUrl("/board");

        // 권한 필터 설정
        http.authorizeRequests((authorize) ->{
            //로그인이 필요한 페이지 설정
            authorize.antMatchers("/signin").permitAll();
            authorize.antMatchers("/board").permitAll();
            authorize.antMatchers("/board/create").authenticated();
            authorize.antMatchers("/board/update").authenticated();
            authorize.antMatchers("/admin/**").access("hasRole('ADMIN') or hasRole('MANAGER')");
        });

        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    private SecretKey serializedSecretKey;
    @Value("${custom.jwt.secretKey}")
    private String secretKeyPlain;

    @Value("${custom.jwt.accessTokenExpirationMin:30}")
    private int accessTokenExpirationMin;

    @Value("${custom.jwt.refreshTokenExpirationMin:180}")
    private int refreshTokenExpirationMin;

    // transform plain secretKey to serializedSecretKey
    private SecretKey _getSecretKey(){
        String keyBase64Encoded = Base64.getEncoder().encodeToString(secretKeyPlain.getBytes());
        return Keys.hmacShaKeyFor(keyBase64Encoded.getBytes());
    }

    // 시크릿 키를 반환하는 method
    public SecretKey getSecretKey() {
        if (serializedSecretKey == null) serializedSecretKey = _getSecretKey();
        return serializedSecretKey;
    }

}
