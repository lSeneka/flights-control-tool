package com.epam.flightscontroltool.domain.airplane.mapper;

import com.epam.flightscontroltool.controller.dto.airplane.CompanyDto;
import com.epam.flightscontroltool.domain.airplane.entity.Company;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CompanyMapper {

    public static final CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    public abstract CompanyDto toCompanyDto(Company airplaneCompany);

    @InheritInverseConfiguration
    @Mapping(target = "airplanes", ignore = true)
    public abstract Company toCompany(CompanyDto companyDto);
}