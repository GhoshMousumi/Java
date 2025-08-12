package com.airline.reservation.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Flight flight;

    private String passengerName;
    private String email;
    private String phone;
    private String travelClass;
    private int seats;
    private double totalPrice;
    private LocalDateTime bookingTime;
}

