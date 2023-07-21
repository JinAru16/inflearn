package com.Toby.helloSpringBoot;
import com.Toby.Config.autoconfig.MySpringBootApplication;
import org.springframework.boot.SpringApplication;

@MySpringBootApplication
public class HelloSpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(HelloSpringBootApplication.class, args);
	}
}
