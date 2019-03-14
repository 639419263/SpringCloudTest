package com.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient   //服务消费者
@EnableDiscoveryClient  //服务提供者
@EnableFeignClients   //开启负载均衡机制来监控
@SpringBootApplication
public class FeignNewApplication {
    public static void main(String[] args){
        System.out.println("----FeignNewApplication-----");
        SpringApplication.run(FeignNewApplication.class,args);
    }
}
