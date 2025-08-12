package com.airline.reservation.repository;

import com.airline.reservation.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findBySourceIgnoreCaseAndDestinationIgnoreCase(String source, String destination);
}

