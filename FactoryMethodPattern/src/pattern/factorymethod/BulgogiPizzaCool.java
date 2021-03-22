package pattern.factorymethod;

public class BulgogiPizzaCool implements Pizza{
    BulgogiPizzaCool(){
        System.out.println("불고기 피자 쿨~");
    }
    @Override
    public void prepare() {}

    @Override
    public void bake() {}

    @Override
    public void box() {}
}
