package poly.ex5;

import poly.ex4.AbstractAnimal;

public class InterfaceMain {
    public static void main(String[] args) {
       // InterfaceAnimal interfaceAnimal = new InterfaceAnimal() 인터페이스도 추상의 일종이기 때문에 생성자 사용이 불가능!
        Cat cat = new Cat();
        Dog dog = new Dog();
        Cow cow = new Cow();
        soundAnimal(cat);
        soundAnimal(dog);
        soundAnimal(cow);
    }

    private static void soundAnimal(InterfaceAnimal animal) {
        System.out.println("동물소리 테스트 시작");
        animal.sound();
        System.out.println("동물소리 테스트 종료");
    }
}
