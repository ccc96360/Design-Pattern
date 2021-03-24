package pattern.Builder;

public abstract class ComputerBuilder {
    protected Computer computer;

    abstract public ComputerBuilder setCpu(String model);
    abstract public ComputerBuilder setRam(int size);
    abstract public ComputerBuilder setStorage(int size);

    abstract public Computer build();
}
