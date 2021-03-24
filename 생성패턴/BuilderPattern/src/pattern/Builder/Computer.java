package pattern.Builder;

public class Computer {
    private Cpu cpu;
    private Ram ram;
    private Storage storage;

    public Computer(){
        this.cpu = new Cpu();
        this.ram = new Ram();
        this.storage = new Storage();
        System.out.println("Computer 객체 생성");
    }
    public void setCpu(String model){
        this.cpu.setModel(model);
    }
    public void setRam(int size){
        this.ram.setSize(size);
    }
    public void setStorage(int size){
        this.storage.setSize(size);
    }
    @Override
    public String toString() {
        return "CPU: "+this.cpu.toString()+"\nRam: " + this.ram.toString() + "\nStorage: "+this.storage.toString();
    }
}
