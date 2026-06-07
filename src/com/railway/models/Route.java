package com.railway.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Route {
    private int routeId;
    private int trainId;
    private String sourceCity;
    private String destinationCity;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private int distanceKm;
    private double baseFare;
    private LocalDate travelDate;
    private int availableSeats;
    private Train train;

    public Route(int routeId, int trainId, String sourceCity, String destinationCity, LocalTime departureTime, LocalTime arrivalTime, int distanceKm, double baseFare, LocalDate travelDate, int availableSeats) {
        this.routeId = routeId;
        this.trainId = trainId;
        this.sourceCity = sourceCity;
        this.destinationCity = destinationCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.distanceKm = distanceKm;
        this.baseFare = baseFare;
        this.travelDate = travelDate;
        this.availableSeats = availableSeats;
    }

    public int getRouteId() {
        return routeId;
    }

    public int getTrainId() {
        return trainId;
    }

    public String getSourceCity() {
        return sourceCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public int getDistanceKm() {
        return distanceKm;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s on %s | Dept: %s, Arr: %s | Fare: Rs.%.2f | Seats: %d", sourceCity, destinationCity, travelDate, departureTime, arrivalTime, baseFare, availableSeats);
    }
}
