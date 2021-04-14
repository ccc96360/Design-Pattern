package pattern.Decorator;

public class ssd256 extends Decorator{
    private Component base;

    public ssd256(Component base) {
        this.base = base;
    }

    @Override
    public String product() {
        return this.base.product()+",ssd256";
    }

    @Override
    public int price() {
        return this.base.price()+110000;
    }
}
