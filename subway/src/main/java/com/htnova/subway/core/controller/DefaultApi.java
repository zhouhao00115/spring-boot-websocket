package com.htnova.subway.core.controller;

import com.htnova.subway.core.dao.TestDao;
import com.htnova.subway.core.mapper.TestMapper;
import com.htnova.subway.core.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DefaultApi {
    @RequestMapping
    public String index() {
        return "tomcat is running";
    }
}
