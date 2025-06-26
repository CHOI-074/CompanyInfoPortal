package org.scoula.domain;


import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PostVO {
    private Long id;                // 고유 ID
    private String title;           // 제목
    private LocalDateTime createdDate; // 등록 일시
    private String writer;          // 작성자
    private Long userId;            // 작성자 ID (외래키)
}