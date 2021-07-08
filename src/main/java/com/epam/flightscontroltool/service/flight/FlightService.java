package com.epam.flightscontroltool.service.flight;

import com.epam.flightscontroltool.controller.dto.flight.FlightDto;
import com.epam.flightscontroltool.controller.exception.airplane.AirplaneNotFoundException;
import com.epam.flightscontroltool.controller.exception.airport.AirportNotFoundException;
import com.epam.flightscontroltool.controller.exception.flight.FlightNotFoundException;
import com.epam.flightscontroltool.domain.Flight;
import com.epam.flightscontroltool.domain.flight.FlightMapper;
import com.epam.flightscontroltool.repository.airplane.AirplaneRepository;
import com.epam.flightscontroltool.repository.airport.AirportRepository;
import com.epam.flightscontroltool.repository.flight.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirplaneRepository airplaneRepository;
    private final AirportRepository airportRepository;

    //TODO: lots of if, to refactor
    public Page<FlightDto> getAll(Long departureCityId, Long arrivalCityId, String departureDateTime,
                                  String arrivalDateTime, int pageNumber, int pageSize) {
        if (departureCityId != null && arrivalCityId != null &&
                departureDateTime != null && arrivalDateTime != null) {
            return getAllByDirectionAndDate(departureCityId, arrivalCityId, departureDateTime, arrivalDateTime, pageNumber, pageSize);
        } else if (departureCityId != null && arrivalCityId != null &&
                departureDateTime == null && arrivalDateTime == null) {
            return getAllByDirection(departureCityId, arrivalCityId, pageNumber, pageSize);
        } else {
            return getAll(pageNumber, pageSize);
        }
    }

    public Page<FlightDto> getAll(int pageNumber, int pageSize) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Flight> page = flightRepository.findAll(pageRequest);
        return page.map(this::convertToFlightDto);
    }

    public Page<FlightDto> getAllByDirection(Long departureCityId, Long arrivalCityId, int pageNumber, int pageSize) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Flight> page = flightRepository.findByDirection(departureCityId, arrivalCityId, pageRequest);
        return page.map(this::convertToFlightDto);
    }

    // TODO: to refactor, too many parameters
    public Page<FlightDto> getAllByDirectionAndDate(Long departureCityId, Long arrivalCityId, String departureDateTime,
                                                    String arrivalDateTime, int pageNumber, int pageSize) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Flight> page = flightRepository.findByDirectionAndDate(departureCityId, arrivalCityId, departureDateTime, arrivalDateTime, pageRequest);
        return page.map(this::convertToFlightDto);
    }

    public FlightDto getFlightDetailsById(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException(id));
        return convertToFlightDto(flight);
    }

    //TODO: to refactor
    @Transactional
    public FlightDto save(FlightDto flightDto) {
        long airplaneId = flightDto.getAirplaneId();
        long departureAirportId = flightDto.getDepartureAirportId();
        long arrivalAirportId = flightDto.getArrivalAirportId();
        if (!airplaneRepository.existsById(airplaneId)) {
            throw new AirplaneNotFoundException(airplaneId);
        } else if (!airportRepository.existsById(departureAirportId)) {
            throw new AirportNotFoundException(departureAirportId);
        } else if (!airportRepository.existsById(arrivalAirportId)) {
            throw new AirportNotFoundException(arrivalAirportId);
        } else {
            Flight flight = convertToFlight(flightDto);
            flight = flightRepository.save(flight);
            return convertToFlightDto(flight);
        }
    }

    //TODO: to refactor
    @Transactional
    public FlightDto update(Long id, FlightDto flightDto) {
        long airplaneId = flightDto.getAirplaneId();
        long departureAirportId = flightDto.getDepartureAirportId();
        long arrivalAirportId = flightDto.getArrivalAirportId();
        if (!airplaneRepository.existsById(airplaneId)) {
            throw new AirplaneNotFoundException(airplaneId);
        } else if (!airportRepository.existsById(departureAirportId)) {
            throw new AirportNotFoundException(departureAirportId);
        } else if (!airportRepository.existsById(arrivalAirportId)) {
            throw new AirportNotFoundException(arrivalAirportId);
        } else {
            Flight flight = convertToFlight(flightDto);
            var updatedFlight = flightRepository.findById(id)
                    .map(f -> updateFlight(flight, f))
                    .orElseThrow(() -> new FlightNotFoundException(id));
            return convertToFlightDto(updatedFlight);
        }
    }

    @Transactional
    public void delete(Long id) {
        flightRepository.findById(id)
                .map(this::deleteFlight)
                .orElseThrow(() -> new FlightNotFoundException(id));
    }

    private Flight updateFlight(Flight copyFrom, Flight copyTo) {
        copyTo.setAirplane(copyFrom.getAirplane());
        copyTo.setArrivalAirport(copyFrom.getArrivalAirport());
        copyTo.setDepartureAirport(copyFrom.getDepartureAirport());
        copyTo.setArrivalDateTime(copyFrom.getArrivalDateTime());
        copyTo.setDepartureDateTime(copyFrom.getDepartureDateTime());
        copyTo.setBasePrice(copyFrom.getBasePrice());
        return copyTo;
    }

    private Flight deleteFlight(Flight flight) {
        flightRepository.deleteById(flight.getId());
        return flight;
    }

    private Flight convertToFlight(FlightDto flightDto) {
        return FlightMapper.INSTANCE.toFlight(flightDto);
    }

    private FlightDto convertToFlightDto(Flight flight) {
        return FlightMapper.INSTANCE.toFlightDto(flight);
    }


}
