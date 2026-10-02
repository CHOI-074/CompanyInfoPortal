# CompanyInfoPortal

기업·취업 정보 게시판을 주제로 만든 Java/Spring MVC 백엔드 학습 프로젝트입니다.
현재 최소 구현 범위는 **게시글·사용자 CRUD REST API**입니다.

## 기술과 구조

Java 17 · Spring MVC 5.3 · MyBatis 3.5 · MySQL · Gradle 8.8 · JUnit 5

```text
Controller → Service → MyBatis Mapper → Database
```

- 부모 Spring 컨텍스트에는 서비스·DB, Servlet 컨텍스트에는 컨트롤러 등록
- 실제 Mapper XML 경로 탐색, snake_case 컬럼 매핑과 Java 시간 직렬화
- app_user / post 스키마, 로그인 ID 유일성 및 게시글 사용자 외래키
- 입력 누락·길이 검사, 잘못된 요청 400, 없는 자원 404, 데이터 충돌 409
- 비밀번호 BCrypt 저장과 JSON 응답 제외

## 테스트와 빌드

Java 17을 준비한 뒤:

```bash
./gradlew test war
```

테스트는 외부 DB 없이 H2의 MySQL 호환 모드에서 실행됩니다.
실제 Controller→Service→Mapper→DB를 연결한 CRUD, 비밀번호 비노출, 중복 사용자,
입력 오류와 외래키 충돌을 검증합니다. **6개 테스트와 WAR 빌드를 로컬에서 통과했습니다.**
MySQL 서버와 외부 Tomcat에 배포하는 운영 조합은 아직 검증하지 않았습니다.

## MySQL 실행 준비

저장소 루트에서 MySQL CLI로 스키마를 준비합니다.

```bash
mysql -u root -p < src/goodjob.sql
```

기존 데이터가 있으면 백업 후 스키마를 확인하세요. 기존 `user` 테이블에서 `app_user`로
데이터를 이전하는 마이그레이션은 자동 수행하지 않습니다.
`CREATE TABLE IF NOT EXISTS`는 기존 테이블 구조를 변경하지 않습니다.

애플리케이션을 실행하는 프로세스에 아래 환경변수를 설정합니다.

- `DB_URL`: 기본값 jdbc:mysql://localhost:3306/goodjob_db?serverTimezone=Asia/Seoul
- `DB_USERNAME`: 기본값 portal
- `DB_PASSWORD`: 필수, 실제 비밀값을 저장소에 커밋하지 않음

해당 DB 계정은 별도로 생성하고 스키마 권한을 부여해야 합니다.
`build/libs/`의 WAR를 Java 17과 Servlet 4 / javax API를 지원하는 Tomcat 9에 배포합니다.
Tomcat 10의 jakarta API와는 호환되지 않습니다.
WAR 파일명에 따른 컨텍스트 경로 뒤에 아래 API 경로를 붙입니다.

## API

| 메서드 | 경로 | 동작 |
| --- | --- | --- |
| GET | /api/posts | 게시글 목록 |
| GET | /api/posts/{id} | 게시글 단건 |
| POST | /api/posts | 게시글 등록 |
| PUT | /api/posts/{id} | 게시글 수정 |
| DELETE | /api/posts/{id} | 게시글 삭제 |
| POST | /api/user | 사용자 등록 |
| GET | /api/user/{id} | 사용자 조회 |
| PUT | /api/user | 사용자 전체 필드 수정, id 필수 |
| DELETE | /api/user/{id} | 사용자 삭제 |

게시글 본문 예: `{"title":"채용 정보","writer":"tester"}`.
사용자 본문 필드: userId, password, name, nickname. 수정 요청에도 필수 필드를 전달합니다.
참조하는 게시글이 있는 사용자를 삭제하면 409를 반환합니다.

## 현재 범위

로그인·인증·권한 검사는 구현하지 않았으므로 로컬 학습·테스트용입니다.
비밀번호 해싱은 인증 구현을 의미하지 않습니다.
기업 정보 관리, 댓글, 첨부파일과 JSP 게시판 화면은 이번 최소 버전 범위에 포함하지 않습니다.

[통합 테스트](src/test/java/org/scoula/PortalApiTest.java) ·
[스키마](src/main/resources/schema.sql) ·
[게시글 API](src/main/java/org/scoula/controller/PostController.java) ·
[사용자 API](src/main/java/org/scoula/controller/UserController.java)
