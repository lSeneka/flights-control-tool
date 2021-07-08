package com.epam.flightscontroltool.service.airplane;

import com.epam.flightscontroltool.controller.dto.airplane.CompanyDto;
import com.epam.flightscontroltool.controller.exception.airplane.CompanyNotFoundException;
import com.epam.flightscontroltool.domain.airplane.entity.Company;
import com.epam.flightscontroltool.domain.airplane.mapper.CompanyMapper;
import com.epam.flightscontroltool.repository.airplane.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Page<CompanyDto> getAll(int pageNumber, int pageSize) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Company> page = companyRepository.findAll(pageRequest);
        return page.map(this::convertToAirplaneCompanyDto);
    }

    @Transactional
    public Company save(CompanyDto companyDto) {
        Company company = convertToAirplaneCompany(companyDto);
        company = companyRepository.save(company);
        return company;
    }

    @Transactional
    public CompanyDto update(Long id, CompanyDto companyDto) {
        Company company = convertToAirplaneCompany(companyDto);
        var updatedCompany = companyRepository.findById(id)
                .map(c -> updateCompany(company, c))
                .orElseThrow(() -> new CompanyNotFoundException(id));
        return convertToAirplaneCompanyDto(updatedCompany);
    }

    @Transactional
    public void delete(Long id) {
        companyRepository.findById(id)
                .map(this::deleteCompany)
                .orElseThrow(() -> new CompanyNotFoundException(id));
    }

    private Company convertToAirplaneCompany(CompanyDto companyDto) {
        return CompanyMapper.INSTANCE.toCompany(companyDto);
    }

    private CompanyDto convertToAirplaneCompanyDto(Company airplaneCompany) {
        return CompanyMapper.INSTANCE.toCompanyDto(airplaneCompany);
    }

    private Company updateCompany(Company copyFrom, Company copyTo) {
        copyTo.setName(copyFrom.getName());
        return copyTo;
    }

    private Company deleteCompany(Company company) {
        companyRepository.deleteById(company.getId());
        return company;
    }
}

