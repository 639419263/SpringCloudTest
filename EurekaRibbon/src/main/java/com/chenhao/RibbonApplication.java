package com.chenhao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 实现客户端的负载均衡策略:路由的作用
 *  跟断路器整合在一起
 */

@EnableDiscoveryClient //服务提供者
@EnableEurekaClient //服务消费者
@EnableHystrix   //开启断路器的功能
@SpringBootApplication
public class RibbonApplication {
    @Bean
    @LoadBalanced//此注解的作用在于开启客户端负载均衡
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        System.out.println("-----负载均衡测试-------");
        SpringApplication.run(RibbonApplication.class, args);
    }
}
