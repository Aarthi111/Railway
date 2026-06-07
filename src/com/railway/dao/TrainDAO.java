package com.railway.dao;

import com.railway.db.DatabaseConnection;
import com.railway.models.Train;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainDAO {

    public List<Train> getAllTrains() {
        List<Train> trains = new ArrayList<>();
        String sql = "SELECT * FROM trains";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                trains.add(new Train(
                    rs.getInt("train_id"),
                    rs.getString("train_number"),
                    rs.getString("train_name"),
                    rs.getString("train_type"),
                    rs.getInt("total_seats"),
                    rs.getInt("available_seats")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving trains: " + e.getMessage());
        }
        return trains;
    }

    public Train getTrainById(int trainId) {
        String sql = "SELECT * FROM trains WHERE train_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, trainId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Train(
                        rs.getInt("train_id"),
                        rs.getString("train_number"),
                        rs.getString("train_name"),
                        rs.getString("train_type"),
                        rs.getInt("total_seats"),
                        rs.getInt("available_seats")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving train: " + e.getMessage());
        }
        return null;
    }

    public boolean updateAvailableSeats(int trainId, int seatsToDeduct) {
        String sql = "UPDATE trains SET available_seats = available_seats - ? WHERE train_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, seatsToDeduct);
            pstmt.setInt(2, trainId);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating seats: " + e.getMessage());
            return false;
        }
    }
}
