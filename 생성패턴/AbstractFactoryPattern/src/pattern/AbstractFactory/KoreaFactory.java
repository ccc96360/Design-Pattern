package pattern.AbstractFactory;

public class KoreaFactory extends Factory{
    @Override
    public Tire createTire() {
        return new KoreaTire();
    }

    @Override
    public Door createDoor() {
        return new KoreaDoor();
    }
}
