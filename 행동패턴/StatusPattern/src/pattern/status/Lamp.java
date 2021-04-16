package pattern.status;

public class Lamp {
    LightState state;

    public Lamp() {
        state = new Off();
    }
    public void pushButton(){
        this.state = this.state.pushButton();
    }
}
