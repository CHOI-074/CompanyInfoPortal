-- Run from repository root: mysql -u root -p < src/goodjob.sql
CREATE DATABASE IF NOT EXISTS goodjob_db CHARACTER SET utf8mb4;
USE goodjob_db;
SOURCE src/main/resources/schema.sql;
