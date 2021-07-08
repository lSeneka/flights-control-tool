package com.epam.flightscontroltool.controller.endpoint;

import com.epam.flightscontroltool.controller.dto.flight.FlightDto;
import com.epam.flightscontroltool.service.flight.FlightService;
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
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    @ApiOperation(value = "Retrieving list of flights")
    @ApiResponses({
            @ApiResponse(code = 200, message = "List of flights"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping

    public Page<FlightDto> getFlights(
            @ApiParam("Departure city id") @RequestParam(value = "departureCityId", required = false) Long departureCityId,
            @ApiParam("Arrival city id") @RequestParam(value = "arrivalCityId", required = false) Long arrivalCityId,
            @ApiParam("Departure time") @RequestParam(value = "departureDateTime", required = false) String departureDateTime,
            @ApiParam("Arrival time") @RequestParam(value = "arrivalDateTime", required = false) String arrivalDateTime,
            @ApiParam("Page number") @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @ApiParam("Page size") @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        log.info("Retrieving all flights...");
        return flightService.getAll(departureCityId, arrivalCityId, departureDateTime, arrivalDateTime, page, size);
    }

    @ApiOperation(value = "Retrieving a flight details")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Flight details"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public FlightDto getFlightById(
            @ApiParam("Flight id") @PathVariable(value = "id") Long id) {
        log.info("Retrieving details of a flight with id {}", id);
        return flightService.getFlightDetailsById(id);
    }

    @ApiOperation(value = "Saving a flight")
    @ApiResponses({
            @ApiResponse(code = 201, message = "A flight was successfully saved"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public FlightDto save(@Valid @RequestBody FlightDto flightDto) {
        log.info("Saving a new flight...");
        return flightService.save(flightDto);
    }

    @ApiOperation(value = "Updating a flight")
    @ApiResponses({
            @ApiResponse(code = 200, message = "A flight was successfully updated"),
            @ApiResponse(code = 404, message = "A flight was not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public FlightDto update(
            @ApiParam("Flight id") @PathVariable(value = "id") Long id,
            @Valid @RequestBody FlightDto flightDto) {
        log.info("Updating a flight with id {}...", id);
        return flightService.update(id, flightDto);
    }

    @ApiOperation(value = "Deleting a flight")
    @ApiResponses({
            @ApiResponse(code = 200, message = "A flight was successfully deleted"),
            @ApiResponse(code = 404, message = "A flight was not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public void delete(
            @ApiParam("Flight id") @PathVariable(value = "id") Long id) {
        log.info("Fetching & deleting a flight with id {}", id);
        flightService.delete(id);
    }
}
