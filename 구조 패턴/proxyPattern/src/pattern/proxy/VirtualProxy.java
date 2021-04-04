package pattern.proxy;

public class VirtualProxy implements Subject{
    private Subject real;

    public VirtualProxy() {
        System.out.println("가상 프록시 생성");
    }

    @Override
    public void action1() {
        if(real == null)
            this.real();
        System.out.println("Action1 메서드 전처리");
        real.action1();
        System.out.println("메서드 실행후 후처리");
    }

    @Override
    public void action2() {
        if(real == null)
            this.real();
        System.out.println("Action2 메서드 전처리");
        real.action2();
        System.out.println("메서드 실행후 후처리");
    }

    @Override
    public void otherAction() {
        if(real == null)
            this.real();
        real.otherAction();
        System.out.println("메서드 실행후 후처리");
    }
    private void real(){
        this.real = new RealSubject();
    }
}
