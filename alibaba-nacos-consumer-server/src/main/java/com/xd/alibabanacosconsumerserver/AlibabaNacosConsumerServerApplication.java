package com.xd.alibabanacosconsumerserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @RestController 表示控制层
 * @EnableDiscoveryClient 开启服务注册发现功能
 */
@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class AlibabaNacosConsumerServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaNacosConsumerServerApplication.class, args);
    }


    @Autowired
    LoadBalancerClient loadBalancerClient;

    @GetMapping("/echo/{name}")
    public String test(@PathVariable("name") String name) {
        // 通过spring cloud client中的负载均衡接口选取服务提供节点实现接口调用
        // serviceId为spring.application.name
        ServiceInstance serviceInstance = loadBalancerClient.choose("provider-service");
        String url = serviceInstance.getUri() + "/echo/" + name;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        return "from: " + url + ",return: " + result;
    }
}
