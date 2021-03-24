package pattern.Builder;

public class MyComputerBuilder extends ComputerBuilder{

    MyComputerBuilder(){
        computer = new Computer();
    }

    @Override
    public ComputerBuilder setCpu(String model) {
        computer.setCpu(model);
        return this;
    }

    @Override
    public ComputerBuilder setRam(int size) {
        computer.setRam(size);
        return this;
    }

    @Override
    public ComputerBuilder setStorage(int size) {
        computer.setStorage(size);
        return this;
    }

    @Override
    public Computer build() {
        return computer;
    }
}
