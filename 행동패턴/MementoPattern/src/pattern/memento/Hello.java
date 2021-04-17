package pattern.memento;

public class Hello implements Cloneable{
    private String msg;

    public Hello(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
