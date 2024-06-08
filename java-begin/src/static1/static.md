<h2>Static</h2>

* static이 붙은 멤버 변수는 메서드 여역의 static영역에서 관리한다.
  * static이 붙은 변수는 인스턴스 영역에 생성되지 않는다. 대신 메서드 영억에서 이 변수를 관리한다.
* 예제의 Data3("A") 인스턴스르 생성하면 생성자가 호출된다.
* 생성자에서는 count++ 코드가 있다. count는 static이 붙은 정적 변수다. 정적 변수는 인스턴스 영역이 아니라
메서드 영역에서 관리하므로 이 경우에는 메서드 영역에 있는 count의 값이 하나 증가한다.

```java
public class DataCountMain3 {
    public static void main(String[] args) {
        Data3 data1 = new Data3("A");
        System.out.println("A couont =" + Data3.count);
    }
}
```
* 예제 코드를 보면 Data3.count <- 이것처럼 클래스명+'.'+스태틱변수명 이렇게 접근하면 된다.
* 붕어빵 틀에서 자체적으로 관리되는 변수라고 생각하면 된다.
