package com.epam.flightscontroltool.repository.airport;

import com.epam.flightscontroltool.domain.airport.entity.Airport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    @Query(value = "SELECT a.id, a.name, a.city_id FROM airport AS a " +
            "INNER JOIN city c ON a.city_id = c.id WHERE c.id=:cityId", nativeQuery = true)
    Page<Airport> getAllByCityId(@Param("cityId") Long cityId, Pageable pageable);

}
