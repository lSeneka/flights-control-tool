package com.epam.flightscontroltool.controller.exception.airplane;

public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException(Long id) {
        super("Airplane company with id " + id + " does not exist");
    }
}