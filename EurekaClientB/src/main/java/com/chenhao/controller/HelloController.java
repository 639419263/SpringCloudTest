package com.chenhao.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {
    private Logger logger= Logger.getLogger(HelloController.class);
    @Autowired
    private DiscoveryClient discoveryClient;
    @RequestMapping("/hello")
    public String hello(){
        return "B------nice to meet you !";
    }
    @RequestMapping("/index")
    public String index(){
        List<String> list=discoveryClient.getServices();
        return "B------"+JSON.toJSONString(list);
    }

    @ApiOperation(value="获取用户信息", notes="根据id来获取用户详细信息")
    @ApiImplicitParam(name="id", value="用户ID", required=true, dataType="String")
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public Map<String,String> getInfo(@PathVariable String id) {
        Map<String ,String> map = new HashMap<String, String>();
        map.put("name", "chenhao");
        map.put("age", "38");
        return map;
    }
}
