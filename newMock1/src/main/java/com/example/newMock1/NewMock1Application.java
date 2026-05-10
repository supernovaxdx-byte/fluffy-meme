package com.example.newMock1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class NewMock1Application {
    public static void main(String[] args) {
        SpringApplication.run(NewMock1Application.class, args);
    }
}
