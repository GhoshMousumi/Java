package com.airline.reservation.controller;


import com.airline.reservation.model.Booking;
import com.airline.reservation.model.Flight;
import com.airline.reservation.repository.FlightRepository;
import com.airline.reservation.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller             // Spring MVC Controller for web requests
@RequestMapping("/booking")  // Base URL path for booking-related endpoints
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private FlightRepository flightRepository;

    // Show booking form page - GET request with flightId param
    @GetMapping("/book")
    public String showBookingForm(@RequestParam Long flightId, Model model) {
        Flight flight = flightRepository.findById(flightId).orElse(null);
        if (flight == null) {
            model.addAttribute("error", "Flight not found");
            return "error";  // a Thymeleaf template to display errors
        }
        model.addAttribute("flight", flight);
        return "book";  // Thymeleaf template name
    }

    // Process booking submission - POST request
    @PostMapping("/book")
    public String processBooking(
            @RequestParam Long flightId,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String travelClass,
            @RequestParam int seats,
            Model model) {

        try {
            Booking booking = bookingService.createBooking(flightId, name, email, phone, travelClass, seats);
            model.addAttribute("booking", booking);
            return "confirmation";  // show booking confirmation page
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            Flight flight = flightRepository.findById(flightId).orElse(null);
            model.addAttribute("flight", flight);
            return "book";  // back to booking form with error message
        }
    }
}

