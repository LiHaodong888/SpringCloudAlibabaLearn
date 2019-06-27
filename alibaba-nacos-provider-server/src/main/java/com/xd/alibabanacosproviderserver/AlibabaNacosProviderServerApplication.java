package com.xd.alibabanacosproviderserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController 表示控制层
 * @EnableDiscoveryClient 开启服务注册发现功能
 */
@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class AlibabaNacosProviderServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlibabaNacosProviderServerApplication.class, args);
	}

	@GetMapping("/echo/{name}")
	public String echo(@PathVariable String name) {
		return "hello " + name;
	}
}
