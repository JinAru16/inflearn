# 1. Message?
* 화면에 보이는 상품명, 가격, 수량 등 label에 있는 단어를 변경하려면 각가 html 파일에 들어가서 하나하나씩 각각 수정해야 한다.
* HTML에 메시지가 하드코딩 되어있기 때문이다.
* HTML 파일의 메시지를 한곳에서 관리 할 수 있는 기능을 메시지 기능이라 함
* message.properties라는 메시지 관리용 파일을 만들어서
```
item=상품
item.id=상품 ID
item.itemName=상품명
item.price=가격
item.quantity=수량
```
이런식으로 HTML에 해당 데이터를 key값으로 불러서 사용하는것이다.
```
addForm.html
<label for="itemName" th:text="#{item.itemName}"></label>

editForm.html
<label for="itemName" th:text="#{item.itemName}"></label>
```

# 2. 국제화
- 메시지에서 설명한 메시지 파일(message.properties)을 각 나라별로 별도로 관리하여 서비스를 국제화 할 수 있다.
```
message_en.properties

item=Item
item.id=Item Id
item.itemName = Item Name
item.price=price
item.quantity=quantiy
```
```
message_kor.properties

item=상품
item.id=상품 ID
item.itemName=상품명
item.price=가격
item.quantity=수량
```
* 영어를 사용하는 사람에겐 message_en.properties를 사용하고, 한국어 사용자는 message_kor.properties를 사용하게 개발한다.
* 어떤 국가에서 접근했는지 확인하는 방법은 HTTP `accept-language`헤더 값을 사용하거나 사용자가 직접 언어를 선택하도록 하고, 쿠키등을 사용하여 처리한다
* 국제화 기능은 스프링에서 편리하게 제공함.


# 3. 적용방법
## 3.1 스프링
* 스프링에서는 MessageSource를 스프링 빈으로 등록하면 된다.
* MessageSource는 인터페이스다. -> 구현체인 ResourceBundleMessageSource를 스프링빈으로 등록하면됨
```
@SpringBootApplication
public class ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new
				ResourceBundleMessageSource();
		messageSource.setBasenames("messages", "errors");
		messageSource.setDefaultEncoding("utf-8");
		return messageSource;
	}
}

```
* basenames : 설정 파일의 이름을 지정한다.
  * messages 로 지정하면 messages.properties 파일을 읽어서 사용한다.
    * 추가로 국제화 기능을 적용하려면 messages_en.properties , messages_ko.properties 와 같이
    파일명 마지막에 언어 정보를 주면된다. 만약 찾을 수 있는 국제화 파일이 없으면
    messages.properties (언어정보가 없는 파일명)를 기본으로 사용한다.
  * 파일의 위치는 /resources/messages.properties 에 두면 된다.
  * 여러 파일을 한번에 지정할 수 있다. 여기서는 messages , errors 둘을 지정했다.
* defaultEncoding : 인코딩 정보를 지정한다. utf-8 을 사용하면 된다

## 3.2 스프링부트
- 스프링부트에서는 부트가 MessageSource를 자동으로 스프링 빈으로 등록함
- application.yml이나 application.properties에
```
spring.messages.basename=messages,config.i18n.messages
```
이렇게 적어주면 끝남

스프링 부트 메시지 소스 기본 값
* spring.messages.basename=messages
* MessageSource 를 스프링 빈으로 등록하지 않고, 스프링 부트와 관련된 별도의 설정을 하지 않으면
messages 라는 이름으로 기본 등록된다. 따라서 messages_en.properties ,
messages_ko.properties , messages.properties 파일만 등록하면 자동으로 인식된다.

# 4. 사용법
## 4.1 test코드로 사용법 익히기
```java
@SpringBootTest
public class MessageSourceTest {
    @Autowired
    MessageSource ms;

    @Test
    void helloMessage(){
        ms.getMessage("hello", null, null);
    //                 code,  args, locale
      
    }
}
```
* 여기선 locale이 null임 -> default로 messages.properties가 선택됨