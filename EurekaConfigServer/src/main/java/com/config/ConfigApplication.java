package com.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer   //开启config-server
@SpringBootApplication
public class ConfigApplication {
    public static void main(String[] args){
        System.out.println("------config  server-----------");
        SpringApplication.run(ConfigApplication.class,args);
    }
}
