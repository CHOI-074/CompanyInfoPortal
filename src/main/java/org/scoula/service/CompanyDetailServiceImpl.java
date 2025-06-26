package org.scoula.service;


import lombok.RequiredArgsConstructor;
import org.scoula.dto.CompanyDetailDTO;
import org.scoula.mapper.CompanyDetailMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyDetailServiceImpl implements CompanyDetailService{

    private final CompanyDetailMapper mapper;

    @Override
    public List<CompanyDetailDTO> getAllDetails() {
        return mapper.getAllDetails();
    }

    @Override
    public CompanyDetailDTO getDetailByCompanyId(Long companyId) {
        return mapper.getDetailByCompanyId(companyId);
    }

    @Override
    public void insertCompanyDetail(CompanyDetailDTO detailDTO) {
        mapper.insertCompanyDetail(detailDTO);
    }

    @Override
    public void updateCompanyDetail(CompanyDetailDTO detailDTO) {
        mapper.updateCompanyDetail(detailDTO);
    }

    @Override
    public void deleteCompanyDetail(Long companyId) {
        mapper.deleteCompanyDetail(companyId);
    }
}