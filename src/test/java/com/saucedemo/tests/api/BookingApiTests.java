package com.saucedemo.tests.api;

import com.saucedemo.api.models.Booking;
import com.saucedemo.api.models.BookingId;
import com.saucedemo.api.models.CreatedBooking;
import com.saucedemo.base.BaseApiTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

public class BookingApiTests extends BaseApiTest {

    private final Booking booking = Booking.fromTestData("default");

    @Test
    public void apiIsUp() {
        Response response = bookingClient.ping();

        Assertions.assertEquals(201, response.statusCode(), "Health check should return 201");
    }

    @Test
    public void createBookingReturnsIdAndSameData() {
        Response response = bookingClient.createBooking(booking);

        Assertions.assertEquals(200, response.statusCode(), "Creating a booking should return 200");
        CreatedBooking created = response.as(CreatedBooking.class);
        deleteAfterTest(created.bookingid());
        Assertions.assertTrue(created.bookingid() > 0, "A booking id should be generated");
        Assertions.assertEquals(booking, created.booking(), "Created booking should echo the data that was sent");
    }

    @Test
    public void createdBookingCanBeRetrievedById() {
        int id = createBooking(booking).bookingid();

        Response response = bookingClient.getBooking(id);

        Assertions.assertEquals(200, response.statusCode(), "Getting an existing booking should return 200");
        Assertions.assertEquals(booking, response.as(Booking.class), "Retrieved booking should match the created one");
    }

    @Test
    public void bookingResponseMatchesJsonSchema() {
        int id = createBooking(booking).bookingid();

        Response response = bookingClient.getBooking(id);

        assertThat("Booking response should match the booking JSON schema",
                response.asString(), matchesJsonSchemaInClasspath("schemas/booking-schema.json"));
    }

    @Test
    public void bookingsCanBeFilteredByName() {
        Booking uniqueBooking = booking.withFirstname("Api" + System.currentTimeMillis());
        int id = createBooking(uniqueBooking).bookingid();

        Response response = bookingClient.getBookingIds(uniqueBooking.firstname(), uniqueBooking.lastname());

        Assertions.assertEquals(200, response.statusCode(), "Filtering bookings should return 200");
        BookingId[] ids = response.as(BookingId[].class);
        Assertions.assertEquals(1, ids.length, "Exactly one booking should match the unique name");
        Assertions.assertEquals(id, ids[0].bookingid(), "The matching booking should be the one just created");
    }

    @Test
    public void bookingListIsNotEmpty() {
        createBooking(booking);

        Response response = bookingClient.getBookingIds();

        Assertions.assertEquals(200, response.statusCode(), "Listing bookings should return 200");
        Assertions.assertTrue(response.as(BookingId[].class).length > 0, "Booking list should not be empty");
    }

    @Test
    public void updateBookingReplacesAllFields() {
        int id = createBooking(booking).bookingid();
        Booking updated = Booking.fromTestData("updated");

        Response response = bookingClient.updateBooking(id, updated, token());

        Assertions.assertEquals(200, response.statusCode(), "Updating a booking should return 200");
        Assertions.assertEquals(updated, bookingClient.getBooking(id).as(Booking.class),
                "Stored booking should contain all the new values");
    }

    @Test
    public void partialUpdateChangesOnlyGivenFields() {
        int id = createBooking(booking).bookingid();

        Response response = bookingClient.partialUpdateBooking(id,
                Map.of("totalprice", 999, "additionalneeds", "Dinner"), token());

        Assertions.assertEquals(200, response.statusCode(), "Partially updating a booking should return 200");
        Booking stored = bookingClient.getBooking(id).as(Booking.class);
        Assertions.assertEquals(999, stored.totalprice(), "Total price should be updated");
        Assertions.assertEquals("Dinner", stored.additionalneeds(), "Additional needs should be updated");
        Assertions.assertEquals(booking.firstname(), stored.firstname(), "First name should stay unchanged");
        Assertions.assertEquals(booking.bookingdates(), stored.bookingdates(), "Booking dates should stay unchanged");
    }

    @Test
    public void deleteBookingRemovesIt() {
        int id = createBooking(booking).bookingid();

        Response response = bookingClient.deleteBooking(id, token());

        // Restful Booker answers 201 Created on a successful delete.
        Assertions.assertEquals(201, response.statusCode(), "Deleting a booking should return 201");
        Assertions.assertEquals(404, bookingClient.getBooking(id).statusCode(), "Deleted booking should no longer exist");
    }

    @Test
    public void getNonExistingBookingReturnsNotFound() {
        Response response = bookingClient.getBooking(999_999_999);

        Assertions.assertEquals(404, response.statusCode(), "Unknown booking id should return 404");
    }

    @Test
    public void updateWithoutTokenIsForbidden() {
        int id = createBooking(booking).bookingid();

        Response response = bookingClient.updateBooking(id, Booking.fromTestData("updated"), null);

        Assertions.assertEquals(403, response.statusCode(), "Update without a token should return 403");
        Assertions.assertEquals(booking, bookingClient.getBooking(id).as(Booking.class), "Booking should be unchanged");
    }

    @Test
    public void deleteWithoutTokenIsForbidden() {
        int id = createBooking(booking).bookingid();

        Response response = bookingClient.deleteBooking(id, null);

        Assertions.assertEquals(403, response.statusCode(), "Delete without a token should return 403");
        Assertions.assertEquals(200, bookingClient.getBooking(id).statusCode(), "Booking should still exist");
    }

    @Test
    public void createBookingWithMissingFieldsIsRejected() {
        Booking incomplete = new Booking(booking.firstname(), null, null, null, null, null);

        Response response = bookingClient.createBooking(incomplete);

        // Restful Booker answers 500 here; a stricter API would return 400.
        Assertions.assertTrue(response.statusCode() >= 400,
                "Booking with missing required fields should be rejected, got " + response.statusCode());
    }
}
