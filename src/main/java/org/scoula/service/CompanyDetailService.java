package org.scoula.service;

import org.scoula.dto.CompanyDetailDTO;

import java.util.List;

public interface CompanyDetailService {
    List<CompanyDetailDTO> getAllDetails();
    CompanyDetailDTO getDetailByCompanyId(Long companyId);
    void insertCompanyDetail(CompanyDetailDTO detailDTO);
    void updateCompanyDetail(CompanyDetailDTO detailDTO);
    void deleteCompanyDetail(Long companyId);
}
