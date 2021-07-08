package com.epam.flightscontroltool.domain.airport.mapper;

import com.epam.flightscontroltool.controller.dto.airport.CountryDto;
import com.epam.flightscontroltool.domain.airport.entity.Country;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CountryMapper {

    public static final CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    public abstract CountryDto toCountryDto(Country country);

    @InheritInverseConfiguration
    @Mapping(target = "cities", ignore = true)
    public abstract Country toCountry(CountryDto countryDto);

}
