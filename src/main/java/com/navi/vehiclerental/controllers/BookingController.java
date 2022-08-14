package com.navi.vehiclerental.controllers;

import com.navi.vehiclerental.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("bookingController")
public class BookingController {
    @Autowired
    BookingService bookingService;

    public String book(String branchId, String vehicleType, Integer startTime, Integer endTime){
        try {
            return String.valueOf(this.bookingService.book(branchId, vehicleType, startTime, endTime));
        } catch (Exception exception) {
            //exception.printStackTrace();
            return String.valueOf(-1);
        }
    }
}
