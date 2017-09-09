package com.htnova.subway.core.dao.impl;

import com.htnova.subway.core.dao.TestDao;
import com.htnova.subway.core.mapper.TestMapper;
import com.htnova.subway.core.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("TestDao")
public class TestDaoImpl implements TestDao {
    @Autowired
    private TestMapper mapper;

    @Override
    public List<Test> getAll() {
        return mapper.getAll();
    }
}
