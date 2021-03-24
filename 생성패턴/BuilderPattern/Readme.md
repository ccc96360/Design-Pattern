# 빌더 패턴
###### 추상 팩토리를 확장하여 크고 복잡한 객체를 생성할 수 있다.

---
## 복합 객체
#### 1. 상속
> ###### 전형적인 클래스 확장 방식은 상속이다.
> ###### 상속은 상위 클래스를 is-a 관계로 포괄하여 큰 규모의 객체를 생성하는 기법이다.
> ###### 그러나, 상속은 강력한 상하 결합 관계와 불필요한 모든 행위 까지 포함 된다는 단점이 있다.
#### 2. 의존성 주입
> ###### 상속의 단점을 개선하기 위해 의존성 주입을 사용한다.
> ###### 의존성 주입을 통해 복합 객체를 생성하여 사용하는 것을 권장한다.
#### 3. 복합 객체
> ###### 복합 객체란 하나의 객체가 다른 객체를 포함하는 관계 구조이다.
> ###### 복합 객체는 구조적 의존 관계를 통해 객체를 확장한다.
> ###### 복합 객체는 객체가 생성된 후에도 다른 객체와 관계를 설정해 동적 확장할 수 있다는 장점을 갖고 있다.
#### 4. 예제
> ###### ```Computer``` 복합 객체 예제이다.
```java
public class Cpu {
    private String model = "아주 좋은 CPU";
    Cpu(String model){
        this.model = model;
    }

    @Override
    public String toString() {
        return this.model;
    }
}

public class Ram {
    private int size;

    Ram(){
        this.size = 0;
    }
    public void setSize(int size){
        this.size = size;
    }
    @Override
    public String toString() {
        return this.size + "GB.";
    }
}

public class Storage {
    private int size;

    Storage(){
        this.size = 0;
    }
    public void setSize(int size){
        this.size = size;
    }
    @Override
    public String toString() {
        return this.size + "GB";
    }
}

public class Computer {
    private Cpu cpu;
    private Ram ram;
    private Storage storage;

    public Computer(){
        this.cpu = new Cpu();
        this.ram = new Ram();
        this.storage = new Storage();
        System.out.println("Computer 객체 생성");
    }
    public void setCpu(String model){
        this.cpu.setModel(model);
    }
    public void setRam(int size){
        this.ram.setSize(size);
    }
    public void setStorage(int size){
        this.storage.setSize(size);
    }
    @Override
    public String toString() {
        return "CPU: "+this.cpu.toString()+"\nRam: " + this.ram.toString() + "\nStorage: "+this.storage.toString();
    }
}
```
## 빌더
> ###### 빌더를 이용해 위의 ```Computer```객체와 같은 복합 객체의 생성 과정을 분리한다.
> ###### 생성 과정을 단계별로 분리함으로써 복합 객체의 생성을 일반화할 수 있다.
#### 생성 로직
> ###### 복합 객체의 구조는 종속적 이기 때문에 종속된 순서의 역순으로 객체를 생성하여 결합해야 한다.
> ###### 복합 객체에는 구조에 맞게 객체를 생성하고 관계를 설정하는 로직이 필요하다.
> ###### 복합 객체의 생성 로직을 일반 코드로 작성하면 객체 생성 과정을 효율적으로 관리하기 어렵다.
> ###### 따라서 빌더 패턴은 복합 객체 생성 과정을 별도의 독립된 클래스로 관리한다.
```java
public abstract class ComputerBuilder {
    protected Computer computer;

    abstract public ComputerBuilder setCpu(String model);
    abstract public ComputerBuilder setRam(int size);
    abstract public ComputerBuilder setStorage(int size);

    abstract public Computer build();
}

public class MyComputerBuilder extends ComputerBuilder{

    MyComputerBuilder(){
        computer = new Computer();
    }

    @Override
    public ComputerBuilder setCpu(String model) {
        computer.setCpu(model);
        return this;
    }

    @Override
    public ComputerBuilder setRam(int size) {
        computer.setRam(size);
        return this;
    }

    @Override
    public ComputerBuilder setStorage(int size) {
        computer.setStorage(size);
        return this;
    }

    @Override
    public Computer build() {
        return computer;
    }
}
...
    public static void main(String[] args) {
        ComputerBuilder builder = new MyComputerBuilder();
        Computer myPC = builder.setCpu("i7").setRam(32).setStorage(1024).build();
        System.out.println(myPC);
        Computer myPc2 = new MyComputerBuilder().setCpu("i3").setStorage(2048).setRam(64).build();
        System.out.println(myPc2);
    }
...
``` 
#### 실행 결과
```aidl
Computer 객체 생성
CPU: i7
Ram: 32GB.
Storage: 1024GB
Computer 객체 생성
CPU: i3
Ram: 64GB.
Storage: 2048GB
```
> ###### 위와 같이 Builder를 추상화 해 공통된 로직을 분리한다.
> ###### 이후 ```MyComputerBuilder```와 같이 하위 클래스에서 생성로직을 구현 한다.
> ###### 이렇게 "빌더 패턴"을 이용하면 클래스를 더 생성해야 하지만 불필요한 생성자 오버로딩이 줄어들고, 생성자의 매개 변수가 많을 시 실수 할 확률을 줄여준다.
> ###### 만약 빌더를 내부 클래스로 생성하고 기존 Setter를 제거 한다면 "불변 객체"로 객체를 생성 할 수 있다.
> > ###### 불변 객체: 객체가 생성된 후 상태가 변하지 않는다.

## 관련 패턴
> ###### 템플릿 메서드 패턴, 복합체 패턴, 추상 팩토리 패턴, 파사드 패턴