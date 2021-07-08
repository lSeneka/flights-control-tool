package com.epam.flightscontroltool.repository.airplane;

import com.epam.flightscontroltool.domain.airplane.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
