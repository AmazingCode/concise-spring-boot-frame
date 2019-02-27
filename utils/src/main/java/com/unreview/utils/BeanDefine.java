package com.unreview.utils;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanDefine {
    @Bean("restTemplate")
    @LoadBalanced
    protected RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
