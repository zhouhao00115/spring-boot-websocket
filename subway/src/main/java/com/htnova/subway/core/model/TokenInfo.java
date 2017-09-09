package com.htnova.subway.core.model;

public class TokenInfo {
    private boolean login;
    private String key;
    public TokenInfo(boolean login,String key) {
        this.login = login;
        this.key = key;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }
}
