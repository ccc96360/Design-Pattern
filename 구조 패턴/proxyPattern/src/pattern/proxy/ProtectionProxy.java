package pattern.proxy;

public class ProtectionProxy implements Subject{
    private Subject real;
    private int permit;

    // Permit
    public static final int ACT1 = 0x01;
    public static final int ACT2 = 0x02;

    public ProtectionProxy(int permit) {
        this.permit = permit;
    }

    @Override
    public void action1() {
        if(this.permit == ProtectionProxy.ACT2) {
            if (real == null)
                this.real();
            System.out.println("Action1 메서드 전처리");
            real.action1();
            System.out.println("메서드 실행후 후처리");
        }
        else{
            System.out.println("실행 권한 없음");
        }
    }

    @Override
    public void action2() {
        if(this.permit == ProtectionProxy.ACT2) {
            if (real == null)
                this.real();
            System.out.println("Action2 메서드 전처리");
            real.action2();
            System.out.println("메서드 실행후 후처리");
        }
        else{
            System.out.println("실행 권한 없음");
        }
    }

    @Override
    public void otherAction() {
        if(this.permit == ProtectionProxy.ACT2) {
            if (real == null)
                this.real();
            real.otherAction();
            System.out.println("메서드 실행후 후처리");
        }
        else{
            System.out.println("실행 권한 없음");
        }
    }
    private void real(){
        this.real = new RealSubject();
    }
}
