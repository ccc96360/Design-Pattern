package pattern.factorymethod;

public class PizzaCool extends PizzaFactory{
    @Override
    public Pizza createPizza(String type) {
        if("cheese".equals(type)){
            return new CheesePizzaCool();
        }
        else if("bulgogi".equals(type)){
            return new BulgogiPizzaCool();
        }
        return null;
    }
}
