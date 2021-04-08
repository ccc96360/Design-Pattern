package pattern.visitor;

public class Product {
    protected String name;
    protected int price;
    protected int num;
    /*
    * Getter & Setter
    * */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getTax(int tax){
        return price*num *tax/100;
    }
}
