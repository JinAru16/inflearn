package poly.ex6;


import poly.ex6.AbstractAnimal;

public class Dog extends AbstractAnimal {

    @Override
    public void sound() {
        System.out.println("멍멍");
    }
}
