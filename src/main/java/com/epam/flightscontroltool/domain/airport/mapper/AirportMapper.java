package com.epam.flightscontroltool.domain.airport.mapper;

import com.epam.flightscontroltool.controller.dto.airport.AirportDto;
import com.epam.flightscontroltool.domain.airport.entity.Airport;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AirportMapper {

    public static final AirportMapper INSTANCE = Mappers.getMapper(AirportMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "cityId", source = "city.id")
    public abstract AirportDto toAirportDto(Airport airport);

    @InheritInverseConfiguration
    public abstract Airport toAirport(AirportDto airportDto);
}
