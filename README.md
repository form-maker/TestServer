# TestServer

- [http://43.201.62.209/swagger-ui.html](http://43.201.62.209/swagger-ui.html)
- [http://43.201.62.209/](http://43.201.62.209/)

## 테스트 Api 설명

### 회원가입

회원 가입 시 아래 형태가 이미 존재하듯 구현

- loginId : test1
- username : 범준

위 정보로 회원가입을 시도하면 예외 발생

### loginId 중복 확인

아래 정보가 있다고 가정, 만약 동일한 loginId로 시도 시 예외 발생

- loginId : test1

이외에 모든 형태는 성공

### username 중복 확인

아래 정보가 있다고 가정, 만약 동일한 username으로 시도 시 예외 발생

- username :  범준

이외에 모든 형태는 성공

### 로그인

아래 정보로만 로그인 시도, 이외에 시도는 없는 계정 예외 발생

- loginId : test
- password : qwer1234

로그인 성공 시 헤더에 토큰을 담아서 응답 됨

### 유저 정보 요청(토큰 확인)

토큰이 있다면 true, 없다면 false

### 전체 조회(사용자 입장)

총 10개의 카드 데이터 반환

생성일과 deadLine의 경우 모두 다르게 설정됨

### 설문 생성

기존 response에는 data가 없으나 확인용으로 입력한 내용을 그대로 내보내고 있음

### 전체 조회(설문 생성자 입장)

총 10개의 카드 데이터 반환

생성일과 deadLine의 경우 모두 다르게 설정됨

마감여부의 조금씩 다르게 설정

### 설문 수정

생성과 마찬가지로 기존 data는 없으나 확인용으로 입력내용 반환

### 설문 삭제

삭제 요청시 삭제 시도한 id만 data에 담아서 보냄(원래는 data에 담지 않음)

### 설문 조회

보내지는 questionId에 따라 각 문항의 형태에 맞게 데이터 반환

1 → MultipleChoice(객관식, 다중 선택)

2 → SingleChoice(객관식, 단일 선택)

3 → Slide(슬라이드)

4 → Rank(순위)

5 → ShortDescriptive(주관, 단답형)

6 → LongDescriptive(주관식, 장문형)

7 → Star(별점)

### 설문 응답

마찬가지로 response에  data는 비어있으나 응답 내용을 반환
