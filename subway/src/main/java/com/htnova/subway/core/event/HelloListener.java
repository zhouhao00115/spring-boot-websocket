package com.htnova.subway.core.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@EnableAsync
@Component
public class HelloListener {
    @Async
    @EventListener(classes = {HelloEvent.class})
    public void listener(HelloEvent event) {
        System.out.println("into My Listener");
        HelloEvent myEvent = event;
        myEvent.print();
    }
}
