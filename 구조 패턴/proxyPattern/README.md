# 프록시 패턴(Proxy Pattern)
#### proxy: 대리인, 대리(권), 대용물
#### 무슨 일을 직접 처리하지 않고 대리자(proxy)를 내세워 처리를 위임한다.

---

## 프록시
#### 1. 프록시의 특징
> ###### 하나의 객체를 두 개로 나눠 재구성한다. (직접적인 접근을 막고 대리할 객체를 구현하기 위해)
> ###### 분리된 두개의 객체는 서로 다른 객체가 아니다.
> ###### 동일한 인터페이스를 사용하며, 단지 객체의 접근과 동작을 제어하기 위한 중간 제어 구조가 추가된 객체일 뿐이다.

#### 2. 다양한 프록시
> ###### 응용 범위에 따라 다르게 불린다.
> ###### 대표적으로, 원격 프록시, 가상 프록시, 보호 프록시, 스마트 프록시 등이 있다.

## 객체 가로채기
> ###### 프록시는 실체 객체를 호출하면 행위를 중간에 가로채서 다른 동작을 수행하는 객체로 변경한다.
#### 1. 실체 객체
> ###### 실습을 위한 객체를 선언한다.
```java
public class RealSubject {
    public RealSubject() {
        System.out.println("Real Subject 생성");
    }
    public void action1(){
        System.out.println("Action1 실행");
    }
    public void action2(){
        System.out.println("Action2 실행");
    }
}
```
#### 2. 객체 호출
> ###### 일반적으론 다음과 같이 동작 시킨다.
```aidl
    ...
    public static void main(String[] args) {
        RealSubject obj = new RealSubject();
        obj.action1();
        obj.action2();
    }
   ...
```
#### 실행 결과
```aidl
Real Subject 생성
Action1 실행
Action2 실행
```

---
## 객체 분리
> ###### 객체를 정교하게 제어해야 하거나 객체 참조가 필요한 경우 프록시 패턴을 이용한다.
#### 1. 인터페이스
> ###### 프록시 패턴은 원본 객체(```RealSubject```)와 동일한 역할을 할 프록시를 생성한다.
> ###### 프록시는 원본 객체와 동일한 인터페이스를 적용해 투명성을 갖도록 설계된다.
```java
public interface Subject {
    public void action1();
    public void action2();
}
```
#### 2. 인터페이스 적용
> ###### 기존의 원본 객체(```RealSubject```)에 인터페이스를 적용한다.
```aidl
public class RealSubject implements Subject{
    public RealSubject() {
        System.out.println("Real Subject 생성");
    }
    @Override
    public void action1(){
        System.out.println("Action1 실행");
    }
    @Override
    public void action2(){
        System.out.println("Action2 실행");
    }
}
```
> ###### 인터페이스를 적용했다고 해서 특정한 변화가 생기는 것은 아니다.
> ###### 인터페이스를 생성한 이유는 실체 객체에 프록시 간의 규약을 전달하기 위해서다.

---
## 프록시 생성
> ###### 인터페이스를 이용해 프록시 객체를 생성한다. 프록시 객체를 생성할 때 기존의 실체 객체의 정보도 같이 필요하다.
#### 1. 기능 분리
> ###### 실체 객체와 프록시는 전혀 다른 객체가 아니라 동릴한 객체처럼 보이며 그렇게 동작한다.
> ###### 프록시는 실체 객체를 대신해 행위를 처리하므로 실체 객체의 정보가 필요하다.
#### 2. 프록시 객체
```java
public class Proxy implements Subject{
    private Subject real;

    public Proxy(Subject real) {
        System.out.println("Proxy 생성.");
        this.real = real;
    }

    @Override
    public void action1() {
        System.out.println("Action1 메서드 전처리");
        real.action1();
        System.out.println("메서드 실행후 후처리");
    }

    @Override
    public void action2() {
        System.out.println("Action2 메서드 전처리");
        real.action2();
        System.out.println("메서드 실행후 후처리");
    }
}
```
> ###### 위와 같이 의존성을 주입해 프록시와 실체 객체를 연결한다.
> ###### 프록시가 실체 객체로 행위를 위임한다.
#### 3. 출력
```aidl
...
    public static void main(String[] args) {
        Proxy proxy = new Proxy(new RealSubject());
        proxy.action1();
        proxy.action2();
    }
...
```
#### 실행 결과
```aidl
Real Subject 생성
Proxy 생성.
Action1 메서드 전처리
Action1 실행
메서드 실행후 후처리
Action2 메서드 전처리
Action2 실행
메서드 실행후 후처리
```
> ###### 클라이언트는 실체 객체에 직접 접근하지 않고 프록시로 대신 실행한다.
> ###### 만약 메서드 전처리가 실체 객체에 대한 사전처리이면 스마트 프록시라고 한다.

#### 4. 투과적 특성
> ###### 실체 객체와 동일한 동작을 그래도 대신하는 것.(동일한 결과를 반환한다.)

---
## 동적 프록시

#### 1. 간접 통로
> ###### 프록시는 간접화된 객체의 접근 통로를 제공한다.
> ###### 간접 통로는 프록시의 투과적 특성을 이용하여 실체 객체의 행위를 위임하고 처리를 요청한다.

#### 2. 설계
> ###### 개발중 실체 객체를 변경해야 되는경우가 발생한다.
> ###### 그럴경우 인터페이스와 프록시 모두 변경해야한다.
> ###### 여러 메서드에 동일한 처리를 해야되는 경우 중복이 발생 된다.
> ###### 이런 경우를 해결하기위해 동적 프록시를 사용한다.
> ###### 자바에서는 ```Java.lang.reflect.Proxy```클래스의 ```newProxyInstance()```메소드를 이용한다.

#### 3. newProxyInstance()
```java
public static Object newProxyInstance(
        ClassLoader loader,
        Class<?> interfaces,
        InvocationHandler h) throws IllegalArgumentException
```
> ###### 첫번째 인자: 프록시를 만들 클래스 로더
> ###### 두번째 인자:어떤 인터페이스에 대해 프록시를 만들 것인지 명시
> ###### 세번째 인자: ```InvocationHandler```인터페이스 구현체
> ###### 반환값: 프록시 객체
#### ```InvocationHandler```인터페이스
> #### ```invoke()```메서드 하나만 가지고있다.
```java
public Object invoke(Object proxy, Method method, Object[] args) throw Throwalbe
```
> ###### 첫번째 인자: 프록시 객체, 두번째 인자: 클라이언트가 호출한 메서드, 세번째 인자: 메소드의 인자 
> ###### 즉 ```newProxyInstance()```로 만든 프록스 객체를 통해 메서드를 실행시키면
> ###### ```InvocationHandler```구현체의 invoke로 메서드가 전달되 실행된다.
> ###### 이전에는 프록시 인스턴스가 컴파일시 생성 된다면, 이제는 런타임시 생성된다.
#### 4. 실습
#### 4.1 InvocationHandler구현 클래스 생성
```java
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
```
> ###### ```invoke()```에서  기존```action1()```과 ```action2()```에 대한 전처리가 되어있다.
> ###### 이후 메서드를 실행하며 이때 반환값을 반환한다.
> ###### Target(여기에서는 ```RealSubject```가 주입된다)의 기능을 확장하거나 추가 하지 않는다.
#### 4.2 기존 객체 변경
```java
public class RealSubject implements Subject{
    public RealSubject() {
        System.out.println("Real Subject 생성");
    }
    @Override
    public void action1(){
        System.out.println("Action1 실행");
    }
    @Override
    public void action2(){
        System.out.println("Action2 실행");
    }
    @Override
    public void otherAction(){
        System.out.println("Other Action");
    }
}

public interface Subject {
    public void action1();
    public void action2();
    public void otherAction();
}
```
> ###### 실체 객체에 새로운 메서드 ```otherAction()```이 추가 되었다.
> ###### 이에 따라 ```Subject```인터페이스도 변경 되었다.
> ###### 위와 같이 변경되면 기존 프록시 객체에도 새로운 메서드를 오버라이드 해야 한다.

#### 4.3 클라이언트
```java
...
    public static void main(String[] args) {
        // Dynamic Proxy
        Subject s = (Subject) java.lang.reflect.Proxy.newProxyInstance(
                Main.class.getClassLoader(),
                new Class[]{Subject.class},
                new ProxyHandler(new RealSubject())
        );
        s.action1();
        s.action2();
        s.otherAction();
    }
...
```
> ###### ```java.lang.reflect.Proxy```클래스의 ```newProxyInstance()```를통해 Proxy를 생성한다.
#### 실행 결과
```aidl
Real Subject 생성
Action1 메서드 전처리
Action1 실행
메서드 실행후 후처리
Action2 메서드 전처리
Action2 실행
메서드 실행후 후처리
Other Action
메서드 실행후 후처리
```
> ###### 새로 추가된 ```OtherAction()```메서드도 프록시에서 잘 실행이 되었으며,
> ###### ```메서드 실행후 처리```코드도 중복이 제거 되었다.

---
## 원격 프록시
> ###### 주로 데이터 전달을 목적으로 사용한다.
#### 1. 어댑터 vs 프록시
> ###### 두 개의 객체를 이어준다는 역할적인 축면에서 두 패턴은 서로 유사하다.
> ###### 하지만, 어댑터 패턴은 서로 다른 인터페이스를 이어주는 반면, 프록시는 투과적 특성으로 동일한 인터페이스를 유지한다.
> ###### 원격 프록시는 분리된 객체에 투과적 특성을 결합해 객체의 연결을 제어 한다.
> ###### 다음은 원격(서버)에 있는 ```RemoteRealSubject``` 클래스이다.
```java
public class RemoteRealSubject implements Subject{
    @Override
    public void action1() {
        System.out.println("실제 action1");
    }

    @Override
    public void action2() {
        System.out.println("실제 action2");
    }

    @Override
    public void otherAction() {
        System.out.println("실제 otherAction");
    }
}
```
#### 2. 프록시
```java
public class RemoteProxy implements Subject{
    private Subject real;

    public RemoteProxy(Subject real) {
        this.real = real;
    }

    @Override
    public void action1() {
        System.out.println("Action1은 기능이 대체되었습니다.");
    }

    @Override
    public void action2() {
        real.action2();
    }

    @Override
    public void otherAction() {
        real.otherAction();
    }
}

```
#### 3. 클라이언트
```java
...
    public static void main(String[] args) {
        //Remote Proxy
        RemoteProxy local = new RemoteProxy(new RemoteRealSubject());

        local.action1();
        local.action2();
        local.otherAction();
    }
...
```
> ###### 기본적인 프록시 패턴과 동일한 모습이지만 RemoteRealSubject가 원격 서비스에 있다고 생각하자.
> ###### 사용자는 네트워킹을 몰라도 로컬에서 메서드를 호출 하는 방식으로 사용하면 된다.
```aidl
Action1은 기능이 대체되었습니다.
실제 action2
실제 otherAction
```

---
## 가상 프록시
> ###### 프로그램의 실행 속도를 개선하기 위한 패턴이다.
> ###### 무거운 객체 생성을 잠시 유보한다.
#### 1. 게으른 초기화(Lazy Initialization)
> ###### 실체 객체를 생성하지 않고 각상의 프록시만 생성 반환 한다.
> ###### 가상의 프록시는 실체 객체 생성보다 더 빨리 객체를 생성, 반환할 수 있다.
> ###### 프로그램에 껍데기만 전달하고 실체 객체의 접근이 필요할 때 원본 객체를 동적으로 생성하여 프록시와 연결한다.

#### 2. 속도 개선
> ###### 다수의 초기화 적업은 프로그램이 처음 실행될 때 속도에 영향을 준다.
> ###### 가상화된 프록시를 통해 객체 생성을 임시 처리함으로써 시스템 성능을 최적화할 수 있다.

#### 3 플라이 웨이트 패턴 결합
> ###### 기존 ```new Proxy(new RealSubject())```와 같이 외부에서 주입된는 의존 객체의 설정을 삭제한다.
> ###### 다음은 내부적으로 의존된 객체를 생성하도록 하는 코드이다.
```java
public class VirtualProxy implements Subject{
    private Subject real;

    public VirtualProxy() {
        System.out.println("가상 프록시 생성");
    }

    @Override
    public void action1() {
        if(real == null)
            this.real();
        System.out.println("Action1 메서드 전처리");
        real.action1();
        System.out.println("메서드 실행후 후처리");
    }

    @Override
    public void action2() {
        if(real == null)
            this.real();
        System.out.println("Action2 메서드 전처리");
        real.action2();
        System.out.println("메서드 실행후 후처리");
    }

    @Override
    public void otherAction() {
        if(real == null)
            this.real();
        real.otherAction();
        System.out.println("메서드 실행후 후처리");
    }
    private void real(){
        this.real = new RealSubject();
    }
}
```
> ###### 각각의 메서드가 실행될 때 실체 객체의 인스턴스가 없으면 생성한다.
> ###### 처음으로 생성될때 다소 지연시간이 발생할 수 있지만, 두번쨰 호출부터는 빠르게 동작한다.
> ###### 이렇게 생성된 실체 객체는 공유를 위해 프록시의 프로퍼티에 저장 된다.

#### 4. 클라이언트
```java
...
    public static void main(String[] args) {
        //Virtual Proxy
        VirtualProxy virtualProxy = new VirtualProxy();

        virtualProxy.action1();
        virtualProxy.action2();
        virtualProxy.otherAction();
    }
...
```
#### 실행 결과
```aidl
가상 프록시 생성
Real Subject 생성
Action1 메서드 전처리
Action1 실행
메서드 실행후 후처리
Action2 메서드 전처리
Action2 실행
메서드 실행후 후처리
Other Action
메서드 실행후 후처리
```

---
## 보호 프록시(Protection Proxy)
> #### 객체의 접근 권한을 제어 한다.
#### 1. 통제
> ###### 객체를 사용하기 위해서는 생성 과정과 자원 할당이 필요하다.
> ###### 권한이 없는 사용자가 제한된 객체 생성하는 것은 의미 없다.
> ###### 권한이 없는 객체를 생성 과정에서 배제하면 자원 낭비를 줄일 수 있다.
> ###### 프록시를 통해 제어권을 확인한 후 객체를 생성한다.
#### 2. 권한 추가
```java
public class ProtectionProxy implements Subject{
    private Subject real;
    private int permit;
    
    // Permit
    public static final int ACT1 = 0x01;
    public static final int ACT2 = 0x02;

    public ProtectionProxy(int permit) {
        this.permit = permit;
    }

    @Override
    public void action1() {
        if(this.permit == ProtectionProxy.ACT2) {
            if (real == null)
                this.real();
            System.out.println("Action1 메서드 전처리");
            real.action1();
            System.out.println("메서드 실행후 후처리");
        }
        else{
            System.out.println("실행 권한 없음");
        }
    }

    @Override
    public void action2() {
        if(this.permit == ProtectionProxy.ACT2) {
            if (real == null)
                this.real();
            System.out.println("Action2 메서드 전처리");
            real.action2();
            System.out.println("메서드 실행후 후처리");
        }
        else{
            System.out.println("실행 권한 없음");
        }
    }

    @Override
    public void otherAction() {
        if(this.permit == ProtectionProxy.ACT2) {
            if (real == null)
                this.real();
            real.otherAction();
            System.out.println("메서드 실행후 후처리");
        }
        else{
            System.out.println("실행 권한 없음");
        }
    }
    private void real(){
        this.real = new RealSubject();
    }
}
```
> ###### 메서드를 호출할때 권한도 검사한다.
#### 3. 클라이언트
```java
...
    public static void main(String[] args) {
        //Protection Proxy
        ProtectionProxy pProxy1 = new ProtectionProxy(ProtectionProxy.ACT1);
        ProtectionProxy pProxy2 = new ProtectionProxy(ProtectionProxy.ACT1);
        
        pProxy1.action1();
        pProxy1.action2();
        pProxy1.otherAction();
        
        pProxy2.action1();
        pProxy2.action2();
        pProxy2.otherAction();
    }
...
```
#### 실행 결과
```aidl
실행 권한 없음
실행 권한 없음
실행 권한 없음
Real Subject 생성
Action1 메서드 전처리
Action1 실행
메서드 실행후 후처리
Action2 메서드 전처리
Action2 실행
메서드 실행후 후처리
Other Action
메서드 실행후 후처리
```

---
## 정리
> ###### 프록시 패턴의 특징은 투명성을 이용해 객체를 분리하여 재위임한다는 것이다.
> ###### 분리된 객체를 위임함으로써 대리 작업을 중간 단계에 삽입할 수 있으며,
> ###### 분리된 객체를 동적으로 연결함으로써 객체의 실행 시점을 관리할 수도 있다.
> ###### 프록시 패턴은 세밀한 객체의 접근이 필요할 때도 매우 유용하다.