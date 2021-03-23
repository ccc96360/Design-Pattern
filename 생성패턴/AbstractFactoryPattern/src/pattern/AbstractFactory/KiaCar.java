package pattern.AbstractFactory;

public class KiaCar implements Car{
    Factory CountryFactory;
    Tire tire;
    Door door;

    public KiaCar(Factory countryFactory){
        this.CountryFactory = countryFactory;
    }

    @Override
    public void build() {
        System.out.println("기아차 조립합니다.");
        tire = CountryFactory.createTire();
        door = CountryFactory.createDoor();
    }
}
