CREATE DATABASE IF NOT EXISTS railway_booking;
USE railway_booking;

CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE trains (
    train_id INT PRIMARY KEY AUTO_INCREMENT,
    train_number VARCHAR(20) UNIQUE NOT NULL,
    train_name VARCHAR(100) NOT NULL,
    train_type VARCHAR(50),
    total_seats INT NOT NULL,
    available_seats INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_train_number (train_number)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE routes (
    route_id INT PRIMARY KEY AUTO_INCREMENT,
    train_id INT NOT NULL,
    source_city VARCHAR(100) NOT NULL,
    destination_city VARCHAR(100) NOT NULL,
    departure_time TIME NOT NULL,
    arrival_time TIME NOT NULL,
    distance_km INT NOT NULL,
    base_fare DECIMAL(10, 2) NOT NULL,
    travel_date DATE NOT NULL,
    available_seats INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (train_id) REFERENCES trains(train_id) ON DELETE CASCADE,
    INDEX idx_route (source_city, destination_city, travel_date),
    UNIQUE KEY unique_route (train_id, travel_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE bookings (
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    route_id INT NOT NULL,
    booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    number_of_seats INT NOT NULL,
    total_fare DECIMAL(15, 2) NOT NULL,
    booking_status ENUM('CONFIRMED', 'CANCELLED') DEFAULT 'CONFIRMED',
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (route_id) REFERENCES routes(route_id) ON DELETE CASCADE,
    INDEX idx_user (user_id),
    INDEX idx_booking_status (booking_status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE passengers (
    passenger_id INT PRIMARY KEY AUTO_INCREMENT,
    booking_id INT NOT NULL,
    passenger_name VARCHAR(100) NOT NULL,
    passenger_age INT NOT NULL,
    gender VARCHAR(10),
    seat_number VARCHAR(10),
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id) ON DELETE CASCADE,
    INDEX idx_booking (booking_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO trains (train_number, train_name, train_type, total_seats, available_seats) VALUES
('12609', 'Chennai-Madurai Express', 'EXPRESS', 100, 100),
('12635', 'Madurai-Bangalore Express', 'EXPRESS', 120, 120),
('16311', 'Chennai-Bangalore Rajdhani', 'RAJDHANI', 80, 80),
('11019', 'Coimbatore-Chennai Local', 'LOCAL', 150, 150);

INSERT INTO routes (train_id, source_city, destination_city, departure_time, arrival_time, distance_km, base_fare, travel_date, available_seats) VALUES
(1, 'Chennai', 'Madurai', '10:30:00', '14:45:00', 185, 450.00, '2026-08-08', 100),
(1, 'Chennai', 'Madurai', '15:00:00', '19:15:00', 185, 450.00, '2026-08-09', 100),
(2, 'Madurai', 'Bangalore', '08:00:00', '18:30:00', 420, 780.00, '2026-08-10', 120),
(3, 'Chennai', 'Bangalore', '22:00:00', '06:30:00', 350, 850.00, '2026-08-11', 80),
(4, 'Coimbatore', 'Chennai', '06:00:00', '11:30:00', 140, 350.00, '2026-08-12', 150);
