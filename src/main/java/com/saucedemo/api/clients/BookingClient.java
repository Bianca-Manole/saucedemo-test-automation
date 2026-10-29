package com.saucedemo.api.clients;

import com.saucedemo.api.ApiClient;
import com.saucedemo.api.models.Booking;
import io.restassured.response.Response;

import java.util.Map;

public class BookingClient extends ApiClient {
    private static final String PING = "/ping";
    private static final String BOOKINGS = "/booking";
    private static final String BOOKING_BY_ID = "/booking/{id}";

    public Response ping() {
        return request().get(PING);
    }

    public Response getBookingIds() {
        return request().get(BOOKINGS);
    }

    public Response getBookingIds(String firstname, String lastname) {
        return request()
                .queryParam("firstname", firstname)
                .queryParam("lastname", lastname)
                .get(BOOKINGS);
    }

    public Response getBooking(int id) {
        return request().pathParam("id", id).get(BOOKING_BY_ID);
    }

    public Response createBooking(Booking booking) {
        return request().body(booking).post(BOOKINGS);
    }

    public Response updateBooking(int id, Booking booking, String token) {
        return request(token).pathParam("id", id).body(booking).put(BOOKING_BY_ID);
    }

    public Response partialUpdateBooking(int id, Map<String, Object> fields, String token) {
        return request(token).pathParam("id", id).body(fields).patch(BOOKING_BY_ID);
    }

    public Response deleteBooking(int id, String token) {
        return request(token).pathParam("id", id).delete(BOOKING_BY_ID);
    }
}
