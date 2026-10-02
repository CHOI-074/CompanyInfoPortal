# CompanyInfoPortal

기업·취업 정보 게시판을 주제로 Spring MVC의 요청 처리와 MyBatis 기반 데이터 접근을 학습한 Java 백엔드 프로젝트입니다. 현재 공개 코드에서 확인할 수 있는 중심 기능은 **게시글 CRUD와 사용자 기본 CRUD**입니다.

## 빠르게 살펴보기

- [게시글 API](src/main/java/org/scoula/controller/PostController.java): 목록·단건 조회, 등록, 수정, 삭제와 HTTP 상태 응답
- [서비스 계층](src/main/java/org/scoula/service/PostServiceImpl.java): 컨트롤러와 데이터 접근 계층 분리
- [Mapper](src/main/java/org/scoula/mapper/PostMapper.java): MyBatis 인터페이스
- [사용자 API](src/main/java/org/scoula/controller/UserController.java): 기본 CRUD 엔드포인트

## 기술 스택

| 영역 | 기술 |
| --- | --- |
| 언어 | Java 17 — build.gradle 기준 |
| 웹 | Spring Framework 5.3.37, Spring MVC, Servlet/JSP |
| 데이터 접근 | MyBatis, MySQL, HikariCP |
| 빌드·테스트 구성 | Gradle WAR, JUnit 5, Spring Test |

## 요청 처리 구조

```text
HTTP 요청 → Controller → Service → Mapper → MySQL
```

컨트롤러는 요청과 응답을, 서비스는 처리 흐름을, Mapper와 XML은 SQL 실행을 담당합니다.

## 게시글 API

| 메서드 | 경로 | 동작 |
| --- | --- | --- |
| GET | /api/posts | 전체 목록 |
| GET | /api/posts/{id} | 단건 조회, 없으면 404 |
| POST | /api/posts | 등록 |
| PUT | /api/posts/{id} | 수정, 대상이 없으면 404 |
| DELETE | /api/posts/{id} | 삭제 성공 시 204, 대상이 없으면 404 |

사용자 API는 `/api/user`와 `/api/user/{id}`에 정의돼 있습니다. 사용자 CRUD 자체를 로그인·인증 기능으로 의미하지는 않습니다.

## 실행 환경과 준비

1. Java 17과 MySQL을 준비합니다.
2. `goodjob_db`를 생성하고 [게시글 스키마](src/goodjob.sql)를 확인합니다.
3. 로컬 `src/main/resources/application.properties`의 `jdbc.driver`, `jdbc.url`, `jdbc.username`, `jdbc.password`를 설정합니다. 실제 접속 비밀값은 커밋하지 않습니다.
4. 아래 명령으로 빌드를 점검하고, 생성된 WAR를 호환되는 Servlet 컨테이너에 배포합니다.

```bash
./gradlew test
./gradlew war
```

위 명령은 점검 절차이며 현재 버전에서 실행 성공을 검증한 결과는 아닙니다.

## 현재 범위와 보완 사항

- 공개 SQL은 게시글 테이블 중심입니다. 사용자 API를 재현하려면 관련 스키마를 추가 확인해야 합니다.
- `RootConfig`의 Mapper XML 탐색 경로와 저장소 내 XML 위치를 일치시키는 점검이 필요합니다.
- 기업 유형·기업 상세·첨부파일의 완성된 관리 기능과 인증·인가·댓글은 현재 공개 코드로 완료를 확인하지 못했습니다.
- 기능 테스트와 실행 재현성을 보완한 뒤 배포 가능한 서비스로 확장할 계획입니다.
