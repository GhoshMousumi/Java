package com.airline.reservation.service;

import com.airline.reservation.model.Flight;
import com.airline.reservation.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService {
    @Autowired
    private FlightRepository repo;

    public List<Flight> search(String source, String dest, LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();
        return repo.findAll().stream()
                .filter(f -> f.getSource().equalsIgnoreCase(source)
                        && f.getDestination().equalsIgnoreCase(dest)
                        && !f.getDepartureTime().isBefore(start)
                        && f.getDepartureTime().isBefore(end))
                .collect(Collectors.toList());
    }
}

