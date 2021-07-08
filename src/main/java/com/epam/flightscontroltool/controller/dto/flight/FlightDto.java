package com.epam.flightscontroltool.controller.dto.flight;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class FlightDto {

    @ApiModelProperty(
            value = "flight id",
            dataType = "long")
    @JsonProperty("id")
    private long id;

    @ApiModelProperty(
            value = "airplane id",
            dataType = "long",
            required = true)
    @NotNull(message = "Airplane id should be filled in")
    @JsonProperty("airplaneId")
    private long airplaneId;

    @ApiModelProperty(
            value = "departure airport id",
            dataType = "long",
            required = true)
    @JsonProperty("departureAirportId")
    private long departureAirportId;

    @ApiModelProperty(
            value = "arrival airport id",
            dataType = "long",
            required = true)
    @JsonProperty("arrivalAirportId")
    private long arrivalAirportId;

    @ApiModelProperty(
            value = "departure date and time",
            dataType = "String",
            required = true)
    @JsonProperty("departureDateTime")
    private String departureDateTime;

    @ApiModelProperty(
            value = "arrival date and time",
            dataType = "String",
            required = true)
    @JsonProperty("arrivalDateTime")
    private String arrivalDateTime;

    @ApiModelProperty(
            value = "base flight price",
            dataType = "number",
            required = true)
    @JsonProperty("basePrice")
    private BigDecimal basePrice; // TODO - not sure about type
}
