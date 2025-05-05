package com.example.photobook.Repository;
import com.example.photobook.Entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findByBookingIdOrderByTimestampAsc(String bookingId);

    @Query("SELECT COUNT(m) FROM ChatMessage m WHERE m.recipientToken = :userToken AND m.isRead = false")
    Long countUnreadMessages(@Param("userToken") String userToken);

    @Modifying
    @Transactional
    @Query("UPDATE ChatMessage m SET m.isRead = true WHERE m.bookingId = :bookingId AND m.recipientToken = :userToken")
    void markMessagesAsRead(@Param("bookingId") String bookingId, @Param("userToken") String userToken);

    List<ChatMessage> findBySenderTokenAndRecipientTokenOrderByTimestampAsc(String senderToken, String recipientToken);
}