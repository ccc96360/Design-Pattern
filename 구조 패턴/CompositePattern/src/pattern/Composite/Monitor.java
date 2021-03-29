package pattern.Composite;

public class Monitor {
    private Screen screen;
    private String name = "모니터";

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getSize(){
        return screen.getInch();
    }
}

