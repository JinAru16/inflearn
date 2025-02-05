package poly.car1;

public class K3Car implements Car{
    @Override
    public void startEngine() {
        System.out.println("K3 Car.starting");
    }

    @Override
    public void offEngine(){
        System.out.println("K3 Car.off");
    }

    @Override
    public void pressAccelerator(){
        System.out.println("K3 Car.pressAccelerator");
    }
}
