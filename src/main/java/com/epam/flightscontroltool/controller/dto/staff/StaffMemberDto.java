package com.epam.flightscontroltool.controller.dto.staff;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class StaffMemberDto {

    @ApiModelProperty(
            value = "staff member id",
            dataType = "long")
    @JsonProperty("id")
    private long id;

    @ApiModelProperty(
            value = "staff member first name",
            dataType = "String",
            example = "Marta",
            required = true)
    @NotNull(message = "First name should be filled in")
    @JsonProperty("firstName")
    private String firstName;

    @ApiModelProperty(
            value = "staff member surname",
            dataType = "String",
            example = "Viarenchykava",
            required = true)
    @NotNull(message = "Surname should be filled in")
    @JsonProperty("surname")
    private String surname;

    @ApiModelProperty(
            value = "staff member position",
            dataType = "String",
            example = "Engineer")
    @JsonProperty("position")
    private String position;

    @ApiModelProperty(
            value = "airplane id",
            dataType = "long")
    @JsonProperty("airplaneId")
    private long airplaneId;

}
