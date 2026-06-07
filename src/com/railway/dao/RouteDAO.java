package com.railway.dao;

import com.railway.db.DatabaseConnection;
import com.railway.models.Route;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RouteDAO {

    public List<Route> searchRoutes(String source, String destination, LocalDate date) {
        List<Route> routes = new ArrayList<>();
        String sql = "SELECT * FROM routes WHERE source_city = ? AND destination_city = ? AND travel_date = ? AND available_seats > 0 ORDER BY departure_time";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, source);
            pstmt.setString(2, destination);
            pstmt.setDate(3, java.sql.Date.valueOf(date));

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Route route = new Route(
                        rs.getInt("route_id"),
                        rs.getInt("train_id"),
                        rs.getString("source_city"),
                        rs.getString("destination_city"),
                        rs.getTime("departure_time").toLocalTime(),
                        rs.getTime("arrival_time").toLocalTime(),
                        rs.getInt("distance_km"),
                        rs.getDouble("base_fare"),
                        rs.getDate("travel_date").toLocalDate(),
                        rs.getInt("available_seats")
                    );
                    routes.add(route);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching routes: " + e.getMessage());
        }
        return routes;
    }

    public Route getRouteById(int routeId) {
        String sql = "SELECT * FROM routes WHERE route_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, routeId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Route(
                        rs.getInt("route_id"),
                        rs.getInt("train_id"),
                        rs.getString("source_city"),
                        rs.getString("destination_city"),
                        rs.getTime("departure_time").toLocalTime(),
                        rs.getTime("arrival_time").toLocalTime(),
                        rs.getInt("distance_km"),
                        rs.getDouble("base_fare"),
                        rs.getDate("travel_date").toLocalDate(),
                        rs.getInt("available_seats")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving route: " + e.getMessage());
        }
        return null;
    }

    public boolean updateAvailableSeats(int routeId, int seatsToDeduct) {
        String sql = "UPDATE routes SET available_seats = available_seats - ? WHERE route_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, seatsToDeduct);
            pstmt.setInt(2, routeId);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating route seats: " + e.getMessage());
            return false;
        }
    }
}
