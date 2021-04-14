# 중재자 패턴 (Mediator Pattern)
### 분산된 다수의 객체 역할을 조정할 때 주로 사용된다.
### 즉, 복잡한 상호 의존성을 느슨한 관계로 풀어준다.

---
## 중재
#### 1. 분산
> ###### 객체지향의 특징은  모든 행동을 하나의 객체에 집중하여 처리하지 않는 다는 것이다.
> ###### 행동은 작은 단위로 분리되고, 목적 동작을 수행하기 위해 분리된 행도을 연결한다.

#### 2. 상호작용
> ###### 객체의 역할을 작은 객체로 분할하는 이유는 동작을 재사용하기 위해서 이다.
> ###### 객체의 행동을 작은 객체 단위로 분리하면, 목적 행동을 수행 하기위해 객체간에 의존 관계가 발생한다.
> ###### 이렇게 연관된 모든 객체들 간에 정보를 주고 받기 위해 복잡한 메시지 호출 동작이 발생한다.

#### 3. 행동 제약
> ###### 강력한 결합 구조는 객체의 재사용을 방해한다. 따라서 객체를 분리했지만, 분리된 객체는 강한 의존성 떄문에 독립적인 행동을 할 수 없다.
> ###### 즉, 객체의 행동제약이 발생한 것이다.

#### 4. 중재를 위한 관계 정리
> ###### 중재자는 객체 간 복잡한 상호 관계를 중재하며 객체 간에 복잡한 관계로 묶인 것을 재구성한다.
> ###### 즉 N:M 관게를 가진 객체를 느슨한 1:1 관계로 변경한다.
> ###### 중재자 패턴은 객체의 관계를 하나의 객체로 정리하는 패턴이다.

#### 5. 소결합
> ###### 복잡하게 얽히는 객체의 연관 관계를 중재자 객체에게 집중한다.
> ###### 중재자는 각각의 객체를 자신과 연결하고, 객체의 행위 요청을 중앙에서 제어한다.
> ###### 객체들은 더 이상 상호 간에 직접적으로 접근하지 않는 대신 중재자에게 필요한 행동을 요청한다.
> ###### 따라서 복잡한 객체의 구조를 단순화 하는 효과가 있다. 또한 복잡한 통신과 제어를 한 곳에 집중하여 처리하는 효과도 있다.

---
## 중재자

#### 1. 중재자와 동료
> ###### 중재자는 하나의 중재자와 여러 동료(Colleague)로 구성되어 있으며, 동료 객체의 강력한 결합 구조를 느슨한 결합 구조로 개선한다.
> ###### 동료 객체는 중재자를 통해 다른 동료 객체를 호출한다.

#### 2. 인터페이스
```java
public abstract class Mediator {
    List<Colleague> colleagues = new ArrayList<>();

    public void addColleague(Colleague colleague){
        colleagues.add(colleague);
    }

    abstract public void mediate(String data, String name);
}
```
> ###### 중재자는 관리할 동료 객체의 목록을 관리한다.

#### 3. ConcreteMediator
```java
public class Server extends Mediator{
    @Override
    public void mediate(String data, String name) {
        System.out.println(name+"로 부터 메시지");
        colleagues.forEach(colleague -> {
            colleague.message(data);
        });
    }
}
```
> ###### 메시지를 받는 서버이다.
> ###### 동료 객체가 메시지를 전송하면 등록된 모든 동료 객체에 메시지를 전달하고 전달된 메시지를 확인한다.

---
## 동료 객체

#### 1. 통신 경로
> ###### 중재자 패턴을 이용하더라도 처리할 동료 객체가 많으면 또 다른 문제가 발생한다.
> ###### 성능이 저하 되지 않도록 신경 써서 구상해야한다.

#### 2. 인터페이스
```java
public abstract class Colleague {
    protected Mediator mediator;

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }
    abstract void send(String data);
    abstract void message(String data);
}
```
#### 3. ConcreteColleague
```java
public class User extends Colleague{
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    void send(String data) {
        this.mediator.mediate(data, this.name);
    }

    @Override
    void message(String data) {
        System.out.println(this.name+"가 받은 메시지:"+data);
    }
}
```
> ###### 채팅을 위한 User 객체이다.
> ###### ```send()```는 중재자에게 처리를 요청하며, ```message()```는 중재자로부터 처리를 부여 받는 메서드이다.

#### 4. 실행
```java
    public static void main(String[] args) {
	    Server server = new Server();

	    User user1 = new User("User A");
	    User user2 = new User("User B");
	    User user3 = new User("User C");

	    user1.setMediator(server);
	    server.addColleague(user1);
	    user2.setMediator(server);
	    server.addColleague(user2);
	    user3.setMediator(server);
	    server.addColleague(user3);

	    user1.send("Hi~!");
	    user2.send("Hello~!");
	    user3.send("무~야~호~");
    }
```
**실행 결과**
```aidl
User A로 부터 메시지
User A가 받은 메시지:Hi~!
User B가 받은 메시지:Hi~!
User C가 받은 메시지:Hi~!
User B로 부터 메시지
User A가 받은 메시지:Hello~!
User B가 받은 메시지:Hello~!
User C가 받은 메시지:Hello~!
User C로 부터 메시지
User A가 받은 메시지:무~야~호~
User B가 받은 메시지:무~야~호~
User C가 받은 메시지:무~야~호~
```
---
## 관련패턴
> ###### 파사드 패턴, 옵저버 패턴