package com.levy.mango.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignHelloController {
    @Autowired
//    @Qualifier("mango-producer")
    private MangoProducerService mangoProducerService;

    @RequestMapping("/feign/call")
    public String call(){
        //像调用本地服务一样
        return mangoProducerService.hello();
    }
}
