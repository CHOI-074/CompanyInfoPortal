package org.scoula.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {
    private Long id;                // 게시글 고유 ID
    private String title;           // 게시글 제목
    private LocalDateTime createdDate; // 작성일시
    private String writer;          // 작성자 이름
    private Long userId;            // 작성자 user ID
}