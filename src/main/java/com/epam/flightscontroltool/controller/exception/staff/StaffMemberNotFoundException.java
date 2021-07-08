package com.epam.flightscontroltool.controller.exception.staff;

public class StaffMemberNotFoundException extends RuntimeException {

    public StaffMemberNotFoundException(Long id) {
        super("Staff member with id " + id + " does not exist");
    }
}