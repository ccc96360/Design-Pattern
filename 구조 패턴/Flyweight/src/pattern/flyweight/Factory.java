package pattern.flyweight;

import java.util.HashMap;

public class Factory {
    private static final HashMap<String, Hello> pool = new HashMap<>();

    public static Hello make(String name){
        System.out.println("Hello 객체 생성 요청!");
        Hello hello = (Hello) pool.get(name);
        if(hello == null){
            hello = new Hello();
            pool.put(name, hello);
        }
        return hello;
    }
}
