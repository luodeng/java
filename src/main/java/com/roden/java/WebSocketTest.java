package com.roden.java;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket/{uid}")
public class WebSocketTest {
    // 用户和websocket的session映射
    private static Map<String, Session> sessions = Collections.synchronizedMap(new HashMap<String, Session>());

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("uid") String uid)
            throws IOException, InterruptedException {
        /*
         * int count = 0; while (count < 3) { Thread.sleep(1000);
         * session.getBasicRemote().sendText(message + ":" + count); count++; }
         */
    	session.getUserProperties().put("", "");
        session.getBasicRemote().sendText("我对大家说:" + message);
        for (Entry<String, Session> entry : sessions.entrySet()) {
            // entry.getValue().getAsyncRemote().sendText(message);
            if (!entry.getKey().equals(session.getId())) {
                entry.getValue().getBasicRemote().sendText(session.getId() + ":" + message);
            }
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("uid") String uid) {
        System.out.println("Client connected:" + session.getId());
        sessions.put(session.getId(), session);
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Connection closed:" + session.getId());
        sessions.remove(session.getId());
        // session.close();
    }
}