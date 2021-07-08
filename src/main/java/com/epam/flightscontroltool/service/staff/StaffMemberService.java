package com.epam.flightscontroltool.service.staff;

import com.epam.flightscontroltool.controller.dto.staff.StaffMemberDto;
import com.epam.flightscontroltool.controller.exception.airplane.AirplaneNotFoundException;
import com.epam.flightscontroltool.controller.exception.staff.StaffMemberNotFoundException;
import com.epam.flightscontroltool.domain.staff.entity.StaffMember;
import com.epam.flightscontroltool.domain.staff.mapper.StaffMemberMapper;
import com.epam.flightscontroltool.repository.airplane.AirplaneRepository;
import com.epam.flightscontroltool.repository.staff.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class StaffMemberService {

    private final StaffRepository staffRepository;
    private final AirplaneRepository airplaneRepository;

    public Page<StaffMemberDto> getAll(Long airplaneId, int pageNumber, int pageSize) {
        return (airplaneId == null) ? getAll(pageNumber, pageSize) : getAllByAirplaneId(airplaneId, pageNumber, pageSize);
    }

    public Page<StaffMemberDto> getAll(int pageNumber, int pageSize) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<StaffMember> page = staffRepository.findAll(pageRequest);
        return page.map(this::convertToStaffMemberDto);
    }

    public Page<StaffMemberDto> getAllByAirplaneId(Long airplaneId, int pageNumber, int pageSize) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<StaffMember> page = staffRepository.getStaffByAirplaneId(airplaneId, pageRequest);
        return page.map(this::convertToStaffMemberDto);
    }

    @Transactional
    public StaffMemberDto save(StaffMemberDto staffMemberDto) {
        long airplaneId = staffMemberDto.getAirplaneId();
        if (airplaneRepository.existsById(airplaneId)) {
            StaffMember staffMember = convertToStaffMember(staffMemberDto);
            staffMember = staffRepository.save(staffMember);
            return convertToStaffMemberDto(staffMember);
        } else {
            throw new AirplaneNotFoundException(airplaneId);
        }
    }

    @Transactional
    public StaffMemberDto update(Long id, StaffMemberDto staffMemberDto) {
        long airplaneId = staffMemberDto.getAirplaneId();
        if (airplaneRepository.existsById(airplaneId)) {
            StaffMember staffMember = convertToStaffMember(staffMemberDto);
            var updatedStaffMember = staffRepository.findById(id)
                    .map(s -> updateStaffMember(staffMember, s))
                    .orElseThrow(() -> new StaffMemberNotFoundException(id));
            return convertToStaffMemberDto(updatedStaffMember);
        } else {
            throw new AirplaneNotFoundException(airplaneId);
        }
    }

    @Transactional
    public void delete(Long id) {
        staffRepository.findById(id)
                .map(this::deleteStaffMember)
                .orElseThrow(() -> new AirplaneNotFoundException(id));
    }

    private StaffMember updateStaffMember(StaffMember copyFrom, StaffMember copyTo) {
        copyTo.setFirstName(copyFrom.getFirstName());
        copyTo.setSurname(copyFrom.getSurname());
        copyTo.setPosition(copyFrom.getPosition());
        copyTo.setAirplane(copyFrom.getAirplane());
        return copyTo;
    }

    private StaffMember deleteStaffMember(StaffMember staffMember) {
        staffRepository.deleteById(staffMember.getId());
        return staffMember;
    }

    private StaffMember convertToStaffMember(StaffMemberDto staffMemberDto) {
        return StaffMemberMapper.INSTANCE.toStaffMember(staffMemberDto);
    }

    private StaffMemberDto convertToStaffMemberDto(StaffMember staffMember) {
        return StaffMemberMapper.INSTANCE.toStaffMemberDto(staffMember);
    }
}

