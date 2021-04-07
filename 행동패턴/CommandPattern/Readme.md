# 명령 패턴 (Command Pattern)
#### 행동의 호출을 객체로 캡슐화하여 실행 한다.

---

## 구성과 특징

#### 1. 구성요소
> ###### ```인터페이스```, ```명령```, ```리시버```, ```인보커```

#### 2. 매개변수
> ###### 커맨드 패턴은 동작을 객체화하여 매개변수 형태로 전달한다.
> ###### 전달 받은 객체를 바로 실행하는 것이 아니라 명령 객체로 프로퍼티에 저장한 후 임의의 시점에 일괄 실행하는 형태이다.

#### 3. 시점 제어
> ###### 작업의 요청과 처리를 분리하고 요청하는 작업들을 객체로 캡슐화하기 때문에 실행 시점을 제어할 수 있다.
> ###### 코드의 동작을 순차적으로 실행하지 않고 큐에 쌓아놓았다가 특정 시점에 호출한다.

#### 4. 저장
> ###### 명령을 실행하면서 동작을 임시로 저장할 수 있다. 이는 향후 실행에서 문제가 발생할 경우 재실행도 가능하다.

#### 5. 복구
> ###### 동작 행위를 저장해 기존의 동작을 취소 할 수 있다.

---
## 인터페이스
#### 1. 일관된 동작
> ###### 동작을 실행하는 방법이 클래스마다 다르다면 코드 재사용이 어려울 것이다.
> ###### 커맨드 패턴은 일관적인 코드 실행과 재사용을 위해 실행 메서드 호출을 하나로 통일한다.

#### 2. 설계
```java
public interface Command {
    public void run();
}
```
> ###### 모든 명령 객체는 위 인터페이스를 적용해야한다.

---
## 명령
#### 1. 명령 객체
```java
public class Exec1 implements Command{
    public Exec1() {
        System.out.println("Exec1 객체 생성");
    }

    @Override
    public void run() {
        System.out.println("Exec1 명령 실행");
    }
}
```
> ###### 위와같이 Command 인터페이스를 적용한 객체를 구현한다.
#### 2. 실행
```java
public class Main {

    public static void main(String[] args) {
        Command cmd1 = new Exec1();

        cmd1.run();
    }
}
```
#### 실행 결과
```aidl
Exec1 객체 생성
Exec1 명령 실행
```
> ###### 위의 예제는 간단한 메시지 출력이지만, 기능을 추가하여 복잡한 명령 동작의 코드를 작성할 수 있다.

---
## 리시버
> ###### 커맨드 패턴은 처리해야 하는 명령을 하나의 객체로 캡슐화한다.
> ###### 명령의 실행 동작을 내부적으로 구현하는 것과 달리 외부로부터 객체를 위임받아 대신 호출한다.

#### 1. 실제 동작
> ###### 분리된 실제 동작은 명령 객체에서 처리된다.
> ###### 다음 ```Concrete```클래스는 실제 동작을 처리하는 객체이다.
```java
public class Concrete {
    public void action1(){
        System.out.println("안녕하세요");
    }
    public void action2(){
        System.out.println("좋은 시간 되세요");
    }
}
```
#### 2. 객체 인자.
> ###### 위의 ```Concrete```클래스 (리시버)의 동작을 위임받는 명령 객체를 생성한다.
```java
public class Exec2 implements Command{
    private Concrete receiver;

    public Exec2(Concrete receiver) {
        this.receiver = receiver;
    }

    @Override
    public void run() {
        receiver.action1();
        receiver.action2();
    }
}
```
> ###### Concrete객체를 의존성 주입 받았다.
#### 3. 리시버 연결
```java
public class Main {

    public static void main(String[] args) {
        Concrete receiver = new Concrete();
        Command cmd2 = new Exec2(receiver);

        cmd2.run();
    }
}
```
***실행 결과***
```aidl
안녕하세요
좋은 시간 되세요
```

---
## 인보커
> ###### 명령 패턴은 다수의 명령 객체를 관리한다.
> ###### 인보커는 생성된 명령 객체를 저장하고 관리하는 역할을 한다.

#### 1. 설계
```java
public class Invoker {
    HashMap<String,Command> cmd = new HashMap<String,Command>();

    public void setCommand(String key, Command command){
        cmd.put(key, command);
    }
    
    public void removeCommand(String key){
        cmd.remove(key);
    }
    
    public void run(String key){
        if(cmd.containsKey(key)){
            cmd.get(key).run();
        }
    }
}
```
#### 2. 실행
```java
public class Main {

    public static void main(String[] args) {
        Command cmd3 = new Exec1();

        Concrete receiver2 = new Concrete();
        Command cmd4 = new Exec2(receiver2);

        Invoker invoker = new Invoker();
        invoker.setCommand("exec1", cmd3);
        invoker.setCommand("exec2", cmd4);

        invoker.run("exec2");
        invoker.run("exec1");

    }
}
```

***실행결과***
```aidl
Exec1 객체 생성
안녕하세요
좋은 시간 되세요
Exec1 명령 실행
```

#### 3. 익명 클래스 활용
```java
public class Main {

    public static void main(String[] args) {
        Invoker invoker2 = new Invoker();
        invoker2.setCommand("cmd1",
                new Command(){
                    @Override
                    public void run() {
                        System.out.println("익명 클래스로 실행한다~!");
                    }
                }
        );
        invoker2.run("cmd1");
    }
}
```
***실행결과***
```aidl
익명 클래스로 실행한다~!
```
> ###### 위와 같이 익명 클래스를 활용 할수 있다.

---
## Undo
> ###### Command 인터페이스에 Undo를 추가해 구현한다.
#### 1. undo 추가
```java
public interface Command {
    public void run();
    public void undo();
}
```
> ###### 인터페이스에 undo를 추가한다.

#### 2. 구현
```java
public class Exec1 implements Command{
    public Exec1() {
        System.out.println("Exec1 객체 생성");
    }

    @Override
    public void undo() {
        System.out.println("Exec1 명령 취소");
    }

    @Override
    public void run() {
        System.out.println("Exec1 명령 실행");
    }
}
```
#### 3. 실행
```java
public class Main {

    public static void main(String[] args) {
        Exec1 cmd5 = new Exec1();

        cmd5.run();
        cmd5.undo();
}
```

***실행 결과***
```aidl
Exec1 객체 생성
Exec1 명령 실행
Exec1 명령 취소
```

#### 4. Undo 상태 저장
> ###### 만일 여러 개의 명령객체가 순차적으로 실행됐다면 취소 동작도 역순으로 순차 실행돼야한다.
> ###### 인보커를 통해 마지막으로 실행한 명령 상태를 저장하는 방법을 사용한다.
> ###### 스택을 이용해서 아래와 같이 ```Invoker```를 수정해 구현해 보았다.
```java
public class Invoker {
    HashMap<String,Command> cmd = new HashMap<String,Command>();
    Stack<Command> undoStatus = new Stack<Command>();

    public void setCommand(String key, Command command){
        cmd.put(key, command);
    }

    public void removeCommand(String key){
        cmd.remove(key);
    }

    public void run(String key){
        if(cmd.containsKey(key)){
            Command command = cmd.get(key);
            undoStatus.push(command);
            command.run();
        }
    }

    public void undo(){
        if(!undoStatus.isEmpty()){
            Command command = undoStatus.pop();
            command.undo();
        }
    }
}
```
#### 4.1 실행
```java
public class Main {

    public static void main(String[] args) {

        Exec1 cmd6 = new Exec1();

        Concrete receiver3 = new Concrete();
        Exec2 cmd7 = new Exec2(receiver3);

        Invoker invoker3 = new Invoker();
        invoker3.setCommand("cmd1",cmd6);
        invoker3.setCommand("cmd2",cmd7);

        invoker3.run("cmd2");
        invoker3.run("cmd1");

        invoker3.undo();
        invoker3.undo();
        invoker3.undo();
    }
}
```

***실행 결과***
```aidl
Exec1 객체 생성
안녕하세요
좋은 시간 되세요
Exec1 명령 실행
Exec1 명령 취소
Exec2 취소!!
```
> ###### Exec2, Exec1순으로 실행되고 Undo는 역순인 Exec1, Exec2순으로 실행 되는것을 볼 수 있다..

---
## 관련패턴
> #### 복합체 패턴, 메멘토 패턴, 프로토타입 패턴