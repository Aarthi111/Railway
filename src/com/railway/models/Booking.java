package com.railway.models;

import java.time.LocalDateTime;

public class Booking {
    private int bookingId;
    private int userId;
    private int routeId;
    private LocalDateTime bookingDate;
    private int numberOfSeats;
    private double totalFare;
    private String bookingStatus;

    public Booking(int userId, int routeId, int numberOfSeats, double totalFare) {
        this.userId = userId;
        this.routeId = routeId;
        this.numberOfSeats = numberOfSeats;
        this.totalFare = totalFare;
        this.bookingStatus = "CONFIRMED";
        this.bookingDate = LocalDateTime.now();
    }

    public Booking(int bookingId, int userId, int routeId, LocalDateTime bookingDate, int numberOfSeats, double totalFare, String bookingStatus) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.routeId = routeId;
        this.bookingDate = bookingDate;
        this.numberOfSeats = numberOfSeats;
        this.totalFare = totalFare;
        this.bookingStatus = bookingStatus;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public int getRouteId() {
        return routeId;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public double getTotalFare() {
        return totalFare;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String status) {
        this.bookingStatus = status;
    }

    @Override
    public String toString() {
        return String.format("Booking #%d | %d seats | Total: Rs.%.2f | Status: %s", bookingId, numberOfSeats, totalFare, bookingStatus);
    }
}
