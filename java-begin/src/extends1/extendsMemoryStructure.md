# 상속과 메모리 구조
```java
// ElcectricCar extends Car
ElectricCar electricCar = new ElectricCar();
```
* 인스턴스를 생성을 해주면 메모리 영역에는 Car()과 ElectricCar() 두개가 같은 메모리공간에 생성된다.
* 참조값은 x001로 같지만 실제로 그 안에는 두가지 클래스 정보가 공존한다.
* 상속이라 해서 단순히 부모의 필드와 메서드만 물려 받는게 아니다. 부모 클래스도 함께 포함해서 생성된다.

## 호출
* electricCar.charge()를 호출하면 참조값을 확인하기 위해서 x001.charge()를 호출함.
* 그런데 상속관계의 경우 내부에 부모와 자식이 모두 존재함 -> 부모인 Car에서 charge()를 찾을지 자식인 ElectricCar에서 charge()를 찾을지 결정해야 함
* 이때는 호출하는 변수의 타입(클래스) 를 기준으로 선택함.
* 지금은 ElectricCar electricCar로  선언했으니 자식인 ElectricCart 인스턴스 내부에서 charge()를 먼저 찾는다.

## 호출2
* electricCar.move()호출
* ElectricCar로 변수타입을 선언하였으니 자식인 ElectricCar로 먼저 가서 move()를 찾아본다
* 없으니 부모인 Car()로 가서 move()를 찾는다
* 만약에 move()가 부모에 존재하지 않는다면 컴파일 에러를 낸다.

