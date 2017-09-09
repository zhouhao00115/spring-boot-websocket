package com.htnova.subway.core.model;

import com.htnova.subway.core.util.DateUtil;

public class Message {
    private String key;
    private String info;
    private String dateTime;

    public Message(String key, String info) {
        this.key = key;
        this.info = info;
        this.dateTime = DateUtil.getCurrentDate(DateUtil.formatDate_01);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
