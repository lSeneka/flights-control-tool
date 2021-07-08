package com.epam.flightscontroltool.domain.airplane.entity;

public enum Manufacturer {

    AEROSTAR("Aerostar"),
    AIRBUS("Airbus"),
    BEECHCRAFT("Beechcraft"),
    BELL("Bell"),
    BOEING("Boeing"),
    BOMBARDIER("Bombardier"),
    CESSNA("Cessna"),
    DASSAULT("Dassault"),
    GULFSTREAM("Gulfstream"),
    HONDA_AIRCRAFT("Honda_aircraft"),
    LEONARDO("Leonardo"),
    PILATUS_AIRCRAFT("Pilatus_Aircraft");

    private String manufacturer;

    Manufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
