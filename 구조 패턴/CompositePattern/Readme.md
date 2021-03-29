# 복합체 패턴 (Composite Pattern)
#### 객체 간의 계층적 구조화를 통해 객체를 확장하는 패턴이다.
#### 복합체는 재귀적으로 결합된 계층화된 트리 구조의 객체이다.

---

#### 복합화(Composition), 집단화(Aggregation)
> ###### 복합적인 객체 관계를 의미한다.
## 수평으로 객체 확장하기.
> ###### 기존 객체
```java
public class Monitor {
    private String name = "모니터";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
public class Computer {
    private Monitor monitor;
    private String name = "구성";
    
    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }
}
```
> ###### 복합 객체 내부에 객체를 추가하면서 확장된다.
```java
public class Memory {
    private String name="메모리";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class Disk {
    private String name="디스크";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class Computer {
    private Monitor monitor;
    private Memory memory;
    private Disk disk;
    private String name = "구성";

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public Disk getDisk() {
        return disk;
    }

    public void setDisk(Disk disk) {
        this.disk = disk;
    }
}
```
> ###### 위와 같이 ```Memory```와 ```Disk```객체를 추가로 포함 시키는 것이 수평으로 객체를 확장한다는 것이다.

## 수직으로 객체 확장하기.
```java
public class Screen {
    private int inch;

    public int getInch() {
        return inch;
    }

    public void setInch(int inch) {
        this.inch = inch;
    }
}
public class Monitor {
    private Screen screen;
    private String name = "모니터";

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getSize(){
        return screen.getInch();
    }
}
```
> ###### 위와 같이 Monitor 객체가 복합 객체가 되면 ```Computer```객체는 수직으로 확장된 것이다.
> ###### ```Computer```객체를 사용하는 Client 코드는 아래와 같다.
```java
    ...
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.setMemory(new Memory());
        computer.setDisk(new Disk());
        computer.setMonitor(new Monitor());
        computer.getMonitor().setScreen(new Screen());
        computer.getMonitor().getScreen().setInch(32);

        System.out.println(computer.getMonitor().getName());
        System.out.println(computer.getMonitor().getSize());
        System.out.println(computer.getDisk().getName());
        System.out.println(computer.getMemory().getName());
    }
    ...
```
#### 실행 결과
```aidl
모니터
32
디스크
메모리
```
## 복합체의 구조적 특징
#### 1. 재귀적 결합
> ###### 재귀적 결합을 통해 하나의 객체가 다수의 연결 객체를 가질 수 있으며 복합체가 객체를 포함할 때는 단일 객체, 복합 객체를 가리지 않는다.
> ###### 재귀적인 데이터 구조를 표현할 때 트리 구조를 자주 사용한다.
> ###### 복합 객체는 객체들을 하위 객체로 그룹화하는 특징이 있다.
> ###### 객체 그룹화를 통해 더 큰 집단의 객체로 확장하는데 이를 집단화(Aggregation)라고 한다.
#### 2. 복합체 패턴의 구성요소
> #### ```Component```, ```Composite```, ```Leaf```, ```Client``` 
> ###### 복합체는 하나의 구조 안에 또 다른 구조를 가진 모델을 설계할 때 많이 사용된다.
## 투명성을 이용한 동일한 설계
> ###### 복합체는 일반 객체, 복합 객체 구분 없이 재귀적 결합이 가능하다.
> ###### 모두 동일한 객체로 처리하여 트리 구조를 쉽게 활용한다.
#### 1. 투명성
> ###### 복합체 구성 요소인 ```Composite```와 ```Leaf```는  엄밀히 서로 다른 객체이다.
> ###### 하지만 2개의 객체 모두를 관리하기 위해 동일한 ```Component``` 인터페이스를 적용하며, 인터페이스에는 두 객체의 공통된 기능이 모두 포함된다.
> ###### 이렇게 ```Component```와 ```Leaf```객체를 구별하지 않고 동일한 인터페이스를 이용해 동일한 동작으로 처리하는 것을 "투명성"이라고 한다.
> ###### 투명성은 복합체 패턴의 특징이다.
#### 2. 불필요한 기능
> ###### 클래스의 일반화와 투명성은 객체 설계 시 불필요한 기능이 추가된다는 단점이 있다.
> ###### 하나의 객체에 2개 이상의 책임이 부여되기 떄문이다. 이는 객체지향 설계원칙 중 단일 책임과도 충돌된다.
> > ###### 단일 책임 원칙: 객체지향 설계 5대 원칙인 SOLID중 S(Single Responsibility Principle)에 해당한다.
> > ###### 모든 클래스는 각각 하나의 책임만을 가져야 하며, 그 책임을 완전히 캡슐화 해야 한다.
> ###### 이렇게 단일 책임 원칙을 위반하면서 투명성을 보장한다.
> ###### 따라서, 객체의 안정성이 떨어지며 원칙과 특징이 충돌 한다는 점에서, 복합체 패턴을 적용하기 위해 어떤 기준으로 패턴을 사용할지 미리 결정해야한다.
#### 3. 안전한 복합체
> ###### 투명성을 적용하지 않고 서로 다른 인터페이스를 적용하여 설계 할 수도있다.
> ###### 이럴 경우, ```instanceof``` 와 같은 메서드를 이용해 인터페이스를 검사 해야한다.

## 추상화를 통한 일반화
###### 파일 시스템 예제이다.
#### 1. Component
> ###### 복합체 패턴의 구성요소인 ```Component```이다.
```java
public abstract class Component {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    abstract void printSub(String path);
}

```
#### 2. Leaf
> ###### 복합체는 계층적인 트리 구조로 되어있다. 즉 복합체의 제일 마지막 객체이다.(리프 노드이다.)
> ###### 마지막 객체이므로 다른 객체를 포함하지 않지만, 복합체 패턴을 사용 할 수있다.
> ###### 죽, 복합체 패턴일 경우 객체를 추가로 더 확장 할 수 있게된다.
```java
public class File extends Component {
    private int data;

    public File(String name) {
        this.setName(name);
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    @Override
    void printSub(String path) {
        System.out.println(path+ "\\" +this.getName());
    }
}
```
#### 3.Composite
> ###### 내부 노드에 해당한다.
```java
public class Folder extends Component{
    private List<Component> children = new ArrayList<Component>();

    public Folder(String name) {
        this.setName(name);
    }

    public void addNode(Component folder){
        this.children.add(folder);
    }
    public void remove(Component folder){
        this.children.remove(folder);
    }

    @Override
    void printSub(String path) {
        String mPath =  path + "\\" + this.getName();
        System.out.println(mPath + "폴더 탐색 시작");
        for(Component child: children){
            child.printSub(mPath);
        }
        System.out.println(mPath + "탐색 종료");
    }
    
    void printSub() {
        String mPath = "\\" + this.getName();
        System.out.println(mPath + "폴더 탐색 시작");
        for(Component child: children){
            child.printSub(mPath);
        }
        System.out.println(mPath + "탐색 종료");
    }
}
```
#### 4. Client
> ###### 실행 코드이다.
```java
    ...
    public static void main(String[] args) {
        Folder root = new Folder("root");
        Folder home = new Folder("home");
        Folder minjae = new Folder("minjae");
        Folder ccc96360 = new Folder("ccc96360");
        Folder users = new Folder("users");
        Folder temp = new Folder("temp");

        File img1 = new File("img1");
        File img2 = new File("img2");
        File img3 = new File("img3");
        File img4 = new File("img4");

        root.addNode(home);
        root.addNode(users);
        users.addNode(minjae);
        minjae.addNode(img1);
        minjae.addNode(img2);
        users.addNode(ccc96360);
        ccc96360.addNode(img3);
        ccc96360.addNode(img4);
        root.addNode(temp);

        root.printSub();

        System.out.println("ccc96360 삭제");
        users.remove(ccc96360);
        root.printSub();
        }
    ...
```
> ###### 위의 코드는 아래와 같은 디렉토리 구조를 나타낸 것 이다.
```aidl
root
|--- home
|--- users
    |--- minjae
        |--- img1
        |--- img2
    |--- ccc96360
        |--- img3
        |--- img4
|--- temp
```

#### 출력 결과
```aidl
\root폴더 탐색 시작
\root\home폴더 탐색 시작
\root\home탐색 종료
\root\users폴더 탐색 시작
\root\users\minjae폴더 탐색 시작
\root\users\minjae\img1
\root\users\minjae\img2
\root\users\minjae탐색 종료
\root\users\ccc96360폴더 탐색 시작
\root\users\ccc96360\img3
\root\users\ccc96360\img4
\root\users\ccc96360탐색 종료
\root\users탐색 종료
\root\temp폴더 탐색 시작
\root\temp탐색 종료
\root탐색 종료
ccc96360 삭제
\root폴더 탐색 시작
\root\home폴더 탐색 시작
\root\home탐색 종료
\root\users폴더 탐색 시작
\root\users\minjae폴더 탐색 시작
\root\users\minjae\img1
\root\users\minjae\img2
\root\users\minjae탐색 종료
\root\users탐색 종료
\root\temp폴더 탐색 시작
\root\temp탐색 종료
\root탐색 종료


```
## 적용사례
> ###### 예제와 같은 파일구조 이외에도, 쇼핑몰 상품 관리, 회원 관리등 트리 구조에 적용할수 있다.
> ###### 그래픽처리에서 도형의 정보를 트리화하여 객체를 처리한다.

## 장단점 및 결과
> ###### 복합체 패턴은 클래스의 일관된 계통을 정의할 수 있다.
> ###### 코드가 매우 단순해지며 새로운 구성 요소를 추가하거나 삭제하는 것도 편리해진다.
#### 1. 장점
> ###### 복합체 패턴으로 트리구조를 구현 하면, 트리를 추가 하거나 이동, 삭제등 전체적인 구조를 유지하는데 매우 유용하다.
> ###### 투명성을 이용해 클라이언트의 사용을 단순화 할 수 있다.
#### 2. 단점
> ###### 복합체 패턴은 수평적, 수직적 모든 방향으로 객체를 확장할 수 있지만, 수평적 방향으로만 확장이 가능하도록 Leaf를 제한하는 Composite를 만들기는 어렵다.

## 관련 패턴
> ###### 체인패턴, 명령 패턴, 방문자 패턴, 장식자 패턴, 반복자 패턴