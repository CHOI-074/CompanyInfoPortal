# 🏢 CompanyInfoPortal

공기업 및 사기업의 정보와 관련 게시글을 관리할 수 있는 통합 게시판 시스템입니다. 
기업 분류, 상세 기업 정보, 사용자 게시글 작성을 포함하며, 추후 사용자/댓글 시스템으로 확장 예정입니다.

---

## 📌 주요 기능

- ✅ 기업 정보 등록 / 수정 / 조회
- ✅ 기업별 게시글 CRUD
- ✅ 게시글 업로드 및 조회
- 🔄 추후 댓글 및 사용자 인증 기능 연동 가능

---

## 🗂️ 기술 스택

| 구분 | 기술 |
|------|------|
| Language | Java 8 |
| Framework | Spring Framework (MVC) |
| ORM | MyBatis |
| DB | MySQL  |
| Build Tool | Gradle |
| API Test | Postman |
| Version Control | Git, GitHub |

---

## 🏛️ ERD 구조 (4개 테이블)

1. `company` – 기업 정보
2. `CompanyDetail` – 기업 상세 정보
3. `User` – 회원 정보
4. `Post` – 게시글 

추후 `comment` 테이블 확장 고려 가능

---

## 📁 프로젝트 구조
