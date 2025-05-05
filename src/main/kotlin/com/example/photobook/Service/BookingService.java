package com.example.photobook.Service;
import com.example.photobook.Entity.BookingEntity;
import com.example.photobook.Entity.PhotographerEntity;
import com.example.photobook.Entity.UserEntity;
import com.example.photobook.Repository.BookingRepository;
import com.example.photobook.Repository.PhotographerRepository;
import com.example.photobook.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhotographerRepository photographerRepository;

    @Transactional
    public BookingEntity createBooking(BookingEntity booking) {
        // Validate and set relationships
        if (booking.getUser() != null && booking.getUser().getId() != null) {
            UserEntity user = userRepository.findById(booking.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            booking.setUser(user);
        }

        if (booking.getPhotographer() != null && booking.getPhotographer().getId() != null) {
            PhotographerEntity photographer = photographerRepository.findById(booking.getPhotographer().getId())
                    .orElseThrow(() -> new RuntimeException("Photographer not found"));
            booking.setPhotographer(photographer);
        }

        // Save first to get auto-generated ID
        BookingEntity savedBooking = bookingRepository.save(booking);

        // Generate Booking Code
        String customCode = "BK-" + String.format("%04d", savedBooking.getId());
        savedBooking.setBookingCode(customCode);

        // Save again with booking code
        return bookingRepository.save(savedBooking);
    }

    public List<BookingEntity> getAllBookings() {
        return bookingRepository.findAll();
    }

    public BookingEntity getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
    }

    public Optional<BookingEntity> getBookingByCode(String bookingCode) {
        return bookingRepository.findByBookingCode(bookingCode);
    }

    public List<BookingEntity> getBookingsByUser(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public List<BookingEntity> getBookingsByPhotographer(Long photographerId) {
        return bookingRepository.findByPhotographerId(photographerId);
    }

    public List<BookingEntity> getBookingsByStatus(String status) {
        return bookingRepository.findByStatus(status);
    }

    public List<BookingEntity> getBookingsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return bookingRepository.findByEventDateBetween(startDate, endDate);
    }

    @Transactional
    public BookingEntity updateBooking(Long id, BookingEntity bookingDetails) {
        BookingEntity booking = getBookingById(id);

        booking.setEventType(bookingDetails.getEventType());
        booking.setEventDate(bookingDetails.getEventDate());
        booking.setStartTime(bookingDetails.getStartTime());
        booking.setEndTime(bookingDetails.getEndTime());
        booking.setLocation(bookingDetails.getLocation());
        booking.setSelectedPackageId(bookingDetails.getSelectedPackageId());
        booking.setPackageName(bookingDetails.getPackageName());
        booking.setPrice(bookingDetails.getPrice());
        booking.setDescription(bookingDetails.getDescription());
        booking.setStatus(bookingDetails.getStatus());
        booking.setFullName(bookingDetails.getFullName());
        booking.setEmail(bookingDetails.getEmail());
        booking.setPhone(bookingDetails.getPhone());
        booking.setNotes(bookingDetails.getNotes());

        if (bookingDetails.getPhotographer() != null && bookingDetails.getPhotographer().getId() != null) {
            PhotographerEntity photographer = photographerRepository.findById(bookingDetails.getPhotographer().getId())
                    .orElseThrow(() -> new RuntimeException("Photographer not found"));
            booking.setPhotographer(photographer);
        }

        return bookingRepository.save(booking);
    }

    @Transactional
    public void deleteBooking(Long id) {
        BookingEntity booking = getBookingById(id);
        bookingRepository.delete(booking);
    }

    @Transactional
    public BookingEntity acceptBooking(Long bookingId) {
        BookingEntity booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));

        // Validate that booking is in pending status
        if (!"pending".equalsIgnoreCase(booking.getStatus())) {
            throw new RuntimeException("Only pending bookings can be accepted");
        }

        booking.setStatus("confirmed");
        return bookingRepository.save(booking);
    }

    @Transactional
    public BookingEntity declineBooking(Long bookingId) {
        BookingEntity booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));

        // Validate that booking is in pending status
        if (!"pending".equalsIgnoreCase(booking.getStatus())) {
            throw new RuntimeException("Only pending bookings can be declined");
        }

        booking.setStatus("declined");
        return bookingRepository.save(booking);
    }

}
