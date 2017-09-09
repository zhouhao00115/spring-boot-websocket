package com.htnova.subway.core.dao;

import com.htnova.subway.core.mapper.TestMapper;
import com.htnova.subway.core.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


public interface TestDao {
    List<Test> getAll();
}
