package br.com.wister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching // habilitanfdo o cache na aplicação
public class WisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(WisterApplication.class, args);
    }

}
