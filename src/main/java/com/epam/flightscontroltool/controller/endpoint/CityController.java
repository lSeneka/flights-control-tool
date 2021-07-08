package com.epam.flightscontroltool.controller.endpoint;

import com.epam.flightscontroltool.controller.dto.airport.CityDto;
import com.epam.flightscontroltool.service.airport.CityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    @ApiOperation(value = "Retrieving list of cities")
    @ApiResponses({
            @ApiResponse(code = 200, message = "List of cities"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<CityDto> getCities(
            @ApiParam("Country id") @RequestParam(value = "countryId", required = false) Long countryId,
            @ApiParam("Page number") @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @ApiParam("Page size") @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        log.info("Retrieving list of cities...");
        return cityService.getAll(countryId, page, size);
    }

    @ApiOperation(value = "Retrieving city details")
    @ApiResponses({
            @ApiResponse(code = 200, message = "City details"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public CityDto getCityById(
            @ApiParam("City id") @PathVariable(value = "id") Long id) {
        log.info("Retrieving details of city with id {}", id);
        return cityService.getCityDetailsById(id);
    }

    @ApiOperation(value = "Saving a city")
    @ApiResponses({
            @ApiResponse(code = 201, message = "A city was successfully saved"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public CityDto save(@Valid @RequestBody CityDto cityDto) {
        log.info("Saving a new city...");
        return cityService.save(cityDto);
    }

    @ApiOperation(value = "Updating a city")
    @ApiResponses({
            @ApiResponse(code = 200, message = "A city was successfully updated"),
            @ApiResponse(code = 404, message = "A city was not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public CityDto update(
            @ApiParam("City id") @PathVariable(value = "id") Long id,
            @Valid @RequestBody CityDto cityDto) {
        log.info("Updating a city with id {}...", id);
        return cityService.update(id, cityDto);
    }

    @ApiOperation(value = "Deleting a city")
    @ApiResponses({
            @ApiResponse(code = 200, message = "A city was successfully deleted"),
            @ApiResponse(code = 404, message = "A city was not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public void delete(
            @ApiParam("City id") @PathVariable(value = "id") Long id) {
        log.info("Fetching & deleting a city with id {}", id);
        cityService.delete(id);
    }
}
