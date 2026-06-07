package com.railway.ui;

import com.railway.models.User;
import com.railway.models.Booking;
import com.railway.models.Route;
import com.railway.service.UserService;
import com.railway.service.BookingService;
import com.railway.service.SearchService;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private UserService userService = new UserService();
    private BookingService bookingService = new BookingService();
    private SearchService searchService = new SearchService();
    private Scanner scanner = new Scanner(System.in);
    private User currentUser;

    public void start() {
        System.out.println("\n========== RAILWAY TICKET BOOKING SYSTEM ==========");
        System.out.println("==================================================\n");

        boolean running = true;
        while (running) {
            if (currentUser == null) {
                running = displayLoginMenu();
            } else {
                running = displayUserMenu();
            }
        }

        System.out.println("\nThank you for using Railway Ticket Booking System!");
        scanner.close();
    }

    private boolean displayLoginMenu() {
        System.out.println("\n1. Register");
        System.out.println("2. Login");
        System.out.println("0. Exit");
        System.out.print("\nEnter choice: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                registerUser();
                return true;
            case 2:
                loginUser();
                return true;
            case 0:
                return false;
            default:
                System.out.println("Invalid choice!");
                return true;
        }
    }

    private boolean displayUserMenu() {
        System.out.println("\n========== WELCOME, " + currentUser.getName().toUpperCase() + " ==========");
        System.out.println("1. Search & Book Tickets");
        System.out.println("2. View My Bookings");
        System.out.println("3. Cancel Booking");
        System.out.println("4. Update Profile");
        System.out.println("5. Change Password");
        System.out.println("6. Logout");
        System.out.print("\nEnter choice: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                searchAndBook();
                break;
            case 2:
                viewMyBookings();
                break;
            case 3:
                cancelBooking();
                break;
            case 4:
                updateProfile();
                break;
            case 5:
                changePassword();
                break;
            case 6:
                currentUser = null;
                System.out.println("✓ Logged out successfully!");
                break;
            default:
                System.out.println("Invalid choice!");
        }
        return true;
    }

    private void registerUser() {
        System.out.println("\n========== REGISTER ==========");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter phone (10 digits): ");
        String phone = scanner.nextLine();

        System.out.print("Enter password (min 8 chars, 1 uppercase, 1 lowercase, 1 digit): ");
        String password = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        if (userService.registerUser(name, email, phone, password, address)) {
            System.out.println("✓ Registration successful! Please login.");
        } else {
            System.out.println("✗ Registration failed!");
        }
    }

    private void loginUser() {
        System.out.println("\n========== LOGIN ==========");
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        currentUser = userService.loginUser(email, password);
        if (currentUser != null) {
            System.out.println("✓ Login successful!");
        } else {
            System.out.println("✗ Invalid credentials!");
        }
    }

    private void searchAndBook() {
        System.out.println("\n========== SEARCH & BOOK ==========");
        System.out.print("Enter source city: ");
        String source = scanner.nextLine().trim();

        System.out.print("Enter destination city: ");
        String destination = scanner.nextLine().trim();

        System.out.print("Enter travel date (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine().trim();

        List<Route> routes = searchService.searchTrains(source, destination, dateStr);

        if (routes != null && !routes.isEmpty()) {
            System.out.println("\n========== AVAILABLE TRAINS ==========");
            for (int i = 0; i < routes.size(); i++) {
                System.out.println((i + 1) + ". " + routes.get(i));
            }

            System.out.print("\nSelect train (0 to cancel): ");
            int choice = getIntInput();

            if (choice > 0 && choice <= routes.size()) {
                Route selectedRoute = routes.get(choice - 1);

                System.out.print("Enter number of seats: ");
                int seats = getIntInput();

                bookingService.bookTicket(currentUser.getUserId(), selectedRoute.getRouteId(), seats);
            }
        }
    }

    private void viewMyBookings() {
        System.out.println("\n========== MY BOOKINGS ==========");
        List<Booking> bookings = bookingService.getUserBookings(currentUser.getUserId());

        if (bookings.isEmpty()) {
            System.out.println("No bookings found!");
        } else {
            for (Booking booking : bookings) {
                System.out.println(booking);
            }
        }
    }

    private void cancelBooking() {
        System.out.println("\n========== CANCEL BOOKING ==========");
        System.out.print("Enter booking ID: ");
        int bookingId = getIntInput();

        bookingService.cancelBooking(bookingId);
    }

    private void updateProfile() {
        System.out.println("\n========== UPDATE PROFILE ==========");
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter new address: ");
        String address = scanner.nextLine();

        currentUser.setName(name);
        currentUser.setPhone(phone);
        currentUser.setAddress(address);

        if (userService.updateProfile(currentUser)) {
            System.out.println("✓ Profile updated!");
        } else {
            System.out.println("✗ Update failed!");
        }
    }

    private void changePassword() {
        System.out.println("\n========== CHANGE PASSWORD ==========");
        System.out.print("Enter old password: ");
        String oldPassword = scanner.nextLine();

        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();

        if (userService.changePassword(currentUser.getUserId(), oldPassword, newPassword)) {
            System.out.println("✓ Password changed!");
        } else {
            System.out.println("✗ Change password failed!");
        }
    }

    private int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        MainMenu menu = new MainMenu();
        menu.start();
    }
}
