package com.htnova.subway.core.event;

import com.htnova.subway.core.model.Test;
import org.springframework.context.ApplicationEvent;

public class HelloEvent extends ApplicationEvent {
    public HelloEvent(Test test) {
        super(test);
        System.out.println("my Event");
    }

    public void print() {
        System.out.println("hello spring event[MyEvent]");
    }
}
