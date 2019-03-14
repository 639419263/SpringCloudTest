package com.chenhao.controller;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于跳转jsp页面的
 */
@Controller
@RequestMapping("/front")
public class FrontPageController {
    private static Logger logger= Logger.getLogger(FrontPageController.class);
    //    @RequestMapping("/index.jsp")
//    public String index(){
//         return "index";
//    }
    @RequestMapping("/templatePage")
    public String templatePage(ModelMap model){
        logger.info("----A---templatePage---");
        model.addAttribute("name","小卿竹是个可爱的xiaobabay");
        return "templates";
    }
    }
