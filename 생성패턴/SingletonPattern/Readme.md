# 싱글톤 패턴
###### 자원 공유를 위해 객체 생성 개수를 1개로 제한한다.

---
## 자원 공유
> ###### ```new``` 키워드로 생성한 객체는 서로 독립된 자원을 갖는다. 즉, 서로 다른 메모리 영역을 차지 하고 있다.
> ###### 싱글톤 패턴은 개체의 생성을 하나로 제한하고 공유한다. 즉 하나의 자원을 공유 하기 편하다.

## 설계
### 1. Private 생성자
```java
class Config{
    private Config(){
        System.out.println("Config 인스턴스 생성");
    }
}
```
> ###### 위와 같이 생성자에 private로 접근을 제어하면 ```new```키워드를 통해 인스턴스를 생성하지 못한다.
> ###### 추가로 cloneable하다면 복제 또한 막아 주어야 인스턴스가 유일하게 존재 할 수 있다.
> ###### 즉, cloneable를 implements하지 말자.
### 2.인스턴스 생성
```java
class Config{
    private Config(){
        System.out.println("Config 인스턴스 생성");
    }
    public static Config getInstance(){
        return new Config();
    }
}
```
> ###### 위와 같이 public static 메서드를 통해 인스턴스를 생성한다.
> ###### 하지만, 위와 같은 방식이면 여전히 여러 인스턴스가 생성 될 수 있다.
> ###### 유일하게 만들기위해 아래와 같이 참조를 반환한다.
 ```java
class Config{
    private static Config self = null;
    private Config(){
        System.out.println("Cofing 인스턴스 생성.");
    }
    public static Config getInstance(){
        if(self == null){
            self = new Config();
        }
        return self;
    }
}

...
    public static void main(String[] args){
        Config c1 = Config.getInstance();
        Config c2 = Config.getInstance();
        Config c3 = Config.getInstance();
    }
...
```
#### 실행 결과
```
Config 인스턴스 생성.
```
> ###### 결과를 보면 알 수 있듯이 단 한번반 객체가 생성된다.

## 플라이웨이트 패턴
>###### 생성한 객체를 공유하는 패턴이다.
> ###### 싱글턴 패턴은 가장 간단한 형태의 플라이웨이트 패턴이 결합한 형태이다.
> ###### 추후 "구조 패턴" 공부 시 학습할 예정

## 확장
> ###### 싱글톤도 클래이스 이므로 상속할 수 있다. 하지만 생성자가 ```private```이므로 상속이 제한된다.
> ###### 따라서 생성자를 ```protected```로 바꿔 주면 외부에서 ```new```로 인스턴스를 생성 할 순없지만 상속은 가능하다.

```java
public class Config {
    private static Config self = null;
    protected Config(){
        System.out.println("Cofing 인스턴스 생성.");
    }
    public static Config getInstance(){
        if(self == null){
            self = new Config();
        }
        return self;
    }
}
public class Env extends Config{
    private static Env self = null;

    protected Env(){
        System.out.println("Env 객체를 생성합니다.");
    }
    public void setting(){
        System.out.println("시스템 환경을 설정합니다. ㅎㅎ");
    }
    public static Env getInstance(){
        if(self == null){
            System.out.println("Env 객체 생성 시작.");
            self = new Env();
        }
        System.out.println("Env 객체 반환");
        return self;
    }
}

...
    public static void main(String[] args) {
        Env e = Env.getInstance();
        e.setting();
    }
...
```
#### 실행 결과
```aidl
Env 객체 생성 시작.
Cofing 인스턴스 생성.
Env 객체를 생성합니다.
Env 객체 반환
시스템 환경을 설정합니다. ㅎㅎ
```
> ###### 위와 같은 방법으로 정확한 의미의 싱글톤은 아닐 수 있지만, 싱글톤 객체를 상속 할 수있다.
> ###### 위 와 같은 방식을 사용하면, Config의 인스턴스가 여러개 존재 할 수있다.(상속받은 클래수 수 +1 만큼)
> 
## 자원 처리
### 1. 경합 조건
> ###### 멀티 스레드 환경에서 위의 코드는 원자성이 결여 되어 2개의 객체가 만들어 질 수있다.
> ###### 이것을 해결하기위해 아래와 같이```static```블록을 이용 할 수 있다
```java
class Config{
    private static Config self = null;
    static{
        self = new Config();
    }
    private Config();
}
```
> ###### 하지만 위와 같은 방식은 메모리 자원이 아깝다.
> ###### Lazyholder를 이용한 싱글톤 패턴을 이용해 모든 문제를 해결한다. 코드는 아래와 같다.
```java
class Config {
    private static Config self = null;
    protected Config(){
        System.out.println("Cofing 인스턴스 생성.");
    }
    public static Config getInstance(){
        return LazyHolder.INSTANCE;
    }
    private static class LazyHolder{
        private static final Config INSTANCE = new Config();
    }
}
```
> ###### 위와 같은 방식을 이용하면, ```Config.getInstance()```를 호출 할때 메모리에 할당된다.
> ###### 따라서, 메모리 낭비도 방지하고 내부 클래스인 LazyHolder가 로딩 되는 순간 인스턴스가 생기므로 Thread 세이프하다.

## 관련패턴
> ###### 팩토리메서드, 추상팩토리, 빌더, 파사드, 프로트타입 패턴