package pattern.status;

public class On implements LightState{
    @Override
    public LightState pushButton() {
        System.out.println("전원을 끕니다.");
        return new Off();
    }
}
