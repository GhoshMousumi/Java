-- Create the database
CREATE DATABASE  airline_reservation;

-- Select the database
USE airline_reservation;

-- Table for flights
CREATE TABLE flight (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    flight_number VARCHAR(255) NOT NULL,
    source VARCHAR(255) NOT NULL,
    destination VARCHAR(255) NOT NULL,
    departure_time DATETIME NOT NULL,
    arrival_time DATETIME NOT NULL,
    economy_seats INT NOT NULL,
    business_seats INT NOT NULL,
    economy_price DOUBLE NOT NULL,
    business_price DOUBLE NOT NULL
);

-- Table for bookings
CREATE TABLE booking (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    flight_id BIGINT NOT NULL,
    passenger_name VARCHAR(255) NOT NULL,
    passenger_email VARCHAR(255) NOT NULL,
    travel_class ENUM('ECONOMY', 'BUSINESS') NOT NULL,
    seats INT NOT NULL,
    booking_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (flight_id) REFERENCES flight(id) ON DELETE CASCADE
);

-- Sample flight data
-- Insert more sample flights
INSERT INTO flight (flight_number, source, destination, departure_time, arrival_time, economy_seats, business_seats, economy_price, business_price) VALUES
('AI104', 'Kolkata', 'Delhi', '2025-08-16 08:00:00', '2025-08-16 10:30:00', 110, 22, 5200, 11800),
('AI105', 'Chennai', 'Hyderabad', '2025-08-16 14:00:00', '2025-08-16 15:20:00', 90, 18, 3500, 9500),
('AI106', 'Delhi', 'Bangalore', '2025-08-17 06:00:00', '2025-08-17 08:40:00', 100, 20, 5800, 12500),
('AI107', 'Mumbai', 'Delhi', '2025-08-17 09:30:00', '2025-08-17 11:50:00', 95, 19, 5000, 12000),
('AI108', 'Kolkata', 'Mumbai', '2025-08-18 05:45:00', '2025-08-18 08:30:00', 130, 28, 5600, 13000),
('AI109', 'Hyderabad', 'Bangalore', '2025-08-18 18:00:00', '2025-08-18 19:10:00', 85, 15, 3200, 8800),
('AI110', 'Delhi', 'Chennai', '2025-08-19 07:00:00', '2025-08-19 09:50:00', 105, 21, 6000, 13500),
('AI111', 'Pune', 'Delhi', '2025-08-19 12:30:00', '2025-08-19 14:50:00', 90, 18, 4800, 11800),
('AI112', 'Goa', 'Mumbai', '2025-08-20 10:00:00', '2025-08-20 11:15:00', 80, 15, 3000, 8500),
('AI113', 'Mumbai', 'Goa', '2025-08-20 16:00:00', '2025-08-20 17:10:00', 80, 15, 3000, 8500);


