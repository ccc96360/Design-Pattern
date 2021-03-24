package pattern.Builder;

public class Cpu {
    private String model = "아주 좋은 CPU";

    public Cpu() {

    }

    public Cpu(String model){
        this.model = model;
    }

    public void setModel(String model){
        this.model = model;
    }

    @Override
    public String toString() {
        return this.model;
    }
}
