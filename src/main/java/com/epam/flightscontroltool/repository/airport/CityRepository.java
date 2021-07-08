package com.epam.flightscontroltool.repository.airport;

import com.epam.flightscontroltool.domain.airport.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query(value = "SELECT c.id, c.name, c.country_id, cn.name c FROM city AS c " +
            "INNER JOIN country AS cn ON c.country_id = cn.id " +
            "WHERE cn.id = :countryId", nativeQuery = true)
    Page<City> getAllByCountryId(@Param("countryId") Long countryId, Pageable pageable);

}
