package com.imooc.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Tjl on 2019/4/16 14:57.
 */
@RestController
@Slf4j
public class ClientController {

    @Autowired//第二种方式
    private LoadBalancerClient loadBalancerClient;

    @Autowired//第三种方式
    private RestTemplate restTemplate;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        //1.第一种方式（直接使用restTemplate,url写死）
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:8080/msg", String.class);
        log.info("response={}", response);
        return response;
    }

    @GetMapping("/getProductMsg2")
    public String getProductMsg2() {
        //2.第二种方式（利用loadBalancerClient通过应用名获取url,然后再使用restTemplate)
        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
        String url = String.format("http://%s:%s/msg", serviceInstance.getHost(), serviceInstance.getPort());
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);log.info("response={}", response);
        return response;
    }
    @GetMapping("/getProductMsg3")
    public String getProductMsg3() {
        String response = restTemplate.getForObject("http://PRODUCT/msg", String.class);
        log.info("response={}",response);
        return response;
    }

}
