package pattern.flyweight;

public class Korean {

    private Hello hello;

    public Korean(Hello hello) {
        this.hello = hello;
    }

    public void hello(){
        hello.print("안녕하세요.");
    }

}
