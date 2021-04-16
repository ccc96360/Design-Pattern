# 상태 패턴 (Status Pattern)
#### 조건에 따른 별개의 동작을 제어문으로 사용하지 않는다. 그 대신 객체를 캡슐화하여 독립된 동작으로 구분하는 패턴이다.
#### 상태 패턴은 상태표현 개체라고 부르기도 한다.

---
## 상태 처리 
#### 프로그램은 조건에 따라 분기해 다양한 동작을 처리한다.
#### 1. if문 상태 처리
```java
public class Main {
    static final int OPEN = 0x01; //주문
    static final int PAY = 0x02; //결제
    static final int ORDERED = 0x04; //주문완료
    public static void main(String[] args) {
	    int state = OPEN;
	    if(state == OPEN){
	        System.out.println("주문");
        }
	    else if(state == PAY){
	        System.out.println("결제 중");
        }
	    else if(state == ORDERED){
	        System.out.println("주문완료");
        }
    }
}
```
> ###### if문을 이용해 동작을 구분하는 가장 전형적인 상태 처리 로직이다.

#### 2. 상태 추가
```java
public class Main {
    static final int OPEN = 0x01; //주문
    static final int PAY = 0x02; //결제
    static final int ORDERED = 0x04; //주문완료
    static final int FINISH = 0x08; //처리완료

    public static void main(String[] args) {
	    int state = OPEN;
	    if(state == OPEN){
	        System.out.println("주문");
        }
	    ...생략...
	    else if(state == FINISH){
	        System.out.println("처리 완료");
        }
    }
}
```
> ###### 만약 상태가 추가되면 위 처럼 코드를 변경해야 한다.
> ###### 이처럼 상태가 추가되면 코드가 복잡해지고, 유지 보수 빈도가 늘어난다.

---
## 패턴 구현

#### 1. 인터페이스
> ###### 상태에 따라 특정한 동작을 실행하는 메서드를 정의한다.
```aidl
public interface State {
    public void process();
}
```
#### 2. 캡슐화
> ###### 각 상태의 동작을 캡슐화 한다.
```java
public class StateOrder implements State {
    @Override
    public void process() {
        System.out.println("주문");
    }
}
public class StatePay implements State {
    @Override
    public void process() {
        System.out.println("결제중");
    }
}
public class StateOrdered implements State {
    @Override
    public void process() {
        System.out.println("주문 완료");
    }
}
public class StateFinish implements State {
    @Override
    public void process() {
        System.out.println("처리완료");
    }
}
```
> ###### 각각의 상태를 객체로 캡슐화 하기 때문에 클래스 파일이 늘어난다는 단점이 있다.
> ###### 하지만, 수많은 조건문을 사용하는 것보다는 유연하게 확장 할 수 있다.

#### 3. 단일 상태
> ###### 단일 상태값으로 캡슐화 한다. 1개의 객체는 1개의 상태만 구현한다.
> ###### 때로는 1개의 상태 객체가 2개 이상의 상태값을 처리하는 경우도 있다.
> ###### 복수의 상태값을 처리하려면 별도의 조건 제어문을 추가해야 한다.
> ###### 그러나 복수의 상태값을 처리하는 경우 상태 객체의 동작이 불분명해지는 상황이 발생한다.

---
## 객체 생성
#### 상태 패턴은 상태별로 구분된 객체에 동작을 위임한다.
#### 위임하기 위해서는 각 상태별 객체의 생성 관리가 필요하다.

#### 1. 인스턴스
> ###### 캡슐화된 상태 클래스를 실체 객체로 생성한다.
```java
public class Order {
    HashMap<String, State> states = new HashMap<>();

    public Order() {
        states.put("ORDER", new StateOrder());
        states.put("PAY", new StatePay());
        states.put("ORDERED", new StateOrdered());
        states.put("FINISH", new StateFinish());
    }
    public void process(String status){
        this.states.get(status).process();
    }
}
```
> ###### 상태 객체를 저장하기위해 배열 또는 여러 변수를 추가로 사용하는것은 단점이다.

---
## 상태 전환

#### 1. 상태 호출
> ###### 다음은 상태를 지정하여 호출하는 예제이다.
```java
public class Main {
    public static void main(String[] args) {
	    Order order = new Order();
	    order.process("ORDER");
    }
}
```
**실행 결과**
```aidl
주문
```
#### 2. 상태 전이
> ###### 상태 패턴에서 상태 값은 고정되지 않는다.
> ###### 상태 값은 동작에 따라 변하는데 이를 ```상태 전이```라 한다.
> ###### 상태 전이는 특정한 규칙에 의해 변동 된다. 
> ###### 상태 패턴은 상태 전이를 명확하게 표현하는 효과가 있다.

#### 3. 상태 결정
> ###### 상태 처리는 주어진 상태를 처리하고 다음 상태로 이동한다.
> ###### 대부분의 상태는 스스로 알아서 다음 상태를 결정하며, 다음 상태가 결정되면 어떤 조건이나 이벤트에 따라 상태를 처리한다.
> ###### 상태 객체는 자기 자신의 상태값을 보관하지 않지만, 다음 상태로 전환되는 값은 구현되는 상태 객체에 추가로 포함할수 있다.
> ###### 상태 객체가 다음 상태값을 갖고 있을 때 상태 객체 간 의존성이 발생한다.

---
## 실습
#### 전구의 on/off 상태를 실습해 본다.

#### 1. 상태 인터페이스
```java
public interface LightState {
    public LightState pushButton();
}
```
> ###### 전등의 불빛 상태를 나타내는 인터페이스 이다.
> ###### ```pushButton()```메서드는 해당 상태에서 전등의 버튼을 눌렀을때 동작이다.
#### 2. 상태 객체
```java
public class On implements LightState{
    @Override
    public LightState pushButton() {
        System.out.println("전원을 끕니다.");
        return new Off();
    }
}
public class Off implements LightState{
    @Override
    public LightState pushButton() {
        System.out.println("전원을 킵니다.");
        return new On();
    }
}
```
> ###### 전등의 불빛의 상태를 나타내는 객체이다.
> ###### 불이 켜져있으면 ```On``` 꺼져있으면 ```Off```이다.
> ###### 다음 상태값을 리턴한다.
#### 3. 전등 객체
```java
public class Lamp {
    LightState state;

    public Lamp() {
        state = new Off();
    }
    public void pushButton(){
        this.state = this.state.pushButton();
    }
}
```
> ###### 전등 객체이다. 기본 상태는 불이 꺼져있으므로 Off이다.
> ###### ```pushButton()```호출시 현재 상태에 맞는 동작을 하고 다음 상태값으로 전환된다.

#### 4. 클라이언트
```java
public class Main {
    public static void main(String[] args) {
	    Lamp lamp = new Lamp();
	    lamp.pushButton();
	    lamp.pushButton();
	    lamp.pushButton();
    }
}
```
**실행 결과**
```java
전원을 킵니다.
전원을 끕니다.
전원을 킵니다.
```
> ###### 위와 같이 if문이나 switch문과 같은 제어문 없이 메서드를 이용해 상태를 처리할 수 있다.

---
## 효과
#### 거대한 구조의 코드를 구조화하는 데 유용하다.
* 조건문 해결
> ###### 상태 패텬은 소스코드에서 복잡한 if문과 switch문을 제거하고 조건 분기 없이 상태값을 이용해 객체의 동작을 처리한다.
* 런타임
> ###### 런타임으로 위임 객체를 변경해 동적으로 행동을 변화 한다.
* 확장성
> ###### 새로운 상태를 쉽게 추가해서 구현할 수 있다.

---
## vs 전략 패턴 (Strategy Pattern)
#### 1. 구조의 유사성
> ###### 두 패턴 모두 런타임으로 객체를 위임해 동작을 변경한다.
> ###### 전략 패턴은 위임된 객체를 알고리즘으로 생각하지만, 상태패턴은 상태값의 처리로 생각한다.

#### 2. 목적의 차별성
> ###### 전략 패턴은 객체의 상태값에 관심이 없으며, 알고리즘을 교체하고 동작을 변경시키는 것만 생각한다.
> ###### 상태 패턴은 동작하는 객체의 변경이 상태에 따라 달라진다. 즉, 상태값이 매우 중요하며 다음 동작과 객체의 위임을 결정한다.

---