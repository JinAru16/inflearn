package poly.ex3;

import poly.ex2.Animal;

public class AbstractMain {
    public static void main(String[] args) {
        //추상 클래스 생성물가
       // AbstractAnimal animal = new AbstractAnimal() {} Class 'Anonymous class derived from AbstractAnimal' must either be declared abstract or implement abstract method 'sound()' in 'AbstractAnimal'
        Dog dog = new Dog();
        Cat cat = new Cat();
        Cow cow = new Cow();

        cat.sound();
        cat.move();

        soundAnimal(cat);
        soundAnimal(dog);
        soundAnimal(cow);

    }
    private static void soundAnimal(AbstractAnimal animal) {
        System.out.println("동물소리 테스트 시작");
        animal.sound();
        System.out.println("동물소리 테스트 종료");
    }
}
