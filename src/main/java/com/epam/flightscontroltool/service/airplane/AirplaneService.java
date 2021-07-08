package com.epam.flightscontroltool.service.airplane;

import com.epam.flightscontroltool.controller.dto.airplane.AirplaneDto;
import com.epam.flightscontroltool.controller.exception.airplane.CompanyNotFoundException;
import com.epam.flightscontroltool.controller.exception.airplane.AirplaneNotFoundException;
import com.epam.flightscontroltool.domain.airplane.entity.Airplane;
import com.epam.flightscontroltool.domain.airplane.mapper.AirplaneMapper;
import com.epam.flightscontroltool.repository.airplane.CompanyRepository;
import com.epam.flightscontroltool.repository.airplane.AirplaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AirplaneService {

    private final AirplaneRepository airplaneRepository;
    private final CompanyRepository companyRepository;

    public Page<AirplaneDto> getAll(Long companyId, int pageNumber, int pageSize) {
        return (companyId == null) ? getAll(pageNumber, pageSize) : getAllByCompanyId(companyId, pageNumber, pageSize);
    }

    public Page<AirplaneDto> getAll(int pageNumber, int pageSize) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Airplane> page = airplaneRepository.findAll(pageRequest);
        return page.map(this::convertToAirplaneDto);
    }

    public Page<AirplaneDto> getAllByCompanyId(Long companyId, int pageNumber, int pageSize) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Airplane> page = airplaneRepository.getAllByCompanyId(companyId, pageRequest);
        return page.map(this::convertToAirplaneDto);
    }

    public AirplaneDto getAirplaneDetailsById(Long id) {
        Airplane airplane = airplaneRepository.findById(id)
                .orElseThrow(() -> new AirplaneNotFoundException(id));
        return convertToAirplaneDto(airplane);
    }

    @Transactional
    public AirplaneDto save(AirplaneDto airplaneDto) {
        long companyId = airplaneDto.getCompanyId();
        if (companyRepository.existsById(companyId)) {
            Airplane airplane = convertToAirplane(airplaneDto);
            airplane = airplaneRepository.save(airplane);
            return convertToAirplaneDto(airplane);
        } else {
            throw new CompanyNotFoundException(companyId);
        }
    }

    @Transactional
    public AirplaneDto update(Long id, AirplaneDto airplaneDto) {
        long companyId = airplaneDto.getCompanyId();
        if (companyRepository.existsById(companyId)) {
            Airplane airplane = convertToAirplane(airplaneDto);
            var updatedAirplane = airplaneRepository.findById(id)
                    .map(a -> updateAirplane(airplane, a))
                    .orElseThrow(() -> new AirplaneNotFoundException(id));
            return convertToAirplaneDto(updatedAirplane);
        } else {
            throw new CompanyNotFoundException(companyId);
        }
    }

    @Transactional
    public void delete(Long id) {
        airplaneRepository.findById(id)
                .map(this::deleteAirplane)
                .orElseThrow(() -> new AirplaneNotFoundException(id));
    }

    private Airplane updateAirplane(Airplane copyFrom, Airplane copyTo) {
        copyTo.setNumber(copyFrom.getNumber());
        copyTo.setCapacity(copyFrom.getCapacity());
        copyTo.setManufacturer(copyFrom.getManufacturer());
        copyTo.setCompany(copyFrom.getCompany());
        return copyTo;
    }

    private Airplane deleteAirplane(Airplane airplane) {
        airplaneRepository.deleteById(airplane.getId());
        return airplane;
    }

    private Airplane convertToAirplane(AirplaneDto airplaneDto) {
        return AirplaneMapper.INSTANCE.toAirplane(airplaneDto);
    }

    private AirplaneDto convertToAirplaneDto(Airplane airplane) {
        return AirplaneMapper.INSTANCE.toAirplaneDto(airplane);
    }
}

