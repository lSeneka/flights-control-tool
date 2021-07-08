package com.epam.flightscontroltool.controller.exception.flight;

public class FlightNotFoundException extends RuntimeException {

    public FlightNotFoundException(Long id) {
        super("Flight with id " + id + " does not exist");
    }
}
