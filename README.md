# Backend - Spring 채용 과제
### 과제명 - 영화관 좌석예매 프로그램
* 기능설명
    1. 예매를 위해 서버에 로그인한다.
    2. 예매자는 선호하는 좌석을 설정할 수 있다.
    3. 선호좌석 중 예매 가능한 좌석정보를 제공한다.
    4. 좌석별 금액은 상이하다. (앞열 / 중간열 / 뒷열에 따라 가격상이)
    5. 예매 완료 시 티켓을 발행한다.
    6. 티켓정보로 예매한 내역을 조회할 수 있다.

### 프로그램 구현 환경구성 조건
* Database 는 `H2` DB를 이용한다. (In-Memory Database로 활용)
* 서버 구동 시마다 DB Schema가 초기화되도록 구성한다. (hibernate 옵션 활용)
* 서버 구동 시 기초데이터가 생성되도록 작성한다. [기초데이터 생성규칙 참조](#initDataRules)
* 작성된 API는 `Swagger`로 기술한다. (`Spring Fox` or `OpenAPI` 등 편리한 툴 사용)
* 인증(로그인)을 제외한 API 호출 시 `JWT` 를 이용한 보안이 전제되도록 구성한다.
  * 초기 구성은 모든 요청에 대해 `permitAll` 로 설정되어 있다.

### 프로그램 구현 가산점
* API 작성 시 Spring `Webflux`를 이용해 **event stream** 방식의 응답이 가능하도록 구현
* `WebTestClient` 를 이용한 테스트 코드 작성

### Entity 필수 구성정보
<pre>
1. 아래 Entity 의 Relation 관계를 재정의하여 구현해도 무관하다.
2. 과제 완성을 위해 아래 Entity 외 추가 Entity 생성이 필요하다.
</pre>
* 사용자
* 영화
* 상영관
* 좌석
* 예매정보

### <a id="initDataRules" name="initDataRules"></a>기초데이터 생성 규칙
* 사용자 데이터 3건 생성 (booker1, booker2, booker3)
  * 비밀번호는 아이디와 동일하게 설정하되, Base64 알고리즘으로 암호화해서 저장
* 영화 데이터 2건 생성
* 상영관 데이터 2건 생성
* 좌석 정보 생성 (상영관A: 10x8의 좌석배치 구조 / 상영관B: 8x7의 좌석배치 구조)

### API 호출 예상 결과 (Http Status Code)
1. 로그인 하지 않고 영화정보 조회 시도 - 403
2. 로그인 정보 입력 후 로그인 - 200
3. 전체 영화 리스트 조회 - 200
4. 영화별 상영관 정보 조회 - 200
5. 상영관 좌석정보 조회 - 200
6. 이미 예매가 확정된 좌석의 예매를 시도 - 409
7. 빈 좌석의 예매를 진행 > 할당된 좌석 금액과 미일치 - 422
8. 빈 좌석의 예매를 진행 > 할당된 좌석 금액과 일치 - 200
9. 완료된 예매정보 조회 - 200