package com.epam.flightscontroltool.controller.dto.airplane;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AirplaneDto {

    @ApiModelProperty(
            value = "airplane id",
            dataType = "long")
    @JsonProperty("id")
    private long id;

    @ApiModelProperty(
            value = "airplane number",
            dataType = "String",
            example = "800-443-0478",
            required = true)
    @NotNull(message = "Number should be filled in")
    @JsonProperty("number")
    private String number;

    @ApiModelProperty(
            value = "number of seats (capacity)",
            dataType = "int",
            required = true)
    @JsonProperty("capacity")
    private int capacity;

    @ApiModelProperty(
            value = "airplane company id",
            dataType = "long",
            required = true)
    @JsonProperty("companyId")
    private long companyId;

    @ApiModelProperty(
            value = "airplane manufacturer name",
            dataType = "String",
            required = true)
    @JsonProperty("manufacturerName")
    private String manufacturer;
}
