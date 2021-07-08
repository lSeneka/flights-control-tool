package com.epam.flightscontroltool.controller.exception.airport;

public class CountryNotFoundException extends RuntimeException {

    public CountryNotFoundException(Long id) {
        super("Country with id " + id + " does not exist");
    }
}