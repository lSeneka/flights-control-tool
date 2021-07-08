package com.epam.flightscontroltool.controller.endpoint;

import com.epam.flightscontroltool.controller.dto.airport.CountryDto;
import com.epam.flightscontroltool.domain.airport.entity.Country;
import com.epam.flightscontroltool.service.airport.CountryService;
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
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @ApiOperation(value = "Retrieving list of countries")
    @ApiResponses({
            @ApiResponse(code = 200, message = "List of countries"),
    })
    @GetMapping
    public Page<CountryDto> getCountries(
            @ApiParam("Page number") @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @ApiParam("Page size") @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        log.info("Retrieving list of all countries...");
        return countryService.getAll(page, size);
    }

    @ApiOperation(value = "Saving a country")
    @ApiResponses({
            @ApiResponse(code = 201, message = "A country was successfully saved"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public Country save(@Valid @RequestBody CountryDto countryDto) {
        log.info("Saving a new country...");
        return countryService.save(countryDto);
    }

    @ApiOperation(value = "Updating a country")
    @ApiResponses({
            @ApiResponse(code = 200, message = "A country was successfully updated"),
            @ApiResponse(code = 404, message = "A country was not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public CountryDto update(
            @ApiParam("Country id") @PathVariable(value = "id") Long id,
            @Valid @RequestBody CountryDto countryDto) {
        log.info("Updating a country with id {}...", id);
        return countryService.update(id, countryDto);
    }

    @ApiOperation(value = "Deleting a country")
    @ApiResponses({
            @ApiResponse(code = 200, message = "A country was successfully deleted"),
            @ApiResponse(code = 404, message = "A country was not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public void delete(
            @ApiParam("Country id") @PathVariable(value = "id") Long id) {
        log.info("Fetching & deleting a country with id {}", id);
        countryService.delete(id);
    }
}
