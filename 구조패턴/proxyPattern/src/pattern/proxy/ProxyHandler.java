package pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {

    Object target;

    public ProxyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().equals("action1")){
            System.out.println("Action1 메서드 전처리");
        }
        else if(method.getName().equals("action2")){
            System.out.println("Action2 메서드 전처리");
        }
        Object ret = method.invoke(target, args);
        System.out.println("메서드 실행후 후처리");
        return ret;
    }
}
