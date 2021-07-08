package com.epam.flightscontroltool.repository.airplane;

import com.epam.flightscontroltool.domain.airplane.entity.Airplane;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long> {

    @Query(value = "SELECT a.id, a.number, a.capacity, a.manufacturer, a.company_id " +
            "FROM airport AS a INNER JOIN company AS ac " +
            "ON a.company_id = ac.id WHERE ac.id=:companyId", nativeQuery = true)
    Page<Airplane> getAllByCompanyId(@Param("companyId") Long companyId, Pageable pageable);

}