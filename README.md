# Railway Ticket Booking System

A console-based railway ticket booking application built with Java, JDBC, and MySQL with password hashing and input validation.

## Features

- User Registration with password hashing (SHA-256)
- Secure User Login
- Search trains by source, destination, and date
- Book tickets with fare calculation
- View booking history
- Cancel bookings with refund
- Update user profile
- Change password
- Input validation and error handling

## Requirements

- Java 8 or higher
- MySQL 5.7 or higher
- MySQL JDBC Driver (mysql-connector-java-8.0.33.jar)

## Project Structure

```
RailwayTicketBookingSystem/
├── src/
│   └── com/railway/
│       ├── db/
│       │   └── DatabaseConnection.java
│       ├── models/
│       │   ├── User.java
│       │   ├── Train.java
│       │   ├── Route.java
│       │   ├── Booking.java
│       │   └── Passenger.java
│       ├── dao/
│       │   ├── UserDAO.java
│       │   ├── TrainDAO.java
│       │   ├── RouteDAO.java
│       │   └── BookingDAO.java
│       ├── service/
│       │   ├── UserService.java
│       │   ├── BookingService.java
│       │   └── SearchService.java
│       ├── util/
│       │   ├── PasswordUtil.java
│       │   └── InputValidator.java
│       └── ui/
│           └── MainMenu.java
├── database/
│   └── database_schema.sql
└── lib/
    └── mysql-connector-java-8.0.33.jar
```

## Setup Instructions

### Step 1: Database Setup

1. Open MySQL
```bash
mysql -u root -p
```

2. Run the SQL schema
```bash
source database/database_schema.sql;
```

Or copy and paste the contents of `database/database_schema.sql` in MySQL.

### Step 2: MySQL JDBC Driver

1. Download MySQL JDBC Driver from: https://dev.mysql.com/downloads/connector/j/
2. Place `mysql-connector-java-8.0.33.jar` in the `lib/` folder

### Step 3: Compile the Project

```bash
javac -cp "lib/*" -d bin src/com/railway/*/*.java
```

### Step 4: Run the Application

```bash
java -cp "bin:lib/*" com.railway.ui.MainMenu
```

## Database Configuration

The application uses the following default configuration:
- Host: localhost
- Port: 3306
- Database: railway_booking
- Username: root
- Password: root

If your MySQL configuration is different, update the constants in `DatabaseConnection.java`:

```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/railway_booking";
private static final String DB_USER = "root";
private static final String DB_PASSWORD = "root";
```

## Usage

### Register a New User

1. Select option 1 from the main menu
2. Enter name, email, phone, password, and address
3. Password must have:
   - Minimum 8 characters
   - At least one uppercase letter
   - At least one lowercase letter
   - At least one digit

### Login

1. Select option 2 from the main menu
2. Enter email and password

### Search and Book Tickets

1. From user menu, select option 1
2. Enter source city, destination city, and travel date (yyyy-MM-dd)
3. Select a train from available options
4. Enter number of seats (1-8)
5. Booking will be confirmed with total fare

### View My Bookings

1. Select option 2 from user menu
2. All your bookings will be displayed

### Cancel Booking

1. Select option 3 from user menu
2. Enter booking ID
3. Booking will be cancelled and refund will be processed

### Update Profile

1. Select option 4 from user menu
2. Enter new name, phone, and address

### Change Password

1. Select option 5 from user menu
2. Enter new password (must meet strength requirements)

## Sample Test Data

The database comes with pre-loaded sample data:

**Trains:**
- 12609: Chennai-Madurai Express
- 12635: Madurai-Bangalore Express
- 16311: Chennai-Bangalore Rajdhani
- 11019: Coimbatore-Chennai Local

**Routes:**
- Chennai to Madurai
- Madurai to Bangalore
- Chennai to Bangalore
- Coimbatore to Chennai

**Travel Dates:** 2024-12-25 and 2024-12-26 (Update dates as needed)

## Security Features

- Passwords are hashed using SHA-256 with Base64 encoding
- Parameterized queries to prevent SQL injection
- Input validation for email, phone, date, and seats
- Secure authentication system
- No plain text password storage

## Validation Rules

- **Email:** Valid email format
- **Phone:** Exactly 10 digits
- **Name:** 3-100 characters
- **Password:** Min 8 chars, 1 uppercase, 1 lowercase, 1 digit
- **Seats:** 1-8 per booking
- **Age:** 5-120 years
- **Travel Date:** Must be in the future

## Architecture

The application follows a 3-layer architecture:

1. **UI Layer:** MainMenu.java - User interface
2. **Service Layer:** Business logic and validation
3. **DAO Layer:** Database operations
4. **Model Layer:** Entity classes
5. **Database Layer:** MySQL

## Error Handling

All database operations include try-catch blocks with appropriate error messages. The application handles:
- Invalid credentials
- Duplicate email registration
- Insufficient seats
- Invalid input data
- Database connection errors

## Future Enhancements

- Email notifications
- SMS notifications
- Admin panel
- Payment gateway integration
- Reporting and analytics
- Web interface

## License

This project is for educational purposes.

## Support

For issues or queries, please refer to the code documentation.
