package com.epam.flightscontroltool.domain.airplane.mapper;

import com.epam.flightscontroltool.controller.dto.airplane.AirplaneDto;
import com.epam.flightscontroltool.domain.airplane.entity.Airplane;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AirplaneMapper {

    public static final AirplaneMapper INSTANCE = Mappers.getMapper(AirplaneMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "capacity", source = "capacity")
    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "manufacturer", source = "manufacturer")
    public abstract AirplaneDto toAirplaneDto(Airplane airplane);

    @InheritInverseConfiguration
    @Mapping(target = "staffMembers", ignore = true)
    public abstract Airplane toAirplane(AirplaneDto airplaneDto);
}
