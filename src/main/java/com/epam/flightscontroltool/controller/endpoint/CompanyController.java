package com.epam.flightscontroltool.controller.endpoint;

import com.epam.flightscontroltool.controller.dto.airplane.CompanyDto;
import com.epam.flightscontroltool.domain.airplane.entity.Company;
import com.epam.flightscontroltool.service.airplane.CompanyService;
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
@RequestMapping("/airplanecompanies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @ApiOperation(value = "Retrieving list of com.epam.flightscontroltool.airplane companies")
    @ApiResponses({
            @ApiResponse(code = 200, message = "List of com.epam.flightscontroltool.airplane companies"),
    })
    @GetMapping
    public Page<CompanyDto> getAirplaneCompanies(
            @ApiParam("Page number") @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @ApiParam("Page size") @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        log.info("Retrieving list of com.epam.flightscontroltool.airplane companies...");
        return companyService.getAll(page, size);
    }

    @ApiOperation(value = "Saving an com.epam.flightscontroltool.airplane company")
    @ApiResponses({
            @ApiResponse(code = 201, message = "An com.epam.flightscontroltool.airplane company was successfully saved"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public Company save(@Valid @RequestBody CompanyDto companyDto) {
        log.info("Saving a new com.epam.flightscontroltool.airplane company...");
        return companyService.save(companyDto);
    }

    @ApiOperation(value = "Updating an com.epam.flightscontroltool.airplane company")
    @ApiResponses({
            @ApiResponse(code = 200, message = "An com.epam.flightscontroltool.airplane company was successfully updated"),
            @ApiResponse(code = 404, message = "An com.epam.flightscontroltool.airplane company was not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public CompanyDto update(
            @ApiParam("Airplane company id") @PathVariable(value = "id") Long id,
            @Valid @RequestBody CompanyDto companyDto) {
        log.info("Updating an com.epam.flightscontroltool.airplane company with id {}...", id);
        return companyService.update(id, companyDto);
    }

    @ApiOperation(value = "Deleting an com.epam.flightscontroltool.airplane company")
    @ApiResponses({
            @ApiResponse(code = 200, message = "An com.epam.flightscontroltool.airplane company was successfully deleted"),
            @ApiResponse(code = 404, message = "An com.epam.flightscontroltool.airplane company was not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public void delete(
            @ApiParam("Airplane company id") @PathVariable(value = "id") Long id) {
        log.info("Fetching & deleting a country with id {}", id);
        companyService.delete(id);
    }
}
