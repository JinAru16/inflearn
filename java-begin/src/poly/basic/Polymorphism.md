## 다형성 문법

```java
public class Parent {
    public void parentMethod(){
        
    }
}

public class Child extends Parent{
    public void childMethod(){
        
    }
}
public class Main{
    Parent poly = new Child(); //Child인스턴스를 Parent타입으로 호출이 가능
    poly.parentMethod(); 
    poly.childMethod();// 부모타입에서는 자식의 메서드 참조 불가능.
    
    Child poly2 = new Parent(); // 자식은 부모를 담을수 없다. 컴파일 오류 발생
}

```
* 자바의 경우 자식타입에서는 부모타입을 참조 가능(밑에서 위로는 참조가 가능)
* 부모타입에서는 자식타입을 참조가 불가능(위에서 밑으로는 참조가 불가능)
* if 부모타입에서 자식 메서드를 호출하고싶다면 캐스팅이 필요