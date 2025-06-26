package org.scoula.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CompanyDetailDTO {

    private Long id;
    private Long companyId;
    private String description;
    private LocalDateTime createdDate;
}
