package com.feign.service;

import com.feign.service.impl.OrderServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "service-order",fallback = OrderServiceImpl.class)
public interface OrderService {
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    String index();

    @RequestMapping(value = "/user/findAll.do",method = RequestMethod.GET)
    String findAll();
}
