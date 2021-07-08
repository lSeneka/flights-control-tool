package com.epam.flightscontroltool.domain.staff.mapper;

import com.epam.flightscontroltool.controller.dto.staff.StaffMemberDto;
import com.epam.flightscontroltool.domain.staff.entity.StaffMember;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class StaffMemberMapper {

    public static final StaffMemberMapper INSTANCE = Mappers.getMapper(StaffMemberMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "position", source = "position") // TODO: to check and convert enum to String if necessary
    @Mapping(target = "airplaneId", source = "airplane.id")
    public abstract StaffMemberDto toStaffMemberDto(StaffMember staffMember);

    @InheritInverseConfiguration
    public abstract StaffMember toStaffMember(StaffMemberDto staffMemberDto);
}
