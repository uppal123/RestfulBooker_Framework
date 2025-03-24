package com.poojauppal.modules;

import com.google.gson.Gson;
import com.poojauppal.Pojos.*;

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

    public String setauthPayload() {
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        gson = new Gson();
        String jsonPayloadstring = gson.toJson(auth);
        System.out.println("Json set to the-> " +jsonPayloadstring);
        return jsonPayloadstring;
    }

    //Json to java
    public String getTokenFromJson(String tokenResponse) {
        gson = new Gson();
        TokenResponse tokenResponse1 = gson.fromJson(tokenResponse, TokenResponse.class);
        return tokenResponse1.getToken().toString();

    }
}