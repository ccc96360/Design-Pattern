package pattern.status;

public class StateOrdered implements State {
    @Override
    public void process() {
        System.out.println("주문 완료");
    }
}
