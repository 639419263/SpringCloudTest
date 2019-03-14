package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 服务B--发送服务到服务注册中心
 */
@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.chenhao.mapper")
public class ClientApplicationA {
    public static void main(String[] args){
        System.out.println("---eureka   ClientApplication    A--start----");
        SpringApplication.run(ClientApplicationA.class,args);
    }
}
