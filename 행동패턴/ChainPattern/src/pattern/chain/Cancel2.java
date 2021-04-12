package pattern.chain;

public class Cancel2 extends Chain2{
    @Override
    public void execute(String event){
        System.out.println("주문을 취소합니다.");
    }

    @Override
    boolean resolve(String event) {
        return event.equals("cancel");
    }
}
