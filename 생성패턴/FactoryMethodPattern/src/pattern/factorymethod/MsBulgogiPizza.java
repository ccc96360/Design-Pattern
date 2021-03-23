package pattern.factorymethod;

public class MsBulgogiPizza implements Pizza{

    MsBulgogiPizza(){
        System.out.println("더 맛있는 미스 불고기 피자!");
    }
    @Override
    public void prepare() { }

    @Override
    public void bake() { }

    @Override
    public void box() { }
}
