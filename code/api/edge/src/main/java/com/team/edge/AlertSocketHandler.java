package com.team.edge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AlertSocketHandler extends TextWebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(AlertSocketHandler.class);

    private static final ConcurrentHashMap<String, WebSocketSession> socketSessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        socketSessions.put(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        socketSessions.remove(session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {

    }

    public void notifyMessage(String message) {
        socketSessions.entrySet().parallelStream().forEach(t -> {
            try {
                t.getValue().sendMessage(new TextMessage(message));
            } catch (IOException e) {
                logger.error("send fail", e);
            }
        });
    }
}
