package poly.car1;

public class Model3Car implements Car{
    @Override
    public void startEngine() {
        System.out.println("Model3Car.starting");
    }

    @Override
    public void offEngine() {
        System.out.println("Model3Car.off");
    }

    @Override
    public void pressAccelerator() {
        System.out.println("Model3Car.pressAccelerator");
    }
}
