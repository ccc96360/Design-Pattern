package pattern.AbstractFactory;

public class FordCar implements Car{
    Factory CountryFactory;
    Tire tire;
    Door door;

    public FordCar(Factory countryFactory){
        this.CountryFactory = countryFactory;
    }
    @Override
    public void build() {
        System.out.println("Ford 조립!");
        this.tire = CountryFactory.createTire();
        this.door = CountryFactory.createDoor();
    }
}
