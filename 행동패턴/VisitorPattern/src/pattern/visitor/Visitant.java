package pattern.visitor;

public class Visitant implements Visitor{

    private int total;
    private int num;

    public Visitant() {
        total = 0;
        num = 0;
    }

    @Override
    public void order(Visitable visitable) {
        System.out.println("==상품 내역==");
        if(visitable instanceof Cart){
            System.out.print("상품명" + ((Cart) visitable).getName());
            System.out.print(" 수량:" + ((Cart) visitable).getNum());
            int cartTotal = ((Cart) visitable).getPrice() * ((Cart) visitable).getNum();
            System.out.println(" 가격:" + cartTotal);

            total += cartTotal;
            System.out.println("합계" + this.total + ", 주문 건수" + ++num);

        }
    }
}
