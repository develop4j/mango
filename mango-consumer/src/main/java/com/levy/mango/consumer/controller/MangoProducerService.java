package com.levy.mango.consumer.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "mango-producer",fallback = MangoProducerHystrix.class) //要调用的服务名
public interface MangoProducerService {

    @RequestMapping("/hello")
    public String hello();
}
