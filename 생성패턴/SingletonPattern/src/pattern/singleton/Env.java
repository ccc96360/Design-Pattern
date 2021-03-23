package pattern.singleton;

public class Env extends Config{
    private static Env self = null;

    protected Env(){
        System.out.println("Env 객체를 생성합니다.");
    }
    public void setting(){
        System.out.println("시스템 환경을 설정합니다. ㅎㅎ");
    }
    public static Env getInstance(){
        if(self == null){
            System.out.println("Env 객체 생성 시작.");
            self = new Env();
        }
        System.out.println("Env 객체 반환");
        return self;
    }
}
