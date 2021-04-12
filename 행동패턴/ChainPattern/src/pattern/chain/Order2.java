package pattern.chain;

public class Order2 extends Chain2 {
    @Override
    public void execute(String event){
        System.out.println("주문을 처리합니다.");
    }

    @Override
    boolean resolve(String event) {
        return event.equals("order");
    }
}
