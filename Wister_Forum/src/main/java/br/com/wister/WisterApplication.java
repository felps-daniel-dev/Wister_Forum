package br.com.wister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class WisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(WisterApplication.class, args);
    }

}
