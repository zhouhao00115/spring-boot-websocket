package com.htnova.subway.core.dao.impl;

import com.htnova.subway.core.dao.TokenDao;
import com.htnova.subway.core.model.TokenInfo;
import org.springframework.stereotype.Repository;

@Repository("TokenDao")
public class TokenInfoImpl implements TokenDao {
    @Override
    public TokenInfo getTokenInfoByKey(String key) {
        System.out.println("dao");
        return new TokenInfo(true, key);
    }
}
