package pattern.Decorator;

public class i7 extends Decorator{
    private Component base;
    public i7(Component base) {
        this.base = base;
    }

    @Override
    public String product() {
        return this.base.product()+",i7";
    }

    @Override
    public int price() {
        return this.base.price()+475000;
    }
}
