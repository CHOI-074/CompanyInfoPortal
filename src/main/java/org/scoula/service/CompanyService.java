package org.scoula.service;

import org.scoula.dto.CompanyDTO;
import org.scoula.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    public void createCompany(CompanyDTO dto) {
        companyMapper.insertCompany(dto);
    }

    public List<CompanyDTO> getAllCompanies() {
        return companyMapper.findAllCompanies();
    }

    public CompanyDTO getCompanyById(Long id) {
        return companyMapper.findCompanyById(id);
    }

    public void updateCompany(CompanyDTO dto) {
        companyMapper.updateCompany(dto);
    }

    public void deleteCompany(Long id) {
        companyMapper.deleteCompany(id);
    }
}
