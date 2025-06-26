package org.scoula.controller;

import org.scoula.dto.CompanyDTO;
import org.scoula.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CompanyDTO dto) {
        companyService.createCompany(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("회사 등록 완료");
    }

    @GetMapping
    public List<CompanyDTO> getAll() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public CompanyDTO getOne(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody CompanyDTO dto) {
        dto.setId(id);
        companyService.updateCompany(dto);
        return ResponseEntity.ok("회사 정보 수정 완료");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.ok("회사 삭제 완료");
    }
}