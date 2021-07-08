package com.epam.flightscontroltool.domain;

import com.epam.flightscontroltool.domain.airplane.entity.Airplane;
import com.epam.flightscontroltool.domain.airport.entity.Airport;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "airplane_id")
    private Airplane airplane;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;

    private LocalDateTime departureDateTime;

    private LocalDateTime arrivalDateTime;

    private BigDecimal basePrice;
}
