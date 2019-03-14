package com.feign.service.impl;

import com.feign.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl  implements OrderService {
    @Override
    public String index() {
        return "server-order is out of work,please wait....";
    }

    @Override
    public String findAll() {
        return "SERVER-ORDER is stop working....";
    }
}
