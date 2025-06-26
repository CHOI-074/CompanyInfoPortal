package org.scoula.dto;

import lombok.Data;

@Data
public class CompanyDTO {
    private Long id;
    private String name;
    private String type;
    private String industry;
    private String url;
}