package pattern.chain;

public class Cancel extends Chain{
    @Override
    public void execute(String event){
        if(event.equals("cancel"))
            System.out.println("주문을 취소합니다.");
        else
            this.next.execute(event);
    }
}
