package pattern.factorymethod;

public class MsCheesePizza implements Pizza{
    MsCheesePizza(){
        System.out.println("맛있는 미스 치즈 피자~");
    }

    @Override
    public void prepare() {}
    @Override
    public void bake() {}
    @Override
    public void box() {}
}
