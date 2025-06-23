use goodjob_db;

CREATE TABLE post
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    title        VARCHAR(255) NOT NULL,
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    writer       VARCHAR(100) NOT NULL,
    user_id      BIGINT
);

CREATE TABLE company (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         type VARCHAR(20) NOT NULL,         -- 공기업 / 사기업
                         industry VARCHAR(100),
                         url VARCHAR(255)
);

CREATE TABLE company_detail (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                company_id BIGINT NOT NULL UNIQUE,
                                description TEXT,
                                created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
                                FOREIGN KEY (company_id) REFERENCES company(id)
);

# 더미데이터
INSERT INTO post (title, created_date, writer, user_id)
VALUES
('2025 공공기관 채용 일정 공유', '2025-06-01 10:30:00', '홍길동', 1),
('사기업 연봉 비교 자료입니다.', '2025-06-05 14:00:00', '이영희', 2),
('공기업 vs 사기업 선택 기준', '2025-06-10 09:45:00', '김철수', 3),
('2025 상반기 합격 후기 모음', '2025-06-15 18:20:00', '박지은', 1),
('사기업 취업을 위한 준비 팁', '2025-06-18 11:10:00', '정우성', 2);

INSERT INTO company (name, type, industry, url)
VALUES
    ('한국전력공사', '공기업', '전기 에너지', 'https://www.kepco.co.kr'),
    ('삼성전자', '사기업', '전자 제품 제조', 'https://www.samsung.com'),
    ('한국도로공사', '공기업', '도로 및 교통', 'https://www.ex.co.kr'),
    ('네이버', '사기업', 'IT / 포털 서비스', 'https://www.naver.com'),
    ('한국수자원공사', '공기업', '수자원 관리', 'https://www.kwater.or.kr');

INSERT INTO company_detail (company_id, description)
VALUES
    (1, '대한민국을 대표하는 전력 공급 공기업입니다. 전력 인프라를 책임지고 있습니다.'),
    (2, '글로벌 전자제품 제조업체로, 반도체 및 스마트폰 분야의 리더입니다.'),
    (3, '도로 및 교통 인프라를 관리하며 고속도로 운영을 책임지는 공기업입니다.'),
    (4, '대한민국 대표 포털 사이트 운영사로 다양한 IT 서비스를 제공합니다.'),
    (5, '국내 수자원 관리 및 홍수 예방 등의 역할을 하는 공공기관입니다.');