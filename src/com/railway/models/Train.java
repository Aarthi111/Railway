package com.railway.models;

public class Train {
    private int trainId;
    private String trainNumber;
    private String trainName;
    private String trainType;
    private int totalSeats;
    private int availableSeats;

    public Train(int trainId, String trainNumber, String trainName, String trainType, int totalSeats, int availableSeats) {
        this.trainId = trainId;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.trainType = trainType;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
    }

    public int getTrainId() {
        return trainId;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getTrainType() {
        return trainType;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return String.format("Train [%s] %s (%s) - Available: %d/%d seats", trainNumber, trainName, trainType, availableSeats, totalSeats);
    }
}
