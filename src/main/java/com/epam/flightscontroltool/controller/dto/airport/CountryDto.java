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
public class CountryDto {

    @ApiModelProperty(
            value = "country id",
            dataType = "long")
    @JsonProperty("id")
    private long id;

    @ApiModelProperty(
            value = "country name",
            dataType = "String",
            example = "USA",
            required = true)
    @NotNull(message = "Name should be filled in")
    @JsonProperty("name")
    private String name;

}
