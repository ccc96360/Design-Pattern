package pattern.chain;

public class Order extends Chain {
    @Override
    public void execute(String event){
        if(event.equals("order"))
            System.out.println("주문을 처리합니다.");
        else
            this.next.execute(event);
    }
}
