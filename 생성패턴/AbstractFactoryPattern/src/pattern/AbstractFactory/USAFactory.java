package pattern.AbstractFactory;

public class USAFactory extends Factory{
    @Override
    public Tire createTire() {
        return new USATire();
    }

    @Override
    public Door createDoor() {
        return new USADoor();
    }
}
