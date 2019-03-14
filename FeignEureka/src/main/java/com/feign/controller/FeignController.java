package com.feign.controller;

import com.feign.service.OrderService;
import com.feign.service.ShareService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class FeignController {
    private static Logger logger= Logger.getLogger(FeignController.class);
    @Autowired
    private ShareService shareService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/share/index")
    public String index(){
        logger.info("----index-----");
        return shareService.index();
    }
    @RequestMapping("/order/index")
    public String hello(){
        logger.info("----hello-----");
        return orderService.index();
    }

    @RequestMapping("/user/findAll.do")
    public String findAll(){
        logger.info("----findAll-----");
        return orderService.findAll();
    }
}
