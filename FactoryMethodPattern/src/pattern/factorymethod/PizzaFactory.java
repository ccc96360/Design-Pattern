package pattern.factorymethod;

abstract public class PizzaFactory {
    public final Pizza create(String type){
        Pizza p = createPizza(type);
        p.prepare();
        p.bake();
        p.box();
        return p;
    }
    abstract public Pizza createPizza(String type);
}
