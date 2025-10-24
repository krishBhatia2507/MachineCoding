package main.java.acko.Service;

import main.java.acko.Models.Booking;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository {
    Booking save(Booking booking);
    Optional<Booking> findById(String bookingId);
    List<Booking> findByUserId(String userId);
    List<Booking> findOverlappingBookings(String turfId, LocalDateTime start, LocalDateTime end);
    List<Booking> findByTurfAndDate(String turfId, LocalDate date);
}