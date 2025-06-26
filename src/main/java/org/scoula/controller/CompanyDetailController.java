package org.scoula.controller;

import lombok.RequiredArgsConstructor;
import org.scoula.dto.CompanyDetailDTO;
import org.scoula.service.CompanyDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/company-details")
@RequiredArgsConstructor
public class CompanyDetailController {


    private final CompanyDetailService service;

    // 전체 조회
    @GetMapping
    public ResponseEntity<List<CompanyDetailDTO>> getAllDetails() {
        return ResponseEntity.ok(service.getAllDetails());
    }

    // 회사 ID로 조회
    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyDetailDTO> getDetail(@PathVariable Long companyId) {
        CompanyDetailDTO dto = service.getDetailByCompanyId(companyId);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    // 등록
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody CompanyDetailDTO dto) {
        service.insertCompanyDetail(dto);
        return ResponseEntity.ok("등록 성공");
    }

    // 수정
    @PutMapping("/{companyId}")
    public ResponseEntity<String> update(@PathVariable Long companyId, @RequestBody CompanyDetailDTO dto) {
        dto.setCompanyId(companyId);  // URL 값과 DTO 동기화
        service.updateCompanyDetail(dto);
        return ResponseEntity.ok("수정 성공");
    }

    // 삭제
    @DeleteMapping("/{companyId}")
    public ResponseEntity<String> delete(@PathVariable Long companyId) {
        service.deleteCompanyDetail(companyId);
        return ResponseEntity.ok("삭제 성공");
    }
}

