package com.epam.flightscontroltool.service.airport;

import com.epam.flightscontroltool.controller.dto.airport.CityDto;
import com.epam.flightscontroltool.domain.airport.entity.City;
import com.epam.flightscontroltool.domain.airport.mapper.CityMapper;
import com.epam.flightscontroltool.controller.exception.airport.CityNotFoundException;
import com.epam.flightscontroltool.controller.exception.airport.CountryNotFoundException;
import com.epam.flightscontroltool.repository.airport.CityRepository;
import com.epam.flightscontroltool.repository.airport.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    public Page<CityDto> getAll(Long countryId, int pageNumber, int pageSize) {
        return (countryId == null) ? getAll(pageNumber, pageSize) : getAllCitiesByCountryId(countryId, pageNumber, pageSize);
    }

    public Page<CityDto> getAll(int pageNumber, int pageSize) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<City> page = cityRepository.findAll(pageRequest);
        return page.map(this::convertToCityDto);
    }

    public Page<CityDto> getAllCitiesByCountryId(Long countryId, int pageNumber, int pageSize) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<City> page = cityRepository.getAllByCountryId(countryId, pageRequest);
        return page.map(this::convertToCityDto);
    }

    public CityDto getCityDetailsById(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
        return convertToCityDto(city);
    }

    @Transactional
    public CityDto save(CityDto cityDto) {
        long countryId = cityDto.getCountryId();
        if (countryRepository.existsById(countryId)) {
            City city = convertToCity(cityDto);
            city = cityRepository.save(city);
            return convertToCityDto(city);
        } else {
            throw new CountryNotFoundException(countryId);
        }
    }

    @Transactional
    public CityDto update(Long id, CityDto cityDto) {
        long countryId = cityDto.getCountryId();
        if (countryRepository.existsById(countryId)) {
            City city = convertToCity(cityDto);
            var updatedCity = cityRepository.findById(id)
                    .map(c -> updateCity(city, c))
                    .orElseThrow(() -> new CityNotFoundException(id));
            return convertToCityDto(updatedCity);
        } else {
            throw new CountryNotFoundException(countryId);
        }
    }

    @Transactional
    public void delete(Long id) {
        cityRepository.findById(id)
                .map(this::deleteCity)
                .orElseThrow(() -> new CityNotFoundException(id));
    }

    private City updateCity(City copyFrom, City copyTo) {
        copyTo.setName(copyFrom.getName());
        copyTo.setCountry(copyFrom.getCountry());
        return copyTo;
    }

    private City deleteCity(City city) {
        cityRepository.deleteById(city.getId());
        return city;
    }

    private City convertToCity(CityDto cityDto) {
        return CityMapper.INSTANCE.toCity(cityDto);
    }

    private CityDto convertToCityDto(City city) {
        return CityMapper.INSTANCE.toCityDto(city);
    }
}

