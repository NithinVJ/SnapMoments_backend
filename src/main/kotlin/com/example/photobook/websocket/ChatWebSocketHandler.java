package com.example.photobook.websocket;

import com.example.photobook.Entity.ChatMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.URI;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper;

    // ✅ Constructor injection of ObjectMapper (Spring will auto-wire it)
    public ChatWebSocketHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        URI uri = session.getUri();
        String token = null;
        if (uri != null) {
            String query = uri.getQuery(); // e.g., "token=user_1746281123091"
            if (query != null && query.startsWith("token=")) {
                token = query.substring(6);
            }
        }

        if (token == null || !isValid(token)) {
            session.close(CloseStatus.NOT_ACCEPTABLE.withReason("Invalid or missing token"));
            return;
        }

        sessions.put(token, session);
        System.out.println("✅ WebSocket connected for token: " + token);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.values().remove(session);
        System.out.println("❌ WebSocket connection closed: " + status);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Optional: log or parse inbound message
        System.out.println("📩 Received message: " + message.getPayload());
    }

    // ✅ Send message to a specific token
    public void sendMessageToUser(String token, ChatMessage message) {
        WebSocketSession session = sessions.get(token);
        if (session != null && session.isOpen()) {
            try {
                String payload = objectMapper.writeValueAsString(message);
                session.sendMessage(new TextMessage(payload));
                System.out.println("📤 Sent message to token: " + token);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("⚠️ No active session for token: " + token);
        }
    }

    // ✅ Stub for token validation – improve as needed
    private boolean isValid(String token) {
        return token != null && token.startsWith("user_");
    }
}
