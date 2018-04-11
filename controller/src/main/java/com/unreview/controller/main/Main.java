package com.unreview.controller.main;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = ("com.unreview.*"))
@MapperScan("com.unreview.dao.com.unreview.dao")
public class Main {

    public static void main(String[] args)
    {

        SpringApplication.run(Main.class,args);
    }
}
