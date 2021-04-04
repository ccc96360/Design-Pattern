package pattern.proxy;

public class RealSubject implements Subject{
    public RealSubject() {
        System.out.println("Real Subject 생성");
    }
    @Override
    public void action1(){
        System.out.println("Action1 실행");
    }
    @Override
    public void action2(){
        System.out.println("Action2 실행");
    }
    @Override
    public void otherAction(){
        System.out.println("Other Action");
    }
}
