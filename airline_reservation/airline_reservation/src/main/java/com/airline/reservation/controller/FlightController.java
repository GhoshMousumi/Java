package com.airline.reservation.controller;

import com.airline.reservation.model.Booking;
import com.airline.reservation.model.Flight;
import com.airline.reservation.repository.FlightRepository;
import com.airline.reservation.service.BookingService;
import com.airline.reservation.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class FlightController {
    @Autowired
    private FlightService flightService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private FlightRepository flightRepo;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam String source, @RequestParam String destination,
                         @RequestParam String date, Model model) {
        LocalDate d = LocalDate.parse(date);
        List<Flight> flights = flightService.search(source, destination, d);
        model.addAttribute("flights", flights);
        return "results";
    }

    @GetMapping("/book")
    public String bookPage(@RequestParam Long flightId, Model model) {
        Flight flight = flightRepo.findById(flightId).orElse(null);
        model.addAttribute("flight", flight);
        return "book";
    }

    @PostMapping("/book")
    public String doBook(@RequestParam Long flightId, @RequestParam String name,
                         @RequestParam String email, @RequestParam String phone,
                         @RequestParam String travelClass, @RequestParam int seats, Model model) {
        Booking b = bookingService.createBooking(flightId, name, email, phone, travelClass, seats);
        model.addAttribute("booking", b);
        return "confirmation";
    }
}

