package com.railway.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputValidator {

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("^[0-9]{10}$");
    }

    public static boolean isValidName(String name) {
        return name != null && name.length() >= 3 && name.length() <= 100;
    }

    public static boolean isValidAge(int age) {
        return age >= 5 && age <= 120;
    }

    public static boolean isValidSeats(int seats) {
        return seats >= 1 && seats <= 8;
    }

    public static LocalDate parseDate(String dateStr) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateStr, formatter);
    }

    public static boolean isFutureDate(LocalDate date) {
        return date.isAfter(LocalDate.now());
    }

    public static int getIntInput() {
        try {
            return Integer.parseInt(System.console().readLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
