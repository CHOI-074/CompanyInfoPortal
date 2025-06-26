package org.scoula.mapper;

import org.scoula.dto.CompanyDetailDTO;

import java.util.List;

public interface CompanyDetailMapper {
    List<CompanyDetailDTO> getAllDetails();  // 전체 조회
    CompanyDetailDTO getDetailByCompanyId(Long companyId);  // 회사ID로 조회
    void insertCompanyDetail(CompanyDetailDTO detailDTO);   // 등록
    void updateCompanyDetail(CompanyDetailDTO detailDTO);   // 수정
    void deleteCompanyDetail(Long companyId);               // 삭제
}
