package com.chenhao.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class HelloController {
    private Logger logger= LoggerFactory.getLogger(HelloController.class);
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/hello")
    public String hello(@RequestParam String id){
        logger.debug("这是一个debug级别");
        logger.info("这是一个级别");
        logger.warn("这是一个warn级别");
        logger.error("这是一个error级别");
        int msg=Integer.valueOf(id);
        return "A------nice to meet you !";
    }

    @RequestMapping("/index")
    public String index(){
        logger.info("这是一个index....");
        List<String> list=discoveryClient.getServices();
        return "A------"+JSON.toJSONString(list);
    }



}
