package main.java.acko.Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Turf {
    private String turfId;
    private String name;
    private String location;
    private String sportId;
    private BigDecimal hourlyPrice;
    private boolean isActive;

    public Turf(String turfId, String name, String location, String sportId, BigDecimal hourlyPrice) {
        this.turfId = turfId;
        this.name = name;
        this.location = location;
        this.sportId = sportId;
        this.hourlyPrice = hourlyPrice;
        this.isActive = true;
    }

    public boolean isAvailable(LocalDateTime start, LocalDateTime end) {
        // This will be implemented with booking service integration
        return isActive;
    }

    public BigDecimal calculatePrice(int hours) {
        return hourlyPrice.multiply(BigDecimal.valueOf(hours));
    }

    public void activate() {
        this.isActive = true;
    }

    public void deactivate() {
        this.isActive = false;
    }
}