package com.epam.flightscontroltool.domain.flight;

import com.epam.flightscontroltool.controller.dto.flight.FlightDto;
import com.epam.flightscontroltool.domain.Flight;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class FlightMapper {

    public static final FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "airplaneId", source = "airplane.id")
    @Mapping(target = "departureAirportId", source = "departureAirport.id")
    @Mapping(target = "arrivalAirportId", source = "arrivalAirport.id")
    @Mapping(target = "departureDateTime", source = "departureDateTime")
    @Mapping(target = "arrivalDateTime", source = "arrivalDateTime")
    @Mapping(target = "basePrice", source = "basePrice")
    public abstract FlightDto toFlightDto(Flight flight);

    @InheritInverseConfiguration
    public abstract Flight toFlight(FlightDto flightDto);
}
