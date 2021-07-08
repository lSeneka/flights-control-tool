package com.epam.flightscontroltool.controller.dto.airport;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CityDto {

    @ApiModelProperty(
            value = "city id",
            dataType = "long")
    @JsonProperty("id")
    private long id;

    @ApiModelProperty(
            value = "city name",
            dataType = "String",
            example = "Milan",
            required = true)
    @NotNull(message = "Name should be filled in")
    @JsonProperty("name")
    private String name;

    @ApiModelProperty(
            value = "country id",
            dataType = "long",
            required = true)
    @JsonProperty("countryId")
    private long countryId;
}
