package pattern.factorymethod;

public class MsPizza extends PizzaFactory {
    @Override
    public Pizza createPizza(String type) {
        if("cheese".equals(type)){
            return new MsCheesePizza();
        }
        else if("bulgogi".equals(type)){
            return new MsBulgogiPizza();
        }
        return null;
    }
}

