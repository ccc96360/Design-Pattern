package pattern.prototype;

public class Hello implements Cloneable{
    private String message;
    Hello(String s){
        this.message = s;
        System.out.println("Hello 객체 생성");
    }
    public String getMessage(){
        return this.message;
    }
    public void setMessage(String s){
        this.message = s;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
