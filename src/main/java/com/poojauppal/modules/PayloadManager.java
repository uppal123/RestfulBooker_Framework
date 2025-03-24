package com.poojauppal.modules;

import com.google.gson.Gson;
import com.poojauppal.Pojos.Booking;
import com.poojauppal.Pojos.BookingResponse;
import com.poojauppal.Pojos.Bookingdates;

public class PayloadManager {
    // Convert Java Objects to JSON
        Gson gson;
    public String test_CreatePayloadBookingString() {
        Booking booking = new Booking();
        booking.setFirstname("Pooja");
        booking.setLastname("Uppal");
        booking.setTotalprice(2340);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2025-03-22");
        bookingdates.setCheckout("2025-03-25");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);
        //java object -> JSON
         gson = new Gson();
        String jsonBooking = gson.toJson(booking);
        System.out.println(jsonBooking);
        return jsonBooking;
    }
    //converting string -> java object
    public BookingResponse bookingresponseJava (String responseString) {
        gson = new Gson();
        BookingResponse bookingResponse = gson.fromJson(responseString, BookingResponse.class);
        return bookingResponse;
    }
}