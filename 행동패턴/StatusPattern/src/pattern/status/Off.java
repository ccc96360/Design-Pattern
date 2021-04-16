package pattern.status;

public class Off implements LightState{
    @Override
    public LightState pushButton() {
        System.out.println("전원을 킵니다.");
        return new On();
    }
}
