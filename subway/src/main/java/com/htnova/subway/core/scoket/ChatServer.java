package com.htnova.subway.core.scoket;

import com.google.gson.Gson;
import com.htnova.subway.core.dao.TokenDao;
import com.htnova.subway.core.dao.impl.TokenInfoImpl;
import com.htnova.subway.core.model.Message;
import com.htnova.subway.core.model.TokenInfo;
import com.htnova.subway.core.runtime.LogManager;
import com.htnova.subway.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Scope("prototype")
@ServerEndpoint(value = "/socket", configurator = SessionConfigurator.class)
public class ChatServer {

    @Autowired
    private TokenDao tokenDao ;

    /**
     * 当前会话
     */
    static final Map<String, ChatServer> webSocketSet = new ConcurrentHashMap<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 用户名
     */
    private String key;

    /**
     *
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        // 单个会话对象保存
        this.session = session;
        this.key = (String) config.getUserProperties().get(SessionConfigurator.class.getSimpleName());
        if (StringUtil.isNullOrEmpty(key)) {
            LogManager.info(this.getClass(), "当前用户没有登录");
            throw new NullPointerException("当前用户没有登录");
        }
        TokenInfo tokenInfo = tokenDao.getTokenInfoByKey(key);
        if (!tokenInfo.isLogin()) {
            LogManager.info(this.getClass(), String.format("当前登录用户token异常[%s]", new Gson().toJson(tokenInfo)));
            throw new NullPointerException("当前登录用户token异常");
        }
        if (webSocketSet.containsKey(key)) {
            webSocketSet.put(key, this);
            LogManager.info(this.getClass(), String.format("重新登录了[%s]", new Gson().toJson(tokenInfo)));
            singleSend(key, new Message(key, "重新登录了"));
            return;
        }
        webSocketSet.put(key, this);
        singleSend(key, new Message(key, "欢迎登录"));
    }

    /**
     * 连接关闭调用的方法-与前端JS代码对应
     */
    @OnClose
    public void onClose() {
        System.out.println("连接关闭了");
        LogManager.info(this.getClass(), String.format("关闭当前是session[%s]", key));
        if (webSocketSet.containsKey(key)) {
            webSocketSet.remove(key);
        }
    }

    /**
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message) {
        System.out.println("成功收到了消息" + message);
        LogManager.info(this.getClass(), String.format("成功接收到消息[%s]", message));
        singleSend(key, new Message(key, "ok"));
    }

    /**
     * 发生错误时调用
     *
     * @param error
     */
    @OnError
    public void onError(Throwable error) {
        LogManager.error(this.getClass(), "websocket连接报错", error);
    }

    /**
     * 广播消息
     *
     * @param message
     */
    public void broadcast(Message message) {
        if (webSocketSet.size() == 0) return;
        // 获取当前所有的会话session
        List<String> list = new ArrayList<>();
        for (String key : webSocketSet.keySet()) {
            try {
                if (singleSend(key, message)) {
                    continue;
                }
                list.add(key);
            } catch (Exception e) {
                LogManager.error(this.getClass(), String.format("发送消息失败[%s][%s]", key, new Gson().toJson(message)), e);
            }
        }
        if (list.size() == 0) {
            return;
        }
        //清理掉报错的连接
        LogManager.info(this.getClass(), String.format("发送消息失败的连接有%s条,[%s]", list.size(), new Gson().toJson(list)));
        for (String key : list) {
            webSocketSet.remove(key);
        }
    }

    /**
     * 对特定用户发送消息 key 为指定用户的唯一识别码
     *
     * @param message
     */
    public boolean singleSend(String key, Message message) {
        if (!webSocketSet.containsKey(key)) {
            return false;
        }
        ChatServer server = webSocketSet.get(key);
        if (null == server.session) {
            return false;
        }
        server.session.getAsyncRemote().sendText(new Gson().toJson(message)); // 异步发送
        return true;
    }
}
