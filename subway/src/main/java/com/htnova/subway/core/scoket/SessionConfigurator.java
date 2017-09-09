package com.htnova.subway.core.scoket;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import java.util.List;
import java.util.Map;

@Configuration
public class SessionConfigurator extends ServerEndpointConfig.Configurator implements ApplicationContextAware {

    private static volatile BeanFactory context;

    /* (non-Javadoc)
     * @see javax.websocket.server.ServerEndpointConfig.Configurator#modifyHandshake(javax.websocket.server.ServerEndpointConfig, javax.websocket.server.HandshakeRequest, javax.websocket.HandshakeResponse)
	 */
    @Override
    public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
        //获取连接中携带的key值
        Map<String, List<String>> param = request.getParameterMap();
        String key = "";
        if (param.containsKey("key") && param.get("key").size() > 0) {
            key = param.get("key").get(0);
            System.out.println(key);
        }
        config.getUserProperties().put(SessionConfigurator.class.getSimpleName(), key);
    }

    @Override
    public <T> T getEndpointInstance(Class<T> clazz) throws InstantiationException {
        return context.getBean(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SessionConfigurator.context = applicationContext;
    }
}
