CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      user_id VARCHAR(50) NOT NULL UNIQUE,
                      password VARCHAR(200) NOT NULL,
                      name VARCHAR(100) NOT NULL,
                      nickname VARCHAR(50) NOT NULL
);
