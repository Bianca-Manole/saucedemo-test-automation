package com.saucedemo.base;

import com.saucedemo.api.clients.AuthClient;
import com.saucedemo.api.clients.BookingClient;
import com.saucedemo.api.models.AuthResponse;
import com.saucedemo.api.models.Booking;
import com.saucedemo.api.models.CreatedBooking;
import com.saucedemo.api.models.Credentials;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseApiTest {
    protected final AuthClient authClient = new AuthClient();
    protected final BookingClient bookingClient = new BookingClient();

    private final List<Integer> createdBookingIds = new ArrayList<>();
    private String token;

    // Every booking a test creates is deleted afterwards, so tests leave no data behind.
    @AfterEach
    public void deleteCreatedBookings() {
        for (int id : createdBookingIds) {
            bookingClient.deleteBooking(id, token());
        }
    }

    protected String token() {
        if (token == null) {
            token = authClient.createToken(Credentials.fromConfig()).as(AuthResponse.class).token();
        }
        return token;
    }

    // Setup helper: creates a booking the test needs as a precondition.
    protected CreatedBooking createBooking(Booking booking) {
        Response response = bookingClient.createBooking(booking);
        if (response.statusCode() != 200) {
            throw new IllegalStateException("Could not create test booking: "
                    + response.statusCode() + " " + response.asString());
        }
        CreatedBooking created = response.as(CreatedBooking.class);
        deleteAfterTest(created.bookingid());
        return created;
    }

    protected void deleteAfterTest(int bookingId) {
        createdBookingIds.add(bookingId);
    }
}
