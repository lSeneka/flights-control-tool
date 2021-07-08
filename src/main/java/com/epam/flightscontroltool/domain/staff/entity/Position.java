package com.epam.flightscontroltool.domain.staff.entity;

public enum Position {

    PILOT("Pilot"),
    FLIGHT_ATTENDANT("Flight attendant"),
    ENGINEER("Engineer"),
    MECHANIC("Mechanic");

    private String title;

    Position(String title) {
        this.title = title;
    }
}
