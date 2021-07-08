package com.epam.flightscontroltool.service.airport;

import com.epam.flightscontroltool.controller.dto.airport.AirportDto;
import com.epam.flightscontroltool.domain.airport.entity.Airport;
import com.epam.flightscontroltool.domain.airport.mapper.AirportMapper;
import com.epam.flightscontroltool.controller.exception.airport.AirportNotFoundException;
import com.epam.flightscontroltool.controller.exception.airport.CityNotFoundException;
import com.epam.flightscontroltool.repository.airport.AirportRepository;
import com.epam.flightscontroltool.repository.airport.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;
    private final CityRepository cityRepository;

    public Page<AirportDto> getAll(Long cityId, int pageNumber, int pageSize) {
        return (cityId == null) ? getAll(pageNumber, pageSize) : getAllAirportsByCityId(cityId, pageNumber, pageSize);
    }

    public Page<AirportDto> getAll(int pageNumber, int pageSize) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Airport> page = airportRepository.findAll(pageRequest);
        return page.map(this::convertToAirportDto);
    }

    public Page<AirportDto> getAllAirportsByCityId(Long cityId, int pageNumber, int pageSize) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Airport> page = airportRepository.getAllByCityId(cityId, pageRequest);
        return page.map(this::convertToAirportDto);
    }

    public AirportDto getAirportDetailsById(Long id) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new AirportNotFoundException(id));
        return convertToAirportDto(airport);
    }

    @Transactional
    public AirportDto save(AirportDto airportDto) {
        long cityId = airportDto.getCityId();
        if (cityRepository.existsById(cityId)) {
            Airport airport = convertToAirport(airportDto);
            airport = airportRepository.save(airport);
            return convertToAirportDto(airport);
        } else {
            throw new CityNotFoundException(cityId);
        }
    }

    @Transactional
    public AirportDto update(Long id, AirportDto airportDto) {
        long cityId = airportDto.getCityId();
        if (cityRepository.existsById(cityId)) {
            Airport airport = convertToAirport(airportDto);
            var updatedAirport = airportRepository.findById(id)
                    .map(a -> updateAirport(airport, a))
                    .orElseThrow(() -> new AirportNotFoundException(id));
            return convertToAirportDto(updatedAirport);
        } else {
            throw new CityNotFoundException(cityId);
        }
    }

    private Airport convertToAirport(AirportDto airportDto) {
        return AirportMapper.INSTANCE.toAirport(airportDto);
    }

    private AirportDto convertToAirportDto(Airport airport) {
        return AirportMapper.INSTANCE.toAirportDto(airport);
    }

    public void delete(Long id) {
        airportRepository.findById(id)
                .map(this::deleteAirport)
                .orElseThrow(() -> new AirportNotFoundException(id));
    }

    private Airport updateAirport(Airport copyFrom, Airport copyTo) {
        copyTo.setName(copyFrom.getName());
        return copyTo;
    }

    private Airport deleteAirport(Airport airport) {
        airportRepository.deleteById(airport.getId());
        return airport;
    }
}

