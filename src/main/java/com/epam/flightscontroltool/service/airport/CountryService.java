package com.epam.flightscontroltool.service.airport;

import com.epam.flightscontroltool.controller.dto.airport.CountryDto;
import com.epam.flightscontroltool.domain.airport.entity.Country;
import com.epam.flightscontroltool.domain.airport.mapper.CountryMapper;
import com.epam.flightscontroltool.controller.exception.airport.CountryNotFoundException;
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
public class CountryService {

    private final CountryRepository countryRepository;

    public Page<CountryDto> getAll(int pageNumber, int pageSize) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Country> page = countryRepository.findAll(pageRequest);
        return page.map(this::convertToCountryDto);
    }

    @Transactional
    public Country save(CountryDto countryDto) {
        Country country = convertToCountry(countryDto);
        country = countryRepository.save(country);
        return country;
    }

    @Transactional
    public CountryDto update(Long id, CountryDto countryDto) {
        Country country = convertToCountry(countryDto);
        var updatedCountry = countryRepository.findById(id)
                .map(c -> updateCountry(country, c))
                .orElseThrow(() -> new CountryNotFoundException(id));
        return convertToCountryDto(updatedCountry);
    }

    @Transactional
    public void delete(Long id) {
        countryRepository.findById(id)
                .map(this::deleteCountry)
                .orElseThrow(() -> new CountryNotFoundException(id));
    }

    private Country convertToCountry(CountryDto countryDto) {
        return CountryMapper.INSTANCE.toCountry(countryDto);
    }

    private CountryDto convertToCountryDto(Country country) {
        return CountryMapper.INSTANCE.toCountryDto(country);
    }

    private Country updateCountry(Country copyFrom, Country copyTo) {
        copyTo.setName(copyFrom.getName());
        return copyTo;
    }

    private Country deleteCountry(Country country) {
        countryRepository.deleteById(country.getId());
        return country;
    }
}

