package com.htnova.subway.core.service.impl;

import com.htnova.subway.core.dao.TestDao;
import com.htnova.subway.core.model.Test;
import com.htnova.subway.core.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TestService")
public class TestServiceImpl implements TestService {
    @Autowired
    private TestDao dao;

    @Override
    public List<Test> getAll() {
        return dao.getAll();
    }


}
