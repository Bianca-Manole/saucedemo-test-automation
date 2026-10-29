package com.saucedemo.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saucedemo.utils.Reader;

// Null fields are left out of the request body, so incomplete bookings can be sent on purpose.
@JsonInclude(JsonInclude.Include.NON_NULL)
public record Booking(String firstname,
                      String lastname,
                      Integer totalprice,
                      Boolean depositpaid,
                      BookingDates bookingdates,
                      String additionalneeds) {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static Booking fromTestData(String key) {
        return MAPPER.convertValue(Reader.entry("bookings", key), Booking.class);
    }

    public Booking withFirstname(String firstname) {
        return new Booking(firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds);
    }
}
