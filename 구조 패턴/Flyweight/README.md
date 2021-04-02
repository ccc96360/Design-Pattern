# 플라이웨이트 패턴 (Flyweight Pattern)
#### 객체를 공유하기 위해 구조를 변경하는 패턴
#### 객체를 공유 하면 객체를 재사용할 수 있어 시스템 자원이 절약 된다.

---
## 자원 공유
> ###### 중복되는 객체를 매번 생성하는 것이 아니라 생성된 객체를 공유하여 재사용하는 방법
> ###### 객체가 재사용 되기때문에 메모리가 절약된다.

#### 1. 객체 재사용
> ###### 다음 코드를 보자.
```java
public class Hello {

    public void print(String msg){
        System.out.println(msg);
    }

}
public class Korean {

    private Hello hello;

    public Korean(Hello hello) {
        this.hello = hello;
    }

    public void hello(){
        hello.print("안녕하세요.");
    }

}
public class English {

    private Hello hello;

    public English(Hello hello) {
        this.hello = hello;
    }

    public void hello(){
        hello.print("Hello.");
    }

}
```
> ###### 중복 코드와 메모리 할당을 줄이기 위해 객체의 의존성을 주집한다.
```java
    ...
    public static void main(String[] args) {
        Korean ko = new Korean(new Hello());
        English en = new English(new Hello());

        ko.hello();
        en.hello();
    }
    ...
```
> ###### 하지만 위와 같이 코딩하면 Hello의 인스턴스가 2개가 생긴다.
> ###### 따라서 아래와 같은 방법으로 코딩해야 메모리를 절약 할 수 있다
```java
    ...
    public static void main(String[] args) {
        Hello hello = new Hello();

        Korean ko = new Korean(hello);
        English en = new English(hello);
        
        ko.hello();
        en.hello();
    }
    ...
```
> ###### 재사용을 달리 말하면 공유라 할 수 있다.
#### 2. 공유 저장소
> ###### 플라이 웨이트 패턴은 효율적인 공유 객체를 관리하기 위해 별도의 저장소를 갖고 있는데 이를 공유 저장소라고 한다.
> ###### 플라이웨이트 패턴은 팩토리 클래스에 공유 저장소를 추가하여 객체의 중복 생성 동작을 제한한다.
```java
public class Hello {

    public Hello() {
        System.out.println("Hello객체 메모리에 할당됨");
    }

    public void print(String msg){
        System.out.println(msg);
    }

}
public class Factory {
    private static final HashMap<String, Hello> pool = new HashMap<>();

    public static Hello make(String name){
        System.out.println("Hello 객체 생성 요청!");
        Hello hello = (Hello) pool.get(name);
        if(hello == null){
            hello = new Hello();
            pool.put(name, hello);
        }
        return hello;
    }
}
    ...
    public static void main(String[] args) {
        Hello hello1 = Factory.make("Hello");
        Hello hello2 = Factory.make("Hello");

        Korean ko = new Korean(hello1);
        English en = new English(hello2);
        Korean ko2 = new Korean(Factory.make("Hello"));

        ko.hello();
        en.hello();
        ko2.hello();
    }
    ...
```
#### 실행 결과
```aidl
Hello 객체 생성 요청!
Hello객체 메모리에 할당됨
Hello 객체 생성 요청!
Hello 객체 생성 요청!
안녕하세요.
Hello.
안녕하세요.
```
---
## 상태 구분
> ###### 객체 공유는 본질적 공유와 부가적 공유로 구분한다.
#### 1. 본질적 상태
> ###### 공유하는 객체가 메스다만으로 구성돼 있다면 상관없지만 데이터를 포함한 객체라면 공유시 문제가 발생한다.
> ###### 공유되는 개체의 데이터가 변경되면 참조되는 모든 다른 객체에도 영향을 미치는데 이를 사이드 이펙트라고 한다.
> ###### 따라서 안정적으로 객체를 공유하려면 어떤 변경도 없이 객체를 있는 그대로 참조해서 사용해야 한다.
> ###### 이러한 상태를 본질적 상태라고 한다.
> ###### 본질적 상태 공유는 객체의 상태값에 따라 달라지지 않고 동일하게 적용되는 것을 말한다.
> > ###### 본질적 상태의 공유객체를 shared 라고 한다.

#### 2. 부가적 상태
> ###### 객체를 공유할 때 상태값에 따라 달라지는것을 말한다. 즉, 객체의 값에 따라 종속적인 상태가 된다.
> ###### 부가적 상태로 객체를 사용하는 경우는 객체의 특정 데이터값을 변경해 참조하는 다른 객체에 영향을 주기 위해서다.
> ###### 공유 객체가 상태값에 종속적인 상태면 플라이 웨이트 패턴으로 공유할 수 없다. 사이드 이펙트가 발생하기 때문이다.
> > ###### 부가적 상태의 공유객체를 unshared 라고 한다.

#### 3. 사이드 이펙트
> ###### 하나의 동작이나 값이 다른 동작과 값에 영향을 줄 경우, 이들은 서로 종속적이며 강력하게 결합 돼 있다고 표한한다.
> ###### 종속적 결합 관계를 주의해서 작성하지 않으면 예상치 않은 오동작이 발생할 수 있다.

#### 4. 독립 객체
> ###### 독립 객체는 공유되지 않고 각각의 상황에 맞게 생성된 객체이다. 즉, 독립적인 동작을 수행한다.
> ###### 플라이웨이트 패턴을 실제 현장에서 사용하기 어려운 이유는 개발 과정에서 공유 객체와 독립 객체를 쉽게 구별할 수 없기 때문이다.

---
## 실습
> ###### 모스 부호를 출력하는 공유 객체를 만들어 메시지를 출력하는 예제이다.
#### 1. Flyweight 인터페이스
```java
public interface Flyweight {
    public void code();
}
```

#### 2. ConcreteFlyweight 인터페이스
```java
public class MorseG implements Flyweight{
    public MorseG() {
        System.out.println("MorseG 객체 생성");
    }

    @Override
    public void code() {
        System.out.print("*");
        System.out.print("*");
        System.out.print("-");
        System.out.print("*");

        System.out.print(" ");
    }
}
public class MorseO implements Flyweight{
    public MorseO() {
        System.out.println("MorseO 객체 생성");
    }

    @Override
    public void code() {
        System.out.print("-");
        System.out.print("-");
        System.out.print("-");

        System.out.print(" ");
    }
}
public class MorseL implements Flyweight{
    public MorseL() {
        System.out.println("MorseL 객체 생성");
    }

    @Override
    public void code() {
        System.out.print("*");
        System.out.print("-");
        System.out.print("*");
        System.out.print("*");

        System.out.print(" ");
    }
}
public class MorseE implements Flyweight{
    public MorseE() {
        System.out.println("MorseE 객체 생성");
    }

    @Override
    public void code() {
        System.out.print("*");

        System.out.print(" ");
    }
}
```
> ###### 본질적 상태의 공유 객체 4개이다. 각각의 객체는 해당 알파벳에 해당하는 모스 부호를 출력한다.

#### 3. Flyweight Factory 인터페이스
> ###### 플라이웨이트 패턴에서는 공유되는 객체의 인스턴스를 직접 생성하지 않는다.
> ###### 플라이웨이트 패턴은 공유하는 객체를 모두 똑같은 방식으로 제어할 때 유용하다.
> ###### 특정 객체만 다른 형식으로 구현해서 처리하는 것은 어려우며 플라이웨이트 특성상 모두 동일하게 처리한다.
```java
public class FlyweightFactory {
    private static final HashMap<String, Flyweight> pool = new HashMap<>();

    public static Flyweight getCode(String alphabet){
        alphabet = alphabet.toUpperCase();
        Flyweight morseAlpha = (Flyweight) pool.get(alphabet);

        if(morseAlpha == null){
            try {
                morseAlpha = (Flyweight) Class.forName("pattern.flyweight.Morse" + alphabet).newInstance();
                pool.put(alphabet, morseAlpha);
            }
            catch (Exception e){

            }
        }
        return morseAlpha;
    }
}
```
#### 4. Client
```java
public class Main {
    public static void main(String[] args) {
		String name = "gooogllleee";
		for(String letter: name.split("")){
			System.out.print(letter + "=>");
			FlyweightFactory.getCode(letter).code();
			System.out.println();
		}
    }
}
```
#### 실행결과
```aidl
g=>MorseG 객체 생성
**-* 
o=>MorseO 객체 생성
--- 
o=>--- 
o=>--- 
g=>**-* 
l=>MorseL 객체 생성
*-** 
l=>*-** 
l=>*-** 
e=>MorseE 객체 생성
* 
e=>* 
e=>* 
```
> ###### 중복된 알파벳은 이미 생성된 객체를  참조하여 출력한다.

---
## 관련패턴
> #### 프록시 패턴, 복합체 패턴, 싱글톤 패턴, 전략패턴, 상태 패턴
> 