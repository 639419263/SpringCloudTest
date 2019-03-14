package com.feign.service.impl;

import com.feign.service.ShareService;
import org.springframework.stereotype.Component;

@Component
public class ShareServiceImpl implements ShareService {
    @Override
    public String index() {
        return "熔断机制发挥作用了，调不通";
    }
}
