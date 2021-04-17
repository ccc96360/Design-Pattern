# 메멘토 패턴 (Memento Pattern)
#### Memento: (사람, 장소를 기억하기 위한) 기념품
#### 객체의 상태를 저장하여 이전 상태로 복구하는 패턴이다. (원하는 시점의 데이터 복구)

---
## 상태 저장
#### 객체는 고유상 상태를 갖고 있으며 객체의 상태는 프로그램 실행 중에 다른 객체에 의해 끊임 없이 값이 변경된다.

#### 1. 상태값
> ###### 다음은 인사말을 출력하는 ```Hello```클래스 이다.
```java
public class Hello {
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
}
```
> ###### 객체 내부에 선언된 프로퍼티(```msg```)에 인사말을 저장하고 읽어 온다.
```java
...
    public static void main(String[] args) {
	    Hello hello = new Hello("안녕하세요.");
	    System.out.println(hello.getMsg());

	    hello.setMsg("Hello nice to meet you.");
	    System.out.println(hello.getMsg());
    }
...
```
> ###### ```setter```를 통해 상태값을 변경한다.

#### 2. 상태 이력
> ###### 객체의 상택밧이 한 번 변경되면 이전 상태로 돌아갈 수 없다.
> ###### 위의 예제에서 ```Hello```객체의 인사말을 초기 상태로 되돌리려면 상태값을 재설정 해야한다.
> ###### 프로그램 동작 실행 단계 하나를 ```체크 포인트```라고 한다.
> ###### 사용자의 행위를 ```체크 포인트``` 형태로 기록 한다. 이를 이용해 이전의 객체 상태로 되돌아 간다.

---
## 캡슐화
#### 1. 객체 관계
> ###### 객체들은 상호 밀접한 의존 관계를 갖고 있고, 객체는 의존성 객체로 메시지를 전송하며 행위를 호출해 동작을 수행한다.
> ###### 따라서 복합 객체를 저장하거나 복원하는 것은 쉽지 않은 작업이다.

#### 2. 객체 접근
> ###### 객체 상태를 복원하려면 객체 내부로 접근해야 한다.
> ###### 그런데 ```private```과 ```protected```로 접근이 제한된 상태값은 복구가 힘들다.
 
#### 3. 캡슐화 파괴
> ###### 완전한 복원을 위해 모두 public으로 변경하면  캡슐화의 장점을 잃어버린다.
> ###### 절충안이 필요하다.

#### 4. 제약 해결
> ###### 메멘토 패턴은 캡슐화 위반을 최소화하면서 객체 저장과 복원을 실행할 수 있도록 돕는 패턴이다.
> ###### 중간 매개체를 이용하여 상태 이력을 보다 쉽게 관리하며, 이 중간 매개체를 ```constraintSolver```라고 한다.

---
## 메멘토

#### 1. 객체 관리
> ###### 객체를 스냅샷 형태로 저장한다. 보통 스택 구조를 활용한다.

#### 2. 인터페이스
> ###### 객체의 저장과 복원을 위해 ```원본(Originator)```, ```케어테이터(caretaker)``` 2가지 인터페이스를 사용하며 인터페이스를 이용해 관리 방법을 구분한다.
> ###### ```Originator```는 광범위한 메멘토의 접근을 모두 허용하지만 ```Caretaker```는 제한된 범위 안에서 허용한다.

#### 3. Memento 클래스
```java
public class HelloMemento {
    private Hello obj;

    public HelloMemento(Hello obj) {
        try {
            this.obj = (Hello) obj.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
    
    public Hello getObj() {
        return obj;
    }
}
```
> ###### ```Hello```클래스의 상태정보를 저장한다.
> ###### ```Memento```객체는 ```Originator```가 생성한다.
> ###### ```clone```을 사용하기위해 Hello 클래스에 Cloneable을 implements해주어야 한다.
---
## Originator
#### 실체 객체와 메멘토 사이의 중간 매개체 역할을 수행한다.
```java
public class HelloOriginator {
    private Hello state;

    public HelloMemento createMemento(){
        return new HelloMemento(this.state);
    }

    public void restoreMemento(HelloMemento memento){
        this.state = memento.getObj();
    }

    public Hello getState() {
        return state;
    }

    public void setState(Hello state) {
        this.state = state;
    }
}

```
> ###### ```state```는 객체의 저장 및 복원을 위한 중간 매개체적인 성격의 프로퍼티이다.
#### 실습
```java
...
    public static void main(String[] args) {
    	HelloOriginator origin = new HelloOriginator();
	    Hello hello = new Hello("상태1: 안녕하세요.");
	    System.out.println(hello.getMsg());
        
	    // 현재 상태를 Originator를 이용해 메멘토에 저장한다.
	    origin.setState(hello);
	    HelloMemento memento = origin.createMemento();

	    hello.setMsg("상태2: Hello nice to meet you.");
	    System.out.println(hello.getMsg());

	    // 메멘토를 이용해 상태를 복원한다.
	    origin.restoreMemento(memento);
	    hello = origin.getState();
	    System.out.println(hello.getMsg());

    }
...
```
**실행 결과**
```aidl
상태1: 안녕하세요.
상태2: Hello nice to meet you.
상태1: 안녕하세요.
```

---
## CareTaker
#### 실행 취소 메커니즘이고 제한적 범위의 인터페이슬 가진다.
#### 1. 구현
> ###### ```Stack```을 이용해 구현한다.
```java
public class HelloCareTaker {
    private Stack<HelloMemento> stack = new Stack<>();
    
    public void push(HelloMemento memento){
        stack.push(memento);
    }
    public HelloMemento pop(){
        return stack.pop();
    }
}
```
> ###### CareTaker에서 꺼내온 메멘토 객체르 다시 Originator객체로 전달한다.
> ###### 메멘토를 보관하기만 할 뿐 메멘토를 조작하지 않는다.

#### 2. 실습
```java
public class Main {

    public static void main(String[] args) {
        HelloOriginator originator = new HelloOriginator();
        HelloCareTaker careTaker = new HelloCareTaker();
    
        Hello hi = new Hello("상태1: 안녕!");
        System.out.println(hi.getMsg());
    
        originator.setState(hi);
        HelloMemento memento1 = originator.createMemento();
        careTaker.push(memento1);
    
        hi.setMsg("상태2: Hi~ Nice to meet you");
        System.out.println(hi.getMsg());
    
        memento1 = originator.createMemento();
        careTaker.push(memento1);
    
        hi.setMsg("상태3: 무~야~호~");
        System.out.println(hi.getMsg());
    
        memento1 = originator.createMemento();
        careTaker.push(memento1);

	    //상태 복원
        memento1 = careTaker.pop();
        originator.restoreMemento(memento1);
        hi = originator.getState();
        System.out.println(hi.getMsg());
    
        memento1 = careTaker.pop();
        originator.restoreMemento(memento1);
        hi = originator.getState();
        System.out.println(hi.getMsg());
    
        memento1 = careTaker.pop();
        originator.restoreMemento(memento1);
        hi = originator.getState();
        System.out.println(hi.getMsg());
    }
}
```

---
## 관련패턴
> #### ```명령 패턴```, ```프로토타입 패턴```, ```상태 패턴```, ```반복자 패턴```