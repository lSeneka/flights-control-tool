package com.epam.flightscontroltool.controller.exception.airport;

public class AirportNotFoundException extends RuntimeException {

    public AirportNotFoundException(Long id) {
        super("Airport with id " + id + " does not exist");
    }
}