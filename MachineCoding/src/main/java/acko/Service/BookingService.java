package main.java.acko.Service;

import main.java.acko.Models.Booking;
import main.java.acko.Models.Turf;
import main.java.acko.Models.User;
import main.java.acko.Service.BookingRepository;
import main.java.acko.Service.TurfRepository;
import main.java.acko.Service.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookingService {
    private final BookingRepository bookingRepository;
    private final TurfRepository turfRepository;
    private final UserRepository userRepository;

    public BookingService(BookingRepository bookingRepository,
                          TurfRepository turfRepository,
                          UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.turfRepository = turfRepository;
        this.userRepository = userRepository;
    }

    public Booking createBooking(BookingRequest request) {
        if (!validateBookingRequest(request)) {
            throw new IllegalArgumentException("Invalid booking request");
        }

        if (checkForOverlappingBookings(request.getTurfId(),
                request.getStartTime(),
                request.getEndTime())) {
            throw new IllegalStateException("Booking slot not available");
        }

        Turf turf = turfRepository.findById(request.getTurfId())
                .orElseThrow(() -> new IllegalArgumentException("Turf not found"));

        int hours = request.getDurationHours();
        Booking booking = new Booking(
                UUID.randomUUID().toString(),
                request.getUserId(),
                request.getTurfId(),
                request.getStartTime(),
                request.getEndTime(),
                turf.calculatePrice(hours)
        );

        return bookingRepository.save(booking);
    }

    public boolean cancelBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        booking.cancel();
        bookingRepository.save(booking);
        return true;
    }

    public List<TimeSlot> getAvailableSlots(String turfId, LocalDate date) {
        List<Booking> existingBookings = bookingRepository.findByTurfAndDate(turfId, date);
        // Implementation to generate available time slots
        // This is a simplified version
        return generateTimeSlots(date, existingBookings);
    }

    private boolean validateBookingRequest(BookingRequest request) {
        return request.getUserId() != null &&
                request.getTurfId() != null &&
                request.getStartTime() != null &&
                request.getEndTime() != null &&
                request.getStartTime().isBefore(request.getEndTime());
    }

    private boolean checkForOverlappingBookings(String turfId,
                                                LocalDateTime start,
                                                LocalDateTime end) {
        List<Booking> overlappingBookings =
                bookingRepository.findOverlappingBookings(turfId, start, end);
        return !overlappingBookings.isEmpty();
    }
}