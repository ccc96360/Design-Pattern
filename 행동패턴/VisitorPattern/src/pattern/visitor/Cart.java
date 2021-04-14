package pattern.visitor;

public class Cart extends Product implements Visitable{
    public Cart(String name, int price, int num) {
        this.num = num;
        this.price = price;
        this.name = name;
    }

    @Override
    public int getTax(int tax) {
        return this.price*this.num*tax/100;
    }

    public void list(){
        System.out.println(name+", 수량:"+num+", 가격:"+num*price);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.order(this);
    }
}
