package com.example.boot.server.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;


@Component
@ServerEndpoint("/websocket/{userName}")
public class WebSocket {
    private Session session;
    private String userName;
    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    /**
     * 建立连接
     * @param userName 客户端标识
     * @param session 会话
     */
    @OnOpen
    public void onOpen(@PathParam("userName") String userName, Session session) {
        this.userName = userName;
        this.session = session;
        webSocketSet.add(this);

        // step1: 获取客户端需要初始化的所有消息
        // step2: 调用sendMessage(String message, String userName)方法发送初始化消息
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
    }

    /**
     * 收到客户端发来的消息
     * @param userName 客户端标识
     * @param message 消息
     */
    @OnMessage
    public void onMessage(@PathParam("userName") String userName, String message) {
        // 需要根据消息内容更改消息记录状态
    }

    /**
     * 群发消息
     * @param message 消息
     */
    public void sendMessage(String message) {
        for (WebSocket webSocket: webSocketSet) {
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 指定客户端发送消息
     * @param message 消息
     * @param userName 客户端标识
     */
    public void sendMessage(String message, String userName) {
        Session session = null;
        for (WebSocket webSocket: webSocketSet) {
           if (webSocket.userName.equals(userName)) {
               session = webSocket.session;
               break;
           }
        }
        if (session != null) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
