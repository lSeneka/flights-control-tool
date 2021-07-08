package com.epam.flightscontroltool.controller.exception.airport;

public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException(Long id) {
        super("City with id " + id + " does not exist");
    }
}