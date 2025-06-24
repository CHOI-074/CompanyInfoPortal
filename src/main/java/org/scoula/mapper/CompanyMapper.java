package org.scoula.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.scoula.dto.CompanyDTO;

import java.util.List;
@Mapper
public interface CompanyMapper {
    void insertCompany(CompanyDTO company);
    List<CompanyDTO> findAllCompanies();
    CompanyDTO findCompanyById(Long id);
    void updateCompany(CompanyDTO company);
    void deleteCompany(Long id);
}
