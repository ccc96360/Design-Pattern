package pattern.proxy;

public class Proxy implements Subject{
    private Subject real;

    public Proxy(Subject real) {
        System.out.println("Proxy 생성.");
        this.real = real;
    }


    @Override
    public void action1() {
        System.out.println("Action1 메서드 전처리");
        real.action1();
        System.out.println("메서드 실행후 후처리");
    }

    @Override
    public void action2() {
        System.out.println("Action2 메서드 전처리");
        real.action2();
        System.out.println("메서드 실행후 후처리");
    }

    @Override
    public void otherAction() {}
}
