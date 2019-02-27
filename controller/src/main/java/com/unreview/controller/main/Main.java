package com.unreview.controller.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = ("com.unreview.*"))
@MapperScan("com.unreview.dao")
public class Main {
    public static void main(String[] args)
    {
        SpringApplication.run(Main.class,args);
    }
}










































