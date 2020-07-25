package com.levy.mango.consumer.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class MangoProducerHystrix implements MangoProducerService{
    @Override
    @RequestMapping("/hello")
    public String hello() {
        return "Sorry ,hello service call failed";
    }
}
