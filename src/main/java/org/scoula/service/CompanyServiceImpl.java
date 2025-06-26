package org.scoula.service;

import lombok.RequiredArgsConstructor;
import org.scoula.dto.CompanyDTO;
import org.scoula.mapper.CompanyMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyMapper companyMapper;

    @Override
    public void createCompany(CompanyDTO company) {
        companyMapper.insertCompany(company);
    }

    @Override
    public List<CompanyDTO> getAllCompanies() {
        return companyMapper.findAllCompanies();
    }

    @Override
    public CompanyDTO getCompanyById(Long id) {
        return companyMapper.findCompanyById(id);
    }

    @Override
    public void updateCompany(CompanyDTO company) {
        companyMapper.updateCompany(company);
    }

    @Override
    public void deleteCompany(Long id) {
        companyMapper.deleteCompany(id);
    }
}