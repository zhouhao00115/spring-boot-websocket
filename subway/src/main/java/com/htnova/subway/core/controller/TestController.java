package com.htnova.subway.core.controller;

import com.htnova.subway.core.model.Test;
import com.htnova.subway.core.runtime.LogManager;
import com.htnova.subway.core.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestService service;

    @GetMapping("/info")
    public String getTest() {
        List<Test> testList = service.getAll();
        for (Test test : testList) {
            System.out.println(test.getHost() + "---" + test.getUser());
        }
        return "success";
    }
}
