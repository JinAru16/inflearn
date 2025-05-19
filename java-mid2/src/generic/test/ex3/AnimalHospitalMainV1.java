package generic.test.ex3;

import generic.animal.Animal;
import generic.animal.Cat;
import generic.animal.Dog;

public class AnimalHospitalMainV1 {

    public static void main(String[] args) {
        AnimalHospitalV1 dogHospital = new AnimalHospitalV1();
        AnimalHospitalV1 catHospital = new AnimalHospitalV1();

        Dog dog = new Dog("멍멍이1", 100);
        Cat cat = new Cat("냐옹이", 300);

        // 개병원
        dogHospital.set(dog);
        dogHospital.checkup();

        // 고양이 병원
        catHospital.set(cat);
        catHospital.checkup();

        // 문제1. 개 병원에 고양이 전달.
         dogHospital.set(cat); // 매개변후 체크 실패 : 컴파일 오류가 발생하지않음.

        // 문제2. 개타입을 반환
        dogHospital.set(dog);
        Dog bigger = (Dog)dogHospital.bigger(new Dog("멍멍이2", 200));
        System.out.println("bigger : " + bigger);

        /*
        타입 안전성의 문제가 있음
        개병원에 고양이를 받아도 컴파일 에러가 발생하지않는다.
        실수로 고양이를 입력했는데, 개를 반환하는 상황이라면 캐스팅 예외가 발생한다.
         */
    }
}
