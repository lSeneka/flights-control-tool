package com.epam.flightscontroltool.controller.exception.airplane;

public class AirplaneNotFoundException extends RuntimeException {

    public AirplaneNotFoundException(Long id) {
        super("Airplane with id " + id + " does not exist");
    }
}