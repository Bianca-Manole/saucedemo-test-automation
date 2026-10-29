package com.saucedemo.api.models;

// Response body of POST /booking.
public record CreatedBooking(int bookingid, Booking booking) {
}
