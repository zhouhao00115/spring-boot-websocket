package com.htnova.subway.core;

import com.htnova.subway.core.event.MyPubisher;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
@SpringBootApplication
@Configuration
@MapperScan("com.htnova.subway.core.mapper")
public class SubwayApplication {

    @Autowired
    private MyPubisher pubisher;

    public static void main(String[] args) {
        SpringApplication.run(SubwayApplication.class, args);
    }

    @RequestMapping("/hello")
    public String index() {
        //观察者模式发布数据
        pubisher.pushListener("测试数据");
        return "Hello World xx";
    }
}
