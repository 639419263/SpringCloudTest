package com.feign.service;

import com.feign.service.impl.ShareServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "service-share",fallback = ShareServiceImpl.class)
public interface ShareService {
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    String index();


}
