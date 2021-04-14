package pattern.Decorator;

public abstract class Decorator implements Component{
    @Override
    public abstract String product();

    @Override
    public abstract int price();
}
