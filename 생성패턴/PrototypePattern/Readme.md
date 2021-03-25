# 프로토타입 패턴(Prototype Pattern)
#### ```new```키워드를 사용하지 않고 객체를 복제해 생성하는 패턴이다.
#### 특정 경우, 적은 리소스로 많은 객체가 생성 가능하다. 

---

## 객체 생성
```java
public class Hello {
    private String message;
    Hello(String s){
        this.message = s;
    }
    public String getMessage(){
        return this.message;
    }
}
...
    public static void main(String[] args) {
        Hello ko = new Hello("안녕하세요~!");
        System.out.println(ko.getMessage());
    }
...
```
> ###### Hello 클래스를 ```new```키워드를 통해 객체를 생성한 예제이다.
#### 실행 결과
```aidl
안녕하세요~!
```
> ###### ```new```를 통해 생성된 객체는 메모리에 할당 된다. 즉 시스템의 자운을 소모한다.
## 객체의 상탯값
> ###### 객체는 "캡슐화"를 통해 행동과 상태를 한곳에 묶어 관리한다.
> ###### 상탯값은 객체 생성시 또는 프로그램 실행중 설정및 변경 가능하다.
```java
    ...
        public static void main(String[] args) {
            Hello ko = new Hello("안녕하세요~!");
            Hello en = new Hello("Hello~!");
            System.out.println(ko.getMessage());
            System.out.println(en.getMessage());
        }
    ...
```
#### 실행 결과
```aidl
안녕하세요~!
Hello~!
```
> ###### 위와 같이 서로 다른 상태를 가진 객체를 만들기 위해서 객체를 여러개 생성해야한다.
> ###### 즉, 자원이 소모된다.

## 복사의 종류
#### 1. 얕은 복사 (Shallow Copy)

> ###### 참조를 복사한다.
```java
    public static void main(String[] args) {
        Hello ko = new Hello("안녕하세요~!");
        System.out.println(ko.getMessage());
        Hello ko2 = ko;
        ko2.setMessage("안녕~!");
        System.out.println(ko.getMessage());
        System.out.println(ko2.getMessage());
    }
```
#### 실행 결과
```aidl
안녕하세요~!
안녕~!
안녕~!
```
> ###### 위의 예제와 같이 1개의 객체를 2개의 변수가 참조 하고있는 형태이다.
#### 2. 깊은 복사 (Deep Copy)
> ###### 새로운 메모리 공간에 값을 복사한다.
> ###### 즉 복사후 영향을 끼치지 않는다.

```java
public class Hello implements Cloneable{
    private String message;
    Hello(String s){
        this.message = s;
    }
    public String getMessage(){
        return this.message;
    }
    public void setMessage(String s){
        this.message = s;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
...
    public static void main(String[] args) throws CloneNotSupportedException {
        Hello ko = new Hello("안녕하세요~!");
        System.out.println(ko.getMessage());
        Hello ko2 = (Hello) ko.clone();
        ko2.setMessage("안녕~!");
        System.out.println(ko.getMessage());
        System.out.println(ko2.getMessage());
    }
...
```
#### 실행 결과
```aidl
안녕하세요~!
안녕하세요~!
안녕~!
```
> ###### 위와같이 깊은 복사를 하기위해서 복사하고자 하는 객체(Hello)는 ```Cloneable```인터페이스를 implement해야한다.
> ###### 이후 ```Hello``` 클래스에서 ```clone()```을 오버라이드 한 후 clone() 메서드를 호출하면 깊은 복사가 된다.
> ###### 이때, ```message```의 타입이 ```String``` 과 같은 Immutable 클래스이기 떄문에 ```super.clone()```만 헀다.
> ###### 만약 ArrayList같은 mutable클래스가 상태값에 포함되어 있다면 모두 각각 clone()하는등 알맞게 오버라이딩을 해주어야 한다.

## 프로토타입 패턴
```java
public class Hello implements Cloneable {
    private String message;

    Hello(String s) {
        this.message = s;
        System.out.println("Hello 객체 생성");
    }
    ...
}

    ...
    public static void main(String[] args) throws CloneNotSupportedException {
        Hello ko = new Hello("안녕하세요~!");
        System.out.println(ko.getMessage());
        Hello ko2 = (Hello) ko.clone();
        ko2.setMessage("안녕~!");
        System.out.println(ko.getMessage());
        System.out.println(ko2.getMessage());
    }
    ...
```
#### 실행 결과
```aidl
Hello 객체 생성
안녕하세요~!
안녕하세요~!
안녕~!
```

> ###### 위의 예제는 이전 깊은 복사 예제에서 Hello객체의 생성자에 출력문을 한줄 추가한 것이다.
> ###### 실행 결과에서 알 수 있듯이 깊은복사를 이용해 생성자 호출없이 상태가 다른 객체를 생성 할 수 있다.

## 자원 절약
> ###### 객체 생성 과정을 생각해 보자.
> ###### 상태값을 설정을 위해 다른 메서드를 실행 시키고, 다른 객체에 접근해야한다.
> ###### 하지만, 복제를 이용해 객체를 생성하면 위와 같은 과정을 생략 가능하다. 즉 자원절약이 가능하다.
> ###### 예를들어, ```Hello```객체를 ```new```를 통해 생성하면 1초가 걸린고 복제의 경우 0.1초가 걸린다고 생각하면 복제를 이용하는게 효율적이다.

## 관련 패턴
> ###### 플라이웨이트 패턴, 메멘토 패턴, 복합체 패턴, 장식자 패턴, 명령 패턴