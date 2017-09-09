package com.htnova.subway.core.rabbitmq;

import com.htnova.subway.core.util.DateUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello " + DateUtil.getCurrentDate(DateUtil.formatDate_01);
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }
}