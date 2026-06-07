package com.railway.models;

public class Passenger {
    private int passengerId;
    private int bookingId;
    private String passengerName;
    private int passengerAge;
    private String gender;
    private String seatNumber;

    public Passenger(String passengerName, int passengerAge, String gender) {
        this.passengerName = passengerName;
        this.passengerAge = passengerAge;
        this.gender = gender;
    }

    public Passenger(int passengerId, int bookingId, String passengerName, int passengerAge, String gender, String seatNumber) {
        this.passengerId = passengerId;
        this.bookingId = bookingId;
        this.passengerName = passengerName;
        this.passengerAge = passengerAge;
        this.gender = gender;
        this.seatNumber = seatNumber;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public int getPassengerAge() {
        return passengerAge;
    }

    public String getGender() {
        return gender;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return String.format("%s (Age: %d) - Gender: %s - Seat: %s", passengerName, passengerAge, gender, seatNumber);
    }
}
