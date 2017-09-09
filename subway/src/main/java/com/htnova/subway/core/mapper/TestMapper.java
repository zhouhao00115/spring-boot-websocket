package com.htnova.subway.core.mapper;

import com.htnova.subway.core.model.Test;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TestMapper {

    @Select("select 'Host','User' from user")
    List<Test> getAll();
}
