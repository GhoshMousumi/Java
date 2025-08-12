package com.airline.reservation.service;

import com.airline.reservation.model.Booking;
import com.airline.reservation.model.Flight;
import com.airline.reservation.repository.BookingRepository;
import com.airline.reservation.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class BookingService {
    @Autowired
    private FlightRepository flightRepo;
    @Autowired
    private BookingRepository bookingRepo;

    @Transactional
    public Booking createBooking(Long flightId, String name, String email, String phone, String travelClass, int seats) {
        Flight f = flightRepo.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        if (travelClass.equalsIgnoreCase("Economy")) {
            if (f.getEconomySeats() < seats) throw new RuntimeException("Not enough economy seats");
            f.setEconomySeats(f.getEconomySeats() - seats);
        } else {
            if (f.getBusinessSeats() < seats) throw new RuntimeException("Not enough business seats");
            f.setBusinessSeats(f.getBusinessSeats() - seats);
        }
        flightRepo.save(f);

        Booking b = new Booking();
        b.setFlight(f);
        b.setPassengerName(name);
        b.setEmail(email);
        b.setPhone(phone);
        b.setTravelClass(travelClass);
        b.setSeats(seats);
        double unitPrice = travelClass.equalsIgnoreCase("Economy") ? f.getEconomyPrice() : f.getBusinessPrice();
        b.setTotalPrice(unitPrice * seats);
        b.setBookingTime(LocalDateTime.now());

        return bookingRepo.save(b);
    }
}

