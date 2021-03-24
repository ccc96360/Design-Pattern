package pattern.Builder;

public class Main {

    public static void main(String[] args) {
	    ComputerBuilder builder = new MyComputerBuilder();
	    Computer myPC = builder.setCpu("i7").setRam(32).setStorage(1024).build();
	    System.out.println(myPC);
	    Computer myPc2 = new MyComputerBuilder().setCpu("i3").setStorage(2048).setRam(64).build();
	    System.out.println(myPc2);
    }
}
