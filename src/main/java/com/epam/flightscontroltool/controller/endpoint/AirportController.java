package com.epam.flightscontroltool.controller.endpoint;

import com.epam.flightscontroltool.controller.dto.airport.AirportDto;
import com.epam.flightscontroltool.service.airport.AirportService;
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
@RequestMapping("/airports")
public class AirportController {

    private final AirportService airportService;

    @ApiOperation(value = "Retrieving list of airports")
    @ApiResponses({
            @ApiResponse(code = 200, message = "List of airports"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<AirportDto> getAirports(
            @ApiParam("City id") @RequestParam(value = "cityId", required = false) Long cityId,
            @ApiParam("Page number") @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @ApiParam("Page size") @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        log.info("Retrieving all airports...");
        return airportService.getAll(cityId, page, size);
    }

    @ApiOperation(value = "Retrieving an airport details")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Airport details"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public AirportDto getAirportById(
            @ApiParam("Airport id") @PathVariable(value = "id") Long id) {
        log.info("Retrieving details of an airport with id {}", id);
        return airportService.getAirportDetailsById(id);
    }

    @ApiOperation(value = "Saving an airport")
    @ApiResponses({
            @ApiResponse(code = 201, message = "An airport was successfully saved"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public AirportDto save(@Valid @RequestBody AirportDto airportDto) {
        log.info("Saving a new airport...");
        return airportService.save(airportDto);
    }

    @ApiOperation(value = "Updating an airport")
    @ApiResponses({
            @ApiResponse(code = 200, message = "An airport was successfully updated"),
            @ApiResponse(code = 404, message = "An airport was not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public AirportDto update(
            @ApiParam("Airport id") @PathVariable(value = "id") Long id,
            @Valid @RequestBody AirportDto airportDto) {
        log.info("Updating an airport with id {}...", id);
        return airportService.update(id, airportDto);
    }

    @ApiOperation(value = "Deleting an airport")
    @ApiResponses({
            @ApiResponse(code = 200, message = "An airport was successfully deleted"),
            @ApiResponse(code = 404, message = "An airport was not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public void delete(
            @ApiParam("Airport id") @PathVariable(value = "id") Long id) {
        log.info("Fetching & deleting an airport with id {}", id);
        airportService.delete(id);
    }
}
