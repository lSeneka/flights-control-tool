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
public class CompanyDto {

    @ApiModelProperty(
            value = "airplane company id",
            dataType = "long")
    @JsonProperty("id")
    private long id;

    @ApiModelProperty(
            value = "airplane company name",
            dataType = "String",
            example = "Turkish Airlines",
            required = true)
    @NotNull(message = "Name should be filled in")
    @JsonProperty("name")
    private String name;

}
