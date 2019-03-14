package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *  C服务-注册服务到服务中心
 */
@EnableEurekaClient
@SpringBootApplication
public class ClientApplicationC {
    public static void main(String[] args){
        System.out.println("---eureka   ClientApplication    C--start----");
        SpringApplication.run(ClientApplicationC.class,args);
    }
}
