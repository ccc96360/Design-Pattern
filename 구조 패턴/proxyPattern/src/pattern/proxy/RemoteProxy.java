package pattern.proxy;

public class RemoteProxy implements Subject{
    private Subject real;

    public RemoteProxy(Subject real) {
        this.real = real;
    }

    @Override
    public void action1() {
        System.out.println("Action1은 기능이 대체되었습니다.");
    }

    @Override
    public void action2() {
        real.action2();
    }

    @Override
    public void otherAction() {
        real.otherAction();
    }
}
