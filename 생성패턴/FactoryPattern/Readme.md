# 팩토리 패턴(기초)
###### 객체의 생성을 별도의 클래스로 분리하여 처리 한다.
---
## 강력한 결합
> ###### 아래 코드와 같이 Hello 객체 내부에 Korean 객체를 생성하면 두 객체 사이에 의존서이 발생한다.
> ###### 또한 new 키워드를 통해 클래스 이름을 직접 선택해 객체를 생성하면 "강력한 결합" 관계가 발생한다고 한다.
> ###### "강력한 결합" 코드는 유연한 코드 확장을 방해하고 변경과 수정을 어렵게 만든다.
> ###### 팩토리 패턴은 이런 "강력한 결합"을 "느슨한" 관계로 변경하는것이 목적이다.
```java
Korean.java
class Korean{
    public void text(){
        System.out.println("안녕하세요");
    }
}

Hello.java
class Hello{
    public void greeting(){
        Korean ko = new Korean();
        ko.text();
    }
}
```

```java
...
    public static void main(String[] args){
        Hello h = new Hello();
        h.greeting();
    }
...
```
**실행결과**
```
안녕하세요
```
---
## 의존성 주입
> ###### 객체의 관계가 외부적 요인으로 결합되는 것을 의존성 주입 이라고 한다.
> ###### 의존성 주입이 발생 되면 객체는 일반이 아닌 복합 객체 형태의 모습을 갖게 된다.
> ###### 아래는 예시 코드 이다.
```java
class Korean{
    public void text(){
        System.out.println("안녕하세요");
    }
}

class Hello{
    private Korean ko;
    Hello(Korean ko){
        this.ko = ko;
    }
    public void greeting(){
        this.ko.text();
    }
}
```
> ###### 위의 과정을 통해 두 클래스는 "종속적 연관" 관계를 갖는다.
---
## 느슨한 결합
> ###### 아래 코드와 같이 Factory란 객체에 객체를 생성하도록 책임을 위임하는것이다.

```java
interface Language{
    public void text();
}

class Korean implements Language{
    @Override
    public void text(){
        System.out.println("안녕하세요.");
    }
}

class Factory{
    static public Language getInstance(){
        return new Korean();
    }
}
```
> ###### 위의 과정에서 호출 증가로 프로그램 성능 저하를 초래 할 수 있지만, 내부적인 결합을 제거하고 동적으로 객체를 관리 할 수 있다는 장점이 있다.
---
## 클래스의 선택
> ###### 아래 코드와 같이 간단하게 여러 클래스들 중 하나를 선택해 생성/반환 할 수 있다.
> ###### 클래스를 추가 할 경우 간단하게 클래스와 조건만 추가하면 되므로 수정이 용이하다.
```java
interface Language{
    public void text();
}

class Korean implements Language{
    @Override
    public void text(){
        System.out.println("안녕하세요.");
    }
}

class English implements Language{
    @Override
    public void text(){
        System.out.println("Hello World.");
    }
}

class Factory{
    static final int KOR = 1;
    static final int ENG = 2;

    static public Language getInstance(int type){
        switch(type){
            case KOR:
                return new Korean();
            case ENG:
                return new English();
        }
        return null;
    }
}

class Hello{
    public void greeting(int type){
        Language lang = Factory.getInstance(type);
        lang.text();
    }
}
...
    public static void main(String[] args){
        Hello h = new Hello();
        h.greeting(Factory.KOR);
        h.greeting(Factory.ENG);
    }
...
```
###### 실행 결과

```
안녕하세요.
Hello World.
```

## 단순(정적) 팩토리

```java
class Hello{
    public void greeting(int type){
        Korean ko = this.factory();
        ko.text();
    }
    public Korean factory(){
        return new Korean();
    }
}
```
>###### 클래스 내부에 객체를 생성하는 factory 메서드를 갖는것이다.

## +
>###### 팩토리 메서드 패턴
>###### 추상 팩토리 패턴