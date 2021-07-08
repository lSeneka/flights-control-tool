package com.epam.flightscontroltool.repository.airport;

import com.epam.flightscontroltool.domain.airport.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
