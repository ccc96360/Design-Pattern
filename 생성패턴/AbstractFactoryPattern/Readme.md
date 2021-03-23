# 추상 팩토리 패턴
> ###### 팩토리 메서드 패턴을 확장했다. 생성패턴을 그룹 형태로 변경한다.

## 패턴의 유사성
> ###### 1. 팩토리 패턴을  팩토리 메서드 패턴으로 변경할 때는 "추상화"를 사용헀다.
> ###### 2. 팩토리 메서드과 추상 팩토리 패턴의 차이는 추상화된 "그룹"을 형성하고 관리하는 것 이다.
> > ###### 팩토리 메서드는 하나의 메서드가 여러 객체를 생성 추상 팩토리는 클래스별 단 하나의 객체

## 그룹
> ###### 여러개의 팩토리 메서드를 그룹을 묶은 것과 유사하다.
```java
public abstract class Factory {
    abstract public Tire createTire();
    abstract public Door createDoor();
}
```
> ###### 자동차 공장이다.

## 하위클래스
### 1. 한국 공장

```java
public class KoreaTire implements Tire{
    KoreaTire(){
        System.out.println("국산 타이어~");
    }
}
public class KoreaDoor implements Door{
    KoreaDoor(){
        System.out.println("한국 문~");
    }
}
public class KoreaFactory extends Factory{
    @Override
    public Tire createTire() {
        return new KoreaTire();
    }

    @Override
    public Door createDoor() {
        return new KoreaDoor();
    }
}
```

### 2. 미국공장
```java
public class USATire implements Tire{
    USATire(){
        System.out.println("미국 타이어~");
    }
}
public class USADoor implements Door{
    USADoor(){
        System.out.println("미국 문~");
    }
}
public class USAFactory extends Factory{
    @Override
    public Tire createTire() {
        return new USATire();
    }

    @Override
    public Door createDoor() {
        return new USADoor();
    }
}
```
> ###### 위와 같이 추상 클래스에서 정의한 인터페이스 규격에 맞게 새로운 그룹의 내용을 구현하고 추가한다.
> ###### 따라서 각각의 그룹은 서로 독립적이다.

## 공장을 이용해서 차를 만들어 보자
```java
public class FordCar implements Car{
    Factory CountryFactory;
    Tire tire;
    Door door;
    
    public FordCar(Factory countryFactory){
        this.CountryFactory = countryFactory;
    }
    @Override
    public void build() {
        System.out.println("Ford 조립!");
        this.tire = CountryFactory.createTire();
        this.door = CountryFactory.createDoor();
    }
}

public class KiaCar implements Car{
    Factory CountryFactory;
    Tire tire;
    Door door;

    public KiaCar(Factory countryFactory){
        this.CountryFactory = countryFactory;
    }

    @Override
    public void build() {
        System.out.println("기아차 조립합니다.");
        tire = CountryFactory.createTire();
        door = CountryFactory.createDoor();
    }
}
...
    public static void main(String[] args) {
        Factory koreaFactory = new KoreaFactory();
        Factory usaFactory = new USAFactory();
        Car myCar1 = new KiaCar(koreaFactory);
        Car myCar2 = new FordCar(usaFactory);

        myCar1.build();
        myCar2.build();

    }
...
```
##### 실행 결과
```aidl
기아차 조립합니다.
국산 타이어~
한국 문~
Ford 조립!
미국 타이어~
미국 문~
```
## 새로운 부품 추가
> ###### 만약 ```Engine``` 이라는 새로운 객체를 추가해야 된다면, 모든 클래스에 ```Engine``` 부품 관련 코드를 삽입해야 한다.
## 단점
> ###### 새로운 종류의 군을 추가하는 것이 쉽지 않다. 이는 새로운 군을 추가하여 확장할 때 모든 서브 클래스들이 동시에 변경돼야 하는 추상 팩토리의 특징 때문이다.
> ###### 즉 확장성이 구리다.
> ######  그룹이 계층적 구조를 가지며 계층을 확장하면서 그룹을 관리한다. 따라서 계층이 커질수록 문제가 발생한다.

## 관련 패턴
> ###### 빌더패턴, 팩토리 메서드 패턴, 복합체 패턴, 싱글톤 패턴