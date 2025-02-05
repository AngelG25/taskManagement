package com.portfolio;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class App {

    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
        log.info("SWAGGER: http://localhost:8080/swagger-ui/index.html");
    }
}
