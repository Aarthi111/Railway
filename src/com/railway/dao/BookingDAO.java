package com.railway.dao;

import com.railway.db.DatabaseConnection;
import com.railway.models.Booking;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    public int createBooking(Booking booking) {
        String sql = "INSERT INTO bookings (user_id, route_id, number_of_seats, total_fare) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, booking.getUserId());
            pstmt.setInt(2, booking.getRouteId());
            pstmt.setInt(3, booking.getNumberOfSeats());
            pstmt.setDouble(4, booking.getTotalFare());

            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error creating booking: " + e.getMessage());
        }
        return -1;
    }

    public Booking getBookingById(int bookingId) {
        String sql = "SELECT * FROM bookings WHERE booking_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookingId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Booking(
                        rs.getInt("booking_id"),
                        rs.getInt("user_id"),
                        rs.getInt("route_id"),
                        rs.getTimestamp("booking_date").toLocalDateTime(),
                        rs.getInt("number_of_seats"),
                        rs.getDouble("total_fare"),
                        rs.getString("booking_status")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving booking: " + e.getMessage());
        }
        return null;
    }

    public List<Booking> getUserBookings(int userId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE user_id = ? ORDER BY booking_date DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    bookings.add(new Booking(
                        rs.getInt("booking_id"),
                        rs.getInt("user_id"),
                        rs.getInt("route_id"),
                        rs.getTimestamp("booking_date").toLocalDateTime(),
                        rs.getInt("number_of_seats"),
                        rs.getDouble("total_fare"),
                        rs.getString("booking_status")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user bookings: " + e.getMessage());
        }
        return bookings;
    }

    public boolean cancelBooking(int bookingId) {
        String sql = "UPDATE bookings SET booking_status = 'CANCELLED' WHERE booking_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookingId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error cancelling booking: " + e.getMessage());
            return false;
        }
    }
}
