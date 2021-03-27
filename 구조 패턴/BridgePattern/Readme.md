# 브릿지 패턴(Bridge Pattern)
#### 객체의 확장성을 향상하기 위한 패턴이다.
#### 핸들패턴(Handle Pattern) 구현부 패턴이라고도 한다.
#### 동작을 처리하는 구현부, 확장을 위한 추상부를 분리한다. 

---

## 복잡한 코드
#### 1. 최초 설계
> ###### 다음은 간단한 인사말을 제공하는 객체이다.
```java
public class Hello {

    public String greeting(){
        return "Hello";
    }
}
    ...
    public static void main(String[] args) {
        Hello hello = new Hello();
        System.out.println(hello.greeting());
    }
    ...
```
###### 실행 결과
```aidl
Hello
```
> ###### 이때 고객의 요청에 따라 다른 언어로도 인사말을 제공할 필요가 생겼다고 가정하자.

#### 2. 코드의 변질
> ###### 새로운 변경 사항이 발생하면 기존 코드를 수정해야 한다.
> ###### 다음은 수정한 코드이다.
```java
    public String greeting(String lang){
        if(lang.equals("Korean")){
            return "안녕하세요.";
        }
        else {
            return "Hello";
        }
    }
```
> ###### 예상치 않았던 작업을 원본 코드에 추가하여 기존에 없던 매개변수 인자값이 추가됐다.
> ###### 원본 객체는 매개변수 추가로 인해 인터페이스와 동작 코드가 변경 됐다.
> ###### 인터페이스가 변경 됐으므로, 이를 호출 실행하는 다른 코드도 같이 변경돼야한다.
> ###### 다음은 변경된 호출 코드 이다.
```java
    ...
    public static void main(String[] args) {
        Hello hello = new Hello();
        System.out.println(hello.greeting("Korean"));
    }
    ...
```
###### 실행 결과
```aidl
안녕하세요.
```
> ###### 처음엔 깔끔했던 코드가 새로운 코드를 추가 할수록 지저분해진다.
> ###### 즉 가독성이 떨어지고 유지보수가 힘들어진다.

## 상속
> ###### 객체지향은 요구되는 행위를 객체화하여 처리한다.
> ###### 이때, 다양한 행위를 위해 클래스는 다른 클래스를 포함하고 상속을 통해 기능을 확장해 나간다.

#### 1. 상속 확장
> ###### 다음은 이전 예시를 상속 확장 형태로 다시 만든 코드이다.
```java
public class Hello {

    public String greeting(){
        return "Hello";
    }
}
public class Greeting extends Hello{
    public String ko(){
        return "안녕하세요.";
    }
}
```
> ###### ```Hello``` 클래스를 수정하지 않고 ```Hello```클래스를 상속 받는 새로운 Greeting클래스를 생성했다.
```java
    ...
    public static void main(String[] args) {
        Greeting greeting = new Greeting();
        System.out.println(greeting.ko());
        System.out.println(greeting.greeting());
    }
    ...
```
> ###### 하위 클래스는 상위 클래스의 모든 메서드와 프로퍼티를 사용할 수 있다.
> ###### 따라서 아래와 같은 결과가 나온다.
###### 실행 결과
```aidl
안녕하세요.
Hello
```
#### 2. 상속의 문제점
> ###### 클래스를 상속하면 구현과 추상 개념이 영구적으로 결합 된다.
> ###### 즉, 기능을 상속으로 확장하면 최종 클래스가 무거워 진다. (불필요한 메서드도 포함되기 떄문에)

## 패턴 설계
> ###### 상속은 강력함 결합 관계가 발생하므로 느슨한 결합 관계로 변경해야한다.
> ###### 느슨한 결합 관계로 변경하는 방법 중 대표적인 것이 위임(Delegate)이다.
> ###### 위임을 통해 객체를 복합객체 구조로 리팩터링한다.
> 
> ###### 다음 4개의 구셩요소로 설계한다.
> > ###### ```Implementor```, ```ConcreteImplementor```, ```Abstract```, ```RefinedAbstract```
#### 1. 계층 분리
> ###### 먼저, 인사말 기능을 분리한다. ```Implementor```에 해당한다.
```java
public interface Hello {
    public String greeting();
}
```
> ###### 다음으로 ```Implementor```를 적용한 ```ConcreteImplementor```를 설계한다.
```java
public class Korean implements Hello{
    @Override
    public String greeting() {
        return "안녕하세요.";
    }
}
public class English implements Hello{
    @Override
    public String greeting() {
        return "Hello";
    }
}
    ...
    public static void main(String[] args) {
        Korean ko = new Korean();
        English en = new English();
        System.out.println(ko.greeting());
        System.out.println(en.greeting());
    }
    ...
```
###### 실행 결과
```aidl
안녕하세요.
Hello
```
> ###### 위와 같이 인터페이스를 이용해 구현부를 클래스로 분리 했다.
#### 2. 복합구조
> ###### 위의 분리된 2개의 구현 클래스를 복학 객체로 연결한다.
```java
public class Language {
    public Korean ko;
    public English en;
    Language(){
        ko = new Korean();
        en = new English();
    }
}
```
> ###### ```Language```객체의 프로퍼티로  구현 클래스를 갖고 있다.
> ###### 다음은 실행 예제이다.
```java
    ...
    public static void main(String[] args) {
        Language lang = new Language();
        System.out.println(lang.ko.greeting());
        System.out.println(lang.en.greeting());
    }
    ...
```
###### 실행 결과
```aidl
안녕하세요.
Hello
```
#### 3. 추상 계층
> ###### 위의 예제에서 구현부는 하나의 계층으로 설계했다.
> ###### 하나의 계층만으로 설계된 복합 객체는 브릿지 패턴이 아니다.
> ###### 브릿지 패턴은 복합 객체를 다시 재정의하여 추상 계층화된 구조이다.
> ###### 추상화 변경을 하는 이유는 각각의 계층이 독립적으로 확장/변경이 가능하도록 하기 위해서이다.

#### 4. 계층 연결
> ###### 브릿지는 구현 계층과 추상 계층 두 곳을 연결하는 다리라는 의미이다.

#### 5. 추상 재설계
> ###### 다음은 브릿지 패턴 구성 요소중 ```abstract```이다.
```java
public abstract class Language {
    public Hello language;

    public abstract String greeting();
}
```
> ###### 위처럼 ```implementor```인```Hello```인터페이스를 가지고 있다.
> ###### 다음은 ```abstract```를 이용해 만든```refinedAbstract```이다.
```java
public class Message extends Language{

    Message(Hello lang){
        this.language = lang;
    }
    @Override
    public String greeting() {
        return this.language.greeting();
    }
}
    ...
    public static void main(String[] args) {
        Message message1 = new Message(new Korean());
        Message message2 = new Message(new English());

        System.out.println(message1.greeting());
        System.out.println(message2.greeting());
    }
    ...
```
> ###### 위의 예제처럼 분리된 계층을 연결하여 인사말을 출력한다. 
> ###### ```Message```객체는 의존성 주입을 통해 어떤 동작을 할지 결정된다.
###### 실행 결과
```aidl
안녕하세요.
Hello
```
## 장점
> ###### 분리된 2개의 추상 계층과 구현 계층은 독립적인 확장이 가능하다.
> ###### 기존 시스템에 부수적인 새로운 기능들을 지속적으로 추가할 때 사용하면 유용한 패턴이다.

## 단점
> ###### 코드 디자인 설계가 다소 복잡해진다.

## 관련 패턴
> ###### 템플릿 메서드 패턴, 추상 팩토리 패턴, 어댑터 패턴

## vs 어댑터 패턴
> ###### 어댑터 패턴은 완성된 코드를 통합하고 결합할 떄 사용되는 패턴이라면, 브릿지 패턴은 처음 설계 단계에서 추상화 및 구현을 위해 확장을 고려한 패턴이다.