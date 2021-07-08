package com.epam.flightscontroltool.controller.endpoint;

import com.epam.flightscontroltool.controller.dto.staff.StaffMemberDto;
import com.epam.flightscontroltool.service.staff.StaffMemberService;
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
@RequestMapping("/staffmembers")
public class StaffController {

    private final StaffMemberService staffMemberService;

    @ApiOperation(value = "Retrieving list of staff members")
    @ApiResponses({
            @ApiResponse(code = 200, message = "List of staff members"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<StaffMemberDto> getStaffMembers(
            @ApiParam("Airplane id") @RequestParam(value = "airplaneId", required = false) Long airplaneId,
            @ApiParam("Page number") @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @ApiParam("Page size") @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        log.info("Retrieving all staff members...");
        return staffMemberService.getAll(airplaneId, page, size);
    }

    @ApiOperation(value = "Saving a staff member")
    @ApiResponses({
            @ApiResponse(code = 201, message = "A staff member was successfully saved"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public StaffMemberDto save(@Valid @RequestBody StaffMemberDto staffMemberDto) {
        log.info("Saving a staff member...");
        return staffMemberService.save(staffMemberDto);
    }

    @ApiOperation(value = "Updating a staff member")
    @ApiResponses({
            @ApiResponse(code = 200, message = "A staff member was successfully updated"),
            @ApiResponse(code = 404, message = "A staff member was not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public StaffMemberDto update(
            @ApiParam("Staff member id") @PathVariable(value = "id") Long id,
            @Valid @RequestBody StaffMemberDto staffMemberDto) {
        log.info("Updating a staff member with id {}...", id);
        return staffMemberService.update(id, staffMemberDto);
    }

    @ApiOperation(value = "Deleting a staff member")
    @ApiResponses({
            @ApiResponse(code = 200, message = "A staff member was successfully deleted"),
            @ApiResponse(code = 404, message = "A staff member was not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public void delete(
            @ApiParam("Staff member id") @PathVariable(value = "id") Long id) {
        log.info("Fetching & deleting a staff member with id {}", id);
        staffMemberService.delete(id);
    }
}
