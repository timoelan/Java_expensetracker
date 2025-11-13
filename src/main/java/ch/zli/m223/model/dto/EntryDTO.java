package ch.zli.m223.model.dto;

import java.time.LocalDateTime;

public class EntryDTO {
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;


    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public LocalDateTime getCheckout(){
        return checkOut;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }
}