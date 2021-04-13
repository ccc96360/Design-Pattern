# 감시자 패턴 (Observer Pattern)

---
## 감시자
#### 1. 관찰
> ###### 프로그램 동작은 하나의 로직을 실행한 후 다음 로직을 실행하는 것처럼 순차적으로 이뤄진다.
> ###### 코드의 로직은 독립적이고 자체적인 동작이다. 또는 외부의 값에 따라 실행되는 동작을 다르게 할 수도있다.
> ###### 코드의 로직이 다른 값(상태)에 의존해서 동작하는 경우, 코드는 값을 확인하는 과정이 필요하다.
> ###### 다음은 반복문과 조건문을 이용한 감시 코드이다.
```java
    public static void main(String[] args) {
	    Boolean status = false;
	    while(true){
	        if(status){
	            System.out.println("Hi~!");
	            break;
            }
        }
    }
```
> ###### 다른 쓰레드가 상태값을 변경 시켜 주지않으면 무한 루프에 빠진다.

#### 2. 상태 변화
> ###### 상태값을 하나하나 관찰하면서 동작하는 것은 비효율적이며, 이러한 프로그램 처리는 성능을 저하시키는 요인이다.
> ###### 따라서 상태값을 관찰하는 것이 아닌 값에 변화가 있을때 이를 알리고 처리를 수행하면 더욱 효율적이다.
> ###### 이처럼 수동적으로 상태값을 전달 받아 처리하는 것이 감시자(Observer) 패턴이다

#### 3. 통보
> ###### 옵저버 패턴엔 ```주체(Subject)```라는 구성 요소가 있는데 주체 클래스는 상태를 갖고있다.
> ###### 이러한 상태에 변경이 발생했을 경우 실제 동작하는 객체(Observer)에 통보하거나 갱신 작업을 통보한다.

#### 4. 할리우드 원칙(Hollywood Principle)
#### "Don't call us, we'll call you."
> ###### 부모 클래스는 서브클래스에 정의된 연산을 호출할 수 있지만 반대 방향의 호출은 안된다.
> ###### 의존성 부패(Dependency rot)현상을 방지한다.
> > ###### 의존성 부패: 고수준 구성요소가 저수준 구성요소에게 의존하고, 그 저수준 구성요소는 다시 고수준 구성요소에 의존하고, 그고수준 구서요수는 다시 또다른 구성요소에 의존하는 것과 같이 의존성이 복잡하게 꼬여있는것.
> ###### 옵저버 패턴은 할리우드 원칙을 적용한 패턴이다.

---
## 구성
#### 1. 관련 클래스
> ###### 다음과 같이 4개의 클래스로 구성된다.
> > ###### ```주체(Subject)```,```실제 주체(Concrete Subject)```,```감시자(Observer)```, ```실제 객체(Concrete Observer)```
> ###### 4개의 클래스는 다시 2개의 그룹으로 구분할수 있다.
> > ###### ```통보를위한 주체-실제 주체 클래스```, ```처리를 위한 감시자-실체 객체 클래스```

#### 2. 주체-실제 주체
> ###### 감시자 패턴에서 객체의 등록, 삭제, 통보를 담당하는 클래스이다.
> ###### 주체 클래스는 실제 처리하는 객체를 관리하고 관리를 담당하는 주체는 1개이상의 감시자 객체를 갖고 있다.

#### 3. 감시자-실체 객체
> ###### 감시자-실체 객체는 통보를 수신 받아 처리하는 객체이다.
> ###### 통보를 받으려면 주체클래스에 수신받는 객체를 등록해야 한다.
> ###### 보퉁은 주체로부터 수동적을 통보를 받지만, 필요 시 능동적으로 서브 객체 상태를 주체로 전달하기도 한다.

---
## 관계
#### 1. 감시자 객체의 의존성
> ###### 메인 구성 요소인 주체(Subject)는 복수의 서브 객체(Observer)를 갖고 있다.
#### 2. 등록
> ###### 실제 동작하는 모든 객체(Observer) 는 주체에 등록돼야한다.
> ###### 주체는 몇 개의 필수 메서드를 갖고 있으며, 필수 메서드를 통해 관게를 설정하기 위한 등록을 요청한다.
> ###### 이러한 필수 메서드는 인터페이스로 적용하여 구현을 일반화하고, 일반화된 인터페이스는 상태 변화 시 주체에 등록된 감시자로 통보를 전달한다.

#### 3. 독립적
> ###### 주체(Subject)와 감시자(Observer)는 느슨한 형태의 결합 구조와 의존성을 갖고 있다.
> ###### 결합과 의존성을 가진다고 해서 결합된 모든 감시자 객체의 세부 내용까지 알 필요는 없다.
> ###### 주체는 상태 변화시 감시자에게 변경된 상태만 전달하면 된다.
> ###### 모든 감시자 객체는 주체에 의존하는 관계지만, 때로는 독립적 실행이 가능한 개별 객체이다.

#### 4. 구독 관계
> ###### 일대다 구조의 감시자 패턴에서는 주체가 변화 상태를 모든 객체에 통보한다.
> ###### 여기서 통보는 일방적인 단방향성으로 이루어진다.
> ###### 단방향성을 가진 감싲자패턴을 다른말로 게시-구독(Publish-subscribe)패턴이라고 한다.

---
## 주체

#### 1. 주체 인터페이스
```java
public interface Subject {
    public void attach(Observer o);
    public void detach(Observer o);
    public void notifyy();
}
```
> ###### 필요에 따라 Abstract class로 만들어도 된다.
 
#### 2. 실제 주체(Concrete Subject)
```java
import java.util.ArrayList;
import java.util.List;

public class Member implements Subject{
    private List<Observer> observers = new ArrayList<Observer>();

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyy() {
        observers.forEach(o -> o.update());
    }
}
```

---
## 감시자.

#### 1. 감시자 인터페이스
```java
public abstract class Observer {
    protected String name;
    abstract public void update();
}
```
> ###### 당연히 인터페이스로 구현해도 된다.

#### 2. 감시자 객체(Concrete Observer)
```java
public class UserA extends Observer {
    public UserA(String name) {
        this.name = name;
    }

    @Override
    public void update() {
        System.out.println(this.name+"업데이트~!");
    }
}
public class UserB extends Observer{
    public UserB(String name) {
        this.name = name;
    }

    @Override
    public void update() {
        System.out.println(this.name+"업데이트~!");
    }
}
```

#### 3. 통보 시스템
> ###### 상태 변화가 감지되면 주체는 등록된 감시자에 변경을 통보한다. 이때 주체는 각 감시자가 가진 공통된 ```update```메서드를 호출한다.

#### 4. 능동적 통보
> ###### 때로는 감시자 객체가 자신의 변호를 주체에게 능동적으로 통보한다.
> ###### 이는 주체와 감시자 간의 의존 관계를 어떻게 구성하는냐에 따라 다르다.
> ###### 상호 통보 기능을 구현할때는 객체간 호출 순환 고리에 빠지지 않도록 설계해야 한다.

## 실습
```java
public class Main {
    public static void main(String[] args) {
	    Subject subject = new Member();
	    UserA a = new UserA("A");
	    subject.attach(a);
	    UserB b = new UserB("B");
	    subject.attach(b);
	    subject.notifyy();
    }
}
```
**실행 결과**
```aidl
A업데이트~!
B업데이트~!
```
---
## 자바에서의 옵저버 패턴
> ###### 위에서 구현한 주체(Subject)는 제공되는 ```java.util.Observable```객체로 대체해 사용할수 있다.
```java
import java.util.Observable;

public class Member2 extends Observable {

    public void statusChange(){
        setChanged();
        notifyObservers();
    }
}
```
> ###### 기존 uttach(addObserver), dettach(deleteObserver)를 포함해서 다양한 함수들이 구현 되어있다.
> ###### 위와같이 ```setChanged()```를 통해 상태가 변화 되었음을 마킹하고 ```notifyObservers()```를 통해 옵저버들에게 알린다.
> > ###### ```setChanged()```는 protected이고 ```notifyObservers()```는 public이다.
> ###### 하지만 ```Observable```이라는 객체를 상속 받은 것이므로 다른 객체를 상속받을 수 없는 문제가 생긴다.
> ###### 즉 이미 수퍼클래스가 있는 클래스에는 적용할 수 없다.
> ###### 다음은 제공되는 ```java.util.Observer```인터페이스를 통해 구현한 ```Concrete Observer```이다.

```java
import java.util.Observable;
import java.util.Observer;

public class UserA2 implements Observer {
    private String name;

    public UserA2(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(this.name+"업데이트~!");
    }
}
public class UserB2 implements Observer {
    private String name;

    public UserB2(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(this.name+"업데이트~!");
    }
}
```
> ###### 위와 같이 ```Observer``` 인터페이스를 구현해 사용하면된다.
> ###### 다음은 실제 동작 코드 이다.
```java
import java.util.Observer;

public class Main {
    public static void main(String[] args) {
		Member2 subject2 = new Member2();
		Observer a2 = new UserA2("A2");
		subject2.addObserver(a2);
		subject2.statusChange();
		Observer b2 = new UserB2("B2");
		subject2.addObserver(b2);
		subject2.statusChange();
    }
}
```
**실행 결과**
```aidl
A2업데이트~!
B2업데이트~!
A2업데이트~!
```

## 결론
> ###### 상황에 맞게 제공되는걸 쓰던 만들어 쓰던 해야한다.

---
## 관련 패턴
> ###### 중재자 패턴, MVC패턴