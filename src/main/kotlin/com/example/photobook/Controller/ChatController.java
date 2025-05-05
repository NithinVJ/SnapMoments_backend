package com.example.photobook.Controller;

import com.example.photobook.Entity.ChatMessage;
import com.example.photobook.Model.ChatMessageModel;
import com.example.photobook.Service.ChatService;
import com.example.photobook.websocket.ChatWebSocketHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;
    private final ChatWebSocketHandler webSocketHandler;

    public ChatController(ChatService chatService, ChatWebSocketHandler webSocketHandler) {
        this.chatService = chatService;
        this.webSocketHandler = webSocketHandler;
    }

    @PostMapping("/send")
    public ResponseEntity<ChatMessage> sendMessage(@RequestBody ChatMessageModel dto) {
        ChatMessage saved = chatService.saveMessage(dto);
        webSocketHandler.sendMessageToUser(dto.getRecipientToken(), saved);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/history/{bookingId}")
    public ResponseEntity<List<ChatMessage>> getChatByBooking(@PathVariable String bookingId) {
        return ResponseEntity.ok(chatService.getMessagesByBookingId(bookingId));
    }

    @PutMapping("/mark-read/{bookingId}")
    public ResponseEntity<Void> markAsRead(@PathVariable String bookingId) {
        chatService.markMessagesAsRead(bookingId);
        return ResponseEntity.ok().build();
    }
}

