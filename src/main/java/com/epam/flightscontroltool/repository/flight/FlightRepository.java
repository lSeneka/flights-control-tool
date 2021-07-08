package com.epam.flightscontroltool.repository.flight;

import com.epam.flightscontroltool.domain.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    // TODO: add proper queries. Change method parameters types + add converter for types
    @Query("")
    Page<Flight> findByDirection(Long departureCityId, Long arrivalCityId, Pageable pageable);

    @Query("")
    Page<Flight> findByDirectionAndDate(Long departureCityId, Long arrivalCityId,
                                        String departureDate, String arrivalDate,
                                        Pageable pageable);
}
