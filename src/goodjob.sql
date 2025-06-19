use goodjob_db;

CREATE TABLE post
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    title        VARCHAR(255) NOT NULL,
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    writer       VARCHAR(100) NOT NULL,
    user_id      BIGINT
);

# 더미데이터
INSERT INTO post (title, created_date, writer, user_id)
VALUES
('2025 공공기관 채용 일정 공유', '2025-06-01 10:30:00', '홍길동', 1),
('사기업 연봉 비교 자료입니다.', '2025-06-05 14:00:00', '이영희', 2),
('공기업 vs 사기업 선택 기준', '2025-06-10 09:45:00', '김철수', 3),
('2025 상반기 합격 후기 모음', '2025-06-15 18:20:00', '박지은', 1),
('사기업 취업을 위한 준비 팁', '2025-06-18 11:10:00', '정우성', 2);
