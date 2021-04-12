# 체인 패턴 (Chain Pattern)
#### 객체 메시지의 송신과 수신을 분리해서 처리한다.

---

## 제어문
> ###### 일반적으로 제어문을 통해 실행 흐름을 변경한다.
#### 1. 조건 처리
```java
    public static void main(String[] args) {
        Boolean conf = true;
        if(conf)
            ordered();
        else
            cancel();
    }
```
> ###### 선택적으로 실행을 제어한다는 의미는 상태에 따라 동작을 분리한다는 것과 같다.

#### 2. 메시지 전송
> ###### 절차지향 프로그래밍에서는 코드 순서와 함수 호출을 통해 동작을 분리하지만, OOP에서는 메시지를 객체에 전송하여 메서드를 호출한다.
> ###### 객체 메시지 전송은 다른 객체에 접근하여 메서드를 호출하는 동작을 말한다.
> ###### 또한 객체간 정보도 주고 받는다. 아래는 예시다.
```java
    static class Cart{
        public void ordered(){}
        public void cancel(){}
    }
    public static void main(String[] args) {
        Cart cart = new Cart();
        Boolean conf = true;
        if(conf)
            cart.ordered();
        else
            cart.cancel();
    }
```

---
## 행동 분리
> ###### 핸들러를 통해 처리로직을 분리한다.
#### 1. 단일 책임
> ###### 핸들러 객체의 단일 책임원칙을 보완한다.
> ###### 단일 책임의 원칙을 적용하기 위해 Order와 Cancel를 독립된 객체로 분리한다.
```java
public class Order {
    public void execute(){
        System.out.println("주문을 처리합니다.");
    }
}
public class Cancel {
    public void execute(){
        System.out.println("주문을 취소합니다.");
    }
}
```
> ###### 분리된 실제 동작 객체를 핸들러 객체와 결합한다.
> ###### 핸들러는 전송 받은 상태 값에 따라 객체를 생성하고 메시지를 출력한다.
```java
    static class Handler {
        public void event(String msg){
            if(msg.equals("order")){
                (new Order()).execute();
            }
            else if(msg.equals("cancel")){
                (new Cancel()).execute();
            }
            else{
                System.out.println("동작이 없습니다.");
            }
        }
    }
    public static void main(String[] args) {
        Handler handler = new Handler();

        handler.event("order");
    }
```
## 사슬 연결
> ###### 핸들러의 처리 동작을 단일 사슬 형태로 변경한다.
> ###### 사슬 연결을 통해 하나의 상태값에 따라 복수의 행동을 실행할 수 있다.

#### 1. 조건 검사흐름
> ###### 위의 예제에서 아직 조건을 판단하는 다음과 같은 부분이있다.
```java
            if(msg.equals("order")){
                (new Order()).execute();
            }
            else if(msg.equals("cancel")){
                (new Cancel()).execute();
            }
            else{
                System.out.println("동작이 없습니다.");
            }
```
> ###### 이 수신부를 개선해본다.

#### 2. 송수신 분리
> ###### 체인 패턴의 아이디어는 핸들러에서 순차적으로 이벤트를 검사하는 조건들을 분리하는 것이다.
> ###### 핸들러 객체에서 검사하던 조건 처리 로직이 분리된 실제 동작 객체로 이동 된다.
> ###### 핸들러 객체안에 있는 조건 처리 로직을 분리하면, 핸들러는 단일 책임 원칙을 유지할 수 있다.
> ###### 아래는 이전 예제를 변경한 코드이다.
```java
public class Order {
    public void execute(String event){
        if(event.equals("order"))
            System.out.println("주문을 처리합니다.");
    }
}
public class Cancel {
    public void execute(String event){
        if(event.equals("cancel"))
            System.out.println("주문을 취소합니다.");
    }
}
public class Main {
    static class Handler {
        public void event(String msg){
            (new Order()).execute(msg);
            (new Cancel()).execute(msg);
        }
    }
    public static void main(String[] args) {
        Handler handler = new Handler();

        handler.event("order");
    }
    ...
}
```
> ###### 조건 처리 로직은 분리 했지만, 핸들러는 별로 개선 되지 않았다.
> ###### 딱봐도 인터페이스 하나 만들어서 처리하면 될것 같이 생겼다.

#### 3. 인터페이스
> ###### 일반적으로 핸들러는 하나의 상태값에 하나의 동작 객체를 지정하는데, 하나의 상태값에 복수의 동작 객체를 지정할 경우 처리 로직이 복잡해진다.
> ###### 체인 패턴은 하나의 상태값에 복수의 동작 객체를 처리할수 있도록 객체를 하나의 사슬 형태로 묶어서 처리한다.
```java
public abstract class Chain {
    protected Chain next;
    public void setNext(Chain nextItem){
        this.next = nextItem;
    }
    abstract void execute(String event);
}
```
> ###### setNext() 메서드를 이용해 위임되는 객체의 정보를 사슬 형태로 설정한다.

#### 4. 사슬 형성
> ###### 핸들러는 이벤트 행동 조건을 검사하지 않는다.
> ###### 실제 동각하는 객체가 판단하며, 자신이 처리해야 하는 조건이 아니라면 다음 객체로 행동을 위임한다.
```java
public class Order extends Chain {
    @Override
    public void execute(String event){
        if(event.equals("order"))
            System.out.println("주문을 처리합니다.");
        else
            this.next.execute(event);
    }
}
public class Cancel extends Chain{
    @Override
    public void execute(String event){
        if(event.equals("cancel"))
            System.out.println("주문을 취소합니다.");
        else
            this.next.execute(event);
    }
}
```
> ###### 위 와 같이 조건을 만족할때 까지 재귀적으로 실행된다.
> ###### Handler는 다음과 같이 변경된다.
```java
public class Main {
    static class Handler {
        public void event(String msg){
            Chain first = new Order();
            first.setNext(new Cancel());

            first.execute(msg);
        }
    }
    public static void main(String[] args) {
        Handler handler = new Handler();

        handler.event("cancel");
    }
    ...
```
**실행 결과**
```aidl
주문을 취소합니다.
```
> ##### 위의 예제에서 알 수 있듯이 체인패턴은 요청한 이벤트 상태를 어떤 객체가 처리할지 모른다.

## 미들웨어
> ###### 체인 패턴은 미들웨어 기능을 구현할 때 많이 응용된다.
> ###### 미들웨어는 객체의 행동을 수행하기 전에 미리 실행되어야 하는 기능을 말한다.
## 관련 패턴
> ##### 복합체 패턴, 명령 패턴
## 추가 
> #### 구글링을 해보니 아래와 같은 방식으로 대부분 구현한다.
```java
public abstract class Chain2 {
    protected Chain2 next;
    public void setNext(Chain2 nextItem){
        this.next = nextItem;
    }
    public final void support(String event){
        if(resolve(event)){
            execute(event);
        }
        else if(next != null){
            next.support(event);
        }
        else{
            System.out.println("어떤 객체도 처리하지 못함");
        }
    }
    abstract boolean resolve(String event);
    abstract void execute(String event);
}
```
> ###### 동작 처리는 ```support()```에서 이루어진다.
> ###### ```resolve()```를 통해 조건을 만족하는지 판단한다. 이때 ```resolve()```는 오버라이드해 각각 실제 객체에서 판단한다.
> ###### ```execute()```는  실제 동작을 처리한다.
> ###### 정리하면 이전 예제에서 조건처리 -> 조건 충족 ->실제동작 or 조건처리 -> 조건 불충족 -> 다음 객체가 분리된 형태이다.
> ###### 다음은 위 ```Chain2```객체를 상속받은 실제 동작 객체이다.
```java
public class Order2 extends Chain2 {
    /*조건 만족시 실행할 동작*/
    @Override
    public void execute(String event){
        System.out.println("주문을 처리합니다.");
    }
    /*이벤트 조건*/
    @Override
    boolean resolve(String event) {
        return event.equals("order");
    }
}
public class Cancel2 extends Chain2{
    @Override
    public void execute(String event){
        System.out.println("주문을 취소합니다.");
    }

    @Override
    boolean resolve(String event) {
        return event.equals("cancel");
    }
}
```
> ###### 핸들러는 아래와 같이 만들어 동작한다.
```java
    static class Handler2 {
        public void event(String msg){
            Chain2 first = new Order2();
            first.setNext(new Cancel2());

            first.support(msg);
        }
    }
    public static void main(String[] args) {
        Handler2 handler2 = new Handler2();
        handler2.event("order");
        handler2.event("cancel");
        handler2.event("qwerasdzxc");

    }
```
**실행 결과**
```aidl
주문을 처리합니다.
주문을 취소합니다.
어떤 객체도 처리하지 못함
```
> ###### 핸들러는 굳이 만들어줄 필요는 없다.
> ###### 이미 Client인 메인 클래스에서 요청송신과 요청 수신이 분리 되기 때문이다.
> ###### 그럼에도 불구하고 만들어 주는게 더 깔끔하며 처리해야할 동작객체가 많이질 경우도 생각하면 만드는게 더 좋다고 생각한다.
> ###### 역할 사슬 패턴 (Chain of Responsibility Pattern) 이라고도 불린다. 