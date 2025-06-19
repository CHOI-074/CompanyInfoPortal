CREATE TABLE post
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    title        VARCHAR(255) NOT NULL,
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    writer       VARCHAR(100) NOT NULL,
    user_id      BIGINT
)