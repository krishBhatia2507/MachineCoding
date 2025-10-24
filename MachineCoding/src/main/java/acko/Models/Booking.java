package main.java.acko.Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Booking {
    private String bookingId;
    private String userId;
    private String turfId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BookingStatus status;
    private BigDecimal totalAmount;

    public Booking(String bookingId, String userId, String turfId, LocalDateTime startTime,
                   LocalDateTime endTime, BigDecimal totalAmount) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.turfId = turfId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalAmount = totalAmount;
        this.status = BookingStatus.PENDING;
    }

    public int getDurationHours() {
        return (int) ChronoUnit.HOURS.between(startTime, endTime);
    }

    public boolean isOverlapping(Booking other) {
        return startTime.isBefore(other.endTime) && endTime.isAfter(other.startTime);
    }

    public void confirm() {
        this.status = BookingStatus.CONFIRMED;
    }

    public void cancel() {
        this.status = BookingStatus.CANCELLED;
    }

    public enum BookingStatus {
        PENDING,
        CONFIRMED,
        CANCELLED
    }

}