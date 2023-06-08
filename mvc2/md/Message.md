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
