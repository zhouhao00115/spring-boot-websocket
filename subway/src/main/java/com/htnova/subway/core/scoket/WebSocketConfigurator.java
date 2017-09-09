package com.htnova.subway.core.scoket;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnWebApplication
@Configuration
public class WebSocketConfigurator {

    @Bean
    public SessionConfigurator sessionConfigurator(){
        return new SessionConfigurator(); // This is just to get context
    }
}
