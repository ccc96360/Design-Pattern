package pattern.flyweight;

public class English {

    private Hello hello;

    public English(Hello hello) {
        this.hello = hello;
    }

    public void hello(){
        hello.print("Hello.");
    }
    
}
