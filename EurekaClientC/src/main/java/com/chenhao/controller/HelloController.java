package com.chenhao.controller;

import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private Logger logger= Logger.getLogger(HelloController.class);

    @RequestMapping("/index")
    public String index(){
        logger.info("c----index-----");
        return "C----i want to earn much much money...";
    }
}
