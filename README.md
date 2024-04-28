# theCommerce


## 실행방법
1. Git을 통해 소스 코드를 클론합니다.
2. Run Configuration에 org.example.thecommerce.TheCommerceApplication를 설정합니다.
3. TheCommerceApplication을 실행합니다.

## 기술스택
* JDK 버전: 1.8
* 프레임워크: Spring Boot2.7
* 데이터베이스: Oracle Cloud
* ORM: JPA
* 빌드 도구: Maven

## 사용한 라이브러리
* Spring Boot Starter Data JPA
* Spring Boot Starter Web
* Spring Boot Starter Web Services
* Lombok
* Spring Boot Starter Test
* Spring Boot Starter Security
* Springfox Swagger 2
* Hibernate

## 동작방식

### 회원가입
1. 유저가 입력한 `UserDto`를 기반으로 회원 정보를 조회합니다.
2. `checkUserValid` 메소드를 통해 중복된 아이디인지 올바른 형식의 아이디인지 확인합니다.
3. 아이디 검증이 완료되면 `convertDtoToEntity` 메소드를 통해 PK, 가입일자를 추가하고 패스워드를 암호화하여 데이터베이스에 저장합니다.
  
### 회원 목록 조회
1. 유저가 입력한 쿼리스트링 page(페이지번호), pageSize(한 페이지에 표시될 수 있는 최대 회원 수), sort(정렬방식, enrollDate 또는 id)를 기반으로 회원 목록을 조회합니다.
2. 페이지 번호와 페이지 크기가 유효한지, 또는 정렬방식을 올바르게 썼는지 검사합니다.
3. 검증이 완료되면 PK와 PWD를 제외한 나머지 정보를 `convertUserListDto` 메소드를 통해 `UserListDto`로 전환하여 리턴합니다.

### 회원 정보 수정
1. 유저가 입력한 아이디를 기반으로 회원 정보를 수정합니다.
2. 유저가 입력한 아이디가 존재하는 회원인지 확인하고, 입력한 값을 `userUpdateDto`를 통해 데이터베이스를 수정합니다.

## 추가사항
### 스웨거를 통해 API 문서화가 가능합니다. 
### http://localhost:8885/swagger-ui.html
