package com.htnova.subway.core.dao;

import com.htnova.subway.core.model.TokenInfo;

public interface TokenDao {
    TokenInfo getTokenInfoByKey(String key);
}
