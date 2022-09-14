MVC 예제실습

게시물을 다루기 위한 모델, 뷰, 컨트롤러 생성

모델 설계 시 유지보수 용이를 위한 JAVA의 interface, abstract class를 적절히 활용
스프링 기능중 하나인 IOC를 활용하기 위한 Bean 등록, @Autowired, @Service 어노테이션 활용

뷰를 구성하기 위해 jsp 활용, 프론트엔드 디자인은 고려하지 않음
get, post요청에 필요한 인수들을 적절히 입력받을 수 있게 구성
put, delete의 경우 postman API활용

컨트롤러에서 요청에 의한 값을 체크하기 위해 @Valid 어노테이션을 활용하여 값 검증, CustomValidation 생성
컨트롤러에서 별도의 비즈니스로직이 필요한 경우 service패키지에 @Service 클래스를 요청함

Jnit 실습을 위해 Validation기능 테스트를 중심으로 테스트 클래스 설계

REST API 구축을 위한 CRUD지향의 컨트롤러설계
기능에 대한 적절한 주소를 mapping, PathVariable에 의한 컨트롤러 제어

AOP를 활용한 로깅기능 실행 메소드, 객체 추적
