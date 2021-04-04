package pattern.proxy;

public class Main {

    public static void main(String[] args) {
        System.out.println("=====일반 Proxy=====");
        // 일반 Proxy
        Proxy proxy = new Proxy(new RealSubject());
        proxy.action1();
        proxy.action2();

        System.out.println("=====Dynamic Proxy=====");
        // Dynamic Proxy
        Subject s = (Subject) java.lang.reflect.Proxy.newProxyInstance(
                Main.class.getClassLoader(),
                new Class[]{Subject.class},
                new ProxyHandler(new RealSubject())
        );
        s.action1();
        s.action2();
        s.otherAction();

        System.out.println("=====Remote Proxy=====");
        //Remote Proxy
        RemoteProxy local = new RemoteProxy(new RemoteRealSubject());

        local.action1();
        local.action2();
        local.otherAction();

        System.out.println("=====Virtual Proxy=====");
        //Virtual Proxy
        VirtualProxy virtualProxy = new VirtualProxy();

        virtualProxy.action1();
        virtualProxy.action2();
        virtualProxy.otherAction();

        System.out.println("=====Protection Proxy=====");
        //Protection Proxy
        ProtectionProxy pProxy1 = new ProtectionProxy(ProtectionProxy.ACT1);
        ProtectionProxy pProxy2 = new ProtectionProxy(ProtectionProxy.ACT2);

        pProxy1.action1();
        pProxy1.action2();
        pProxy1.otherAction();

        pProxy2.action1();
        pProxy2.action2();
        pProxy2.otherAction();
    }
}
