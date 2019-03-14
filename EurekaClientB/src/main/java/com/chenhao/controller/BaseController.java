package com.chenhao.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    @RequestMapping("error-404")
    public String toPage404(){
        return "error/error-404    你要找的资源不存在，返回错误码404，请检查路径...";
    }
    @RequestMapping("error-400")
    public String toPage400(){
        return "error/error-400   你要找的资源不存在，返回错误码404，请检查路径... ";
    }
    @RequestMapping("error-500")
    public String toPage500(){
        return "error/error-500   服务器内部错误，返回错误码500，请检查...";
    }
}
