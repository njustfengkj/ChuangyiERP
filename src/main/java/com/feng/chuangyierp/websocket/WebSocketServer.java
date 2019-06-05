package com.feng.chuangyierp.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket/{sid}")
@Component
public class WebSocketServer {
    private static int onlineCount=0;
    private static ConcurrentHashMap<String,WebSocketServer> webSocketSet=new ConcurrentHashMap<>();
    private Session session;
    private String sid="";
    @OnOpen
    public void onOpen(Session session, @PathParam("sid")String sid){
        this.session=session;
        webSocketSet.put(sid,this);
        addOnlineCount();
        this.sid=sid;
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @OnClose
    public void onClose(Session session,@PathParam("sid")String sid){
        webSocketSet.remove("sid");
        subOnlineCount();
        try {
            sendtoUser("已断开连接",sid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @OnMessage
    public void onMessage(String message, Session session){
            //可以自己约定字符串内容，比如 内容|0 表示信息群发，内容|X 表示信息发给id为X的用户
            String sendMessage = message.split("[|]")[0];
            String sendUserId = message.split("[|]")[1];
            try {
                if(sendUserId.equals("0"))
                    sendtoAll(sendMessage);
                else
                    sendtoUser(sendMessage,sendUserId);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
    public void sendtoUser(String message,String sendUserId) throws IOException {
        if (webSocketSet.get(sendUserId) != null) {
            if(!sid.equals(sendUserId))
                webSocketSet.get(sendUserId).sendMessage( "用户" + sid + "发来消息：" + " <br/> " + message);
            else
                webSocketSet.get(sendUserId).sendMessage(message);
        } else {
            //如果用户不在线则返回不在线信息给自己
            sendtoUser("当前用户不在线",sid);
        }
    }

    /**
     * 群发自定义消息
     * */
    public void sendtoAll(String message) throws IOException {
        for (String key : webSocketSet.keySet()) {
            try {
                webSocketSet.get(key).sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
