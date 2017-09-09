package com.htnova.subway.core.event;


import com.htnova.subway.core.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MyPubisher{
    @Autowired
    private ApplicationContext applicationContext;

    //事件发布方法
    public void pushListener(String msg) {
        System.out.println(msg);
        applicationContext.publishEvent(new HelloEvent(new Test()));
    }
}
