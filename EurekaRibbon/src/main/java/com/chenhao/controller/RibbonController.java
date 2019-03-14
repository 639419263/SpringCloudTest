package com.chenhao.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonController {
    private Logger logger= Logger.getLogger(RibbonController.class);
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String index() {
        String result=restTemplate.getForEntity("http://SERVICE-ORDER/index", String.class).getBody();
        logger.info("请求成功----"+result);
        return result;
    }
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public String findAll() {
        String result=restTemplate.getForEntity("http://SERVICE-ORDER/user/findAll.do", String.class).getBody();
        logger.info("请求成功----"+result);
        return result;
    }

    @RequestMapping(value = "/share", method = RequestMethod.GET)
    public String shareIndex(){
        String result=restTemplate.getForEntity("http://SERVICE-SHARE/index", String.class).getBody();
        logger.info("请求成功----"+result);
        return result;
    }

    /**
     * 这里给server-share(A服务)添加一个断路器的功能，看下如果A服务调不通会怎样
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallbackMsg")
    public String hiService(String name) {
        return restTemplate.getForObject("http://SERVICE-SHARE/index?name="+name,String.class);
    }

    /**
     * 断路器fallback的执行逻辑
     * @return
     */
    public String fallbackMsg(String name) {
        logger.info("断路器--------Sorry! The server is stop,please wait some minutes...");
        return "断路器起作用了，Sorry! The server is stop,please wait some minutes...name==="+name;
    }





}
