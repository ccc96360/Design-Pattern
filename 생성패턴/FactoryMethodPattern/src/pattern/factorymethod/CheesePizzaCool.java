package pattern.factorymethod;

public class CheesePizzaCool implements Pizza{
    CheesePizzaCool(){
        System.out.println("치즈 피자 쿨~");
    }
    @Override
    public void prepare() {}

    @Override
    public void bake() {}

    @Override
    public void box() {}
}
