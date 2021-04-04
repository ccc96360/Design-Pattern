package pattern.proxy;

public class RemoteRealSubject implements Subject{
    @Override
    public void action1() {
        System.out.println("실제 action1");
    }

    @Override
    public void action2() {
        System.out.println("실제 action2");
    }

    @Override
    public void otherAction() {
        System.out.println("실제 otherAction");
    }
}
