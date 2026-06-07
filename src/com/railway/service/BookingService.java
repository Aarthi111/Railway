package com.railway.service;

import com.railway.dao.BookingDAO;
import com.railway.dao.RouteDAO;
import com.railway.models.Booking;
import com.railway.models.Route;
import com.railway.util.InputValidator;
import java.util.List;

public class BookingService {
    private BookingDAO bookingDAO = new BookingDAO();
    private RouteDAO routeDAO = new RouteDAO();

    public int bookTicket(int userId, int routeId, int numberOfSeats) {

        if (!InputValidator.isValidSeats(numberOfSeats)) {
            System.out.println("Invalid number of seats! (1-8 allowed)");
            return -1;
        }

        Route route = routeDAO.getRouteById(routeId);
        if (route == null) {
            System.out.println("Route not found!");
            return -1;
        }

        if (route.getAvailableSeats() < numberOfSeats) {
            System.out.println("Not enough seats available!");
            return -1;
        }

        double totalFare = route.getBaseFare() * numberOfSeats;

        Booking booking = new Booking(userId, routeId, numberOfSeats, totalFare);
        int bookingId = bookingDAO.createBooking(booking);

        if (bookingId != -1) {
            routeDAO.updateAvailableSeats(routeId, numberOfSeats);
            System.out.println("✓ Booking successful! Booking ID: " + bookingId);
            System.out.printf("✓ Total Fare: Rs.%.2f%n", totalFare);
        } else {
            System.out.println("✗ Booking failed!");
        }

        return bookingId;
    }

    public List<Booking> getUserBookings(int userId) {
        return bookingDAO.getUserBookings(userId);
    }

    public boolean cancelBooking(int bookingId) {
        Booking booking = bookingDAO.getBookingById(bookingId);
        if (booking == null) {
            System.out.println("Booking not found!");
            return false;
        }

        if ("CANCELLED".equals(booking.getBookingStatus())) {
            System.out.println("Booking already cancelled!");
            return false;
        }

        if (bookingDAO.cancelBooking(bookingId)) {
            routeDAO.updateAvailableSeats(booking.getRouteId(), -booking.getNumberOfSeats());
            System.out.println("✓ Booking cancelled successfully!");
            System.out.printf("✓ Refund Amount: Rs.%.2f%n", booking.getTotalFare());
            return true;
        }

        return false;
    }

    public Booking getBooking(int bookingId) {
        return bookingDAO.getBookingById(bookingId);
    }
}
