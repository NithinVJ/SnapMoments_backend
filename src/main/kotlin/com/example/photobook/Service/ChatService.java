package com.example.photobook.Service;
import com.example.photobook.Entity.ChatMessage;
import com.example.photobook.Model.ChatMessageModel;
import com.example.photobook.Repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {

    private final ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public ChatMessage saveMessage(ChatMessageModel dto) {
        ChatMessage message = new ChatMessage();
        message.setBookingId(dto.getBookingId());
        message.setSenderToken(dto.getSenderToken());
        message.setSenderName(dto.getSenderName());
        message.setSenderRole(dto.getSenderRole());
        message.setRecipientToken(dto.getRecipientToken());
        message.setRecipientName(dto.getRecipientName());
        message.setRecipientRole(dto.getRecipientRole());
        message.setContent(dto.getContent());
        message.setTimestamp(LocalDateTime.now());

        message.setRead(false);
        return chatRepository.save(message);
    }

    public List<ChatMessage> getMessagesByBookingId(String bookingId) {
        return chatRepository.findByBookingIdOrderByTimestampAsc(bookingId);
    }

    public void markMessagesAsRead(String bookingId) {
        List<ChatMessage> messages = chatRepository.findByBookingIdOrderByTimestampAsc(bookingId);
        messages.forEach(msg -> msg.setRead(true));
        chatRepository.saveAll(messages);
    }
}
