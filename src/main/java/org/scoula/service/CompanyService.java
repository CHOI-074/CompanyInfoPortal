package org.scoula.service;

import org.scoula.dto.CompanyDTO;

import java.util.List;

public interface CompanyService {
    void createCompany(CompanyDTO company);
    List<CompanyDTO> getAllCompanies();
    CompanyDTO getCompanyById(Long id);
    void updateCompany(CompanyDTO company);
    void deleteCompany(Long id);
}