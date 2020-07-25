package com.levy.mango.consumer;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@EnableFeignClients
@EnableDiscoveryClient //将微服务注册到服务发现组件上。
@SpringBootApplication
public class MangoConsumerApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
     * 此配置是为了服务监控而配置，与服务容错本身无关
     * ServletRegistrationBean 是因为springboot的默认路径本身不是“/hystrix.stream”
     * 只要在自己项目里配置上下文的servlet可以了
     * @return
     */
    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
//        List mappingList = new ArrayList();
//        mappingList.add("/hystrix.stream");
//        registrationBean.setUrlMappings(mappingList);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;

    }

    public static void main(String[] args) {
        SpringApplication.run(MangoConsumerApplication.class, args);
    }

}
