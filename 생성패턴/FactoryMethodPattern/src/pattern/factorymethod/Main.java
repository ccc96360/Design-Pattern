package pattern.factorymethod;

public class Main {

    public static void main(String[] args) {
        PizzaFactory msPizza = new MsPizza();
        PizzaFactory pizzaCool = new PizzaCool();
        Pizza msCheesePizza = msPizza.createPizza("cheese");
        Pizza msBulgogiPizza = msPizza.createPizza("bulgogi");
        Pizza cheesePizzaCool = pizzaCool.createPizza("cheese");
        Pizza bulgogiPizzaCool = pizzaCool.createPizza("bulgogi");
    }
}
