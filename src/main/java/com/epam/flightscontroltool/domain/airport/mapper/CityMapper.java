package com.epam.flightscontroltool.domain.airport.mapper;

import com.epam.flightscontroltool.controller.dto.airport.CityDto;
import com.epam.flightscontroltool.domain.airport.entity.City;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CityMapper {

    public static final CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "countryId", source = "country.id")
    public abstract CityDto toCityDto(City city);

    @InheritInverseConfiguration
    @Mapping(target = "airports", ignore = true)
    public abstract City toCity(CityDto cityDto);
}
