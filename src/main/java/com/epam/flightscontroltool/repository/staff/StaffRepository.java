package com.epam.flightscontroltool.repository.staff;

import com.epam.flightscontroltool.domain.staff.entity.StaffMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<StaffMember, Long> {

    @Query(value = "SELECT s.id, s.firstName, s.surname, s.position, s.airplane_id " +
            "FROM staffMember AS s INNER JOIN com.epam.flightscontroltool.airplane AS a " +
            "ON s.airplane_id = a.id WHERE a.id=:airplaneId", nativeQuery = true)
    Page<StaffMember> getStaffByAirplaneId(@Param("airplaneId") Long airplaneId, Pageable pageable);

}