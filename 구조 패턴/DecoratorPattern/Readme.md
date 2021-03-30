# 장식자 패턴 (Decorator Pattern)
#### 객체에 동적 기능을 추가하기 위해 구조를 개선하는 패턴이다.
#### 다양한 확장을 위해 객체를 조합한다.

---

## 상속을 통한 기능 추가
> ###### 상위 클래스와 하위 클래스 간에 강력한 결합 관계가 생성된다.
> ###### 이러한 강력한 결합 관계는 객체의 유연한 확장을 방해한다.
> ###### 또한, 새로운 기능을 추가할 때 마다 오버라이드하여 변경해야한다.
> ###### 오버라이드시 불필요한 상위 메서드가 남는다.
> ###### 상속은 정적 방식으로 기능을 확장하기 때문에, 객체를 상황에 맞게 동적으로 확장해야 하는경우 구현이 쉽지 않다.

## 조건 추가
> ###### 하나의 단일 행위만 처리하는 것과 달리 여러 행위를 처리해야 하는 경우가 있다.
> ###### 이때 여러 행위를 구분하기 위해 조건을 추가한다.
> > ###### 여기서 조건이란 다른 행위를 하기위해 기존의 행위를 확장하는 것과 유사하다.
#### 1. 옵션
> ###### 하나의 동작에 부수적인 기능을 추가하는 경우, 추가 기능을 처리하기 위해 객체의 행위를 확장해야 한다.
> ###### 하지만, 몇 개의 부수적 행위(옵션)가 추가 되는지는 정확하게 알 수 없다.

#### 2. 동적 확장을 위한 구성(Composition)
> ###### 장식자 패턴은 동적으로 객체를 결합하기 위해서 구성을 통해 확장한다.
> > ###### 구성: 상위클래스로 하려고 했던 클래스를 하위클래스로 하려고 했던 클래스의 private 필드에서 참조 하는것.

## 확장
#### 1. 동적 추가
> ###### 런타임에서 객체에 새로운 책임을 추가할 수 있다.
> ###### 객체를 복합객체로 구성하면 위임을 통해 객체를 생성한 후에도 동적으로 구조를 변경할 수 있다.
#### 2. 단일화
> ###### 장식자(Decorator)는 기존의 객체에 새로운 부가 행위를 추가하며 추가하는 행위는 단위별로 분리된다.
> ###### 장식자 패턴은 많은 종류의 작은 객체를 단일화해서 결합한다.
#### 3. 축소
> ###### 장식자는 새로운 기능을 추가하는 것 외에 어떤 기능을 제거하기 위해서도 사용할 수 있다.
> ###### 이는 객체의 내부를 변경하는 것과 유사하며 내부를 변경하는 유사 패턴으로 전략(strategy)패턴이 있다.

## 객체에 장식 추가하기
> ###### 베이스 객체를 시작점으로 장식을 추가해 객체를 확장한다.
#### 1. 래퍼(Wrapper)
> ###### 장식자는 기본이 되는 객체를 감싸 새로운 객체로 확장한다.
> ###### 감쌀때 행위를 추가하여 확장한다.
#### 2. 객체의 투명성
> ###### 객체를 감싸면 또 다른 객체로 파생된다.
> ###### 장식자 패턴으로 확장된 파생 객체는 요청된 행위를 중간에서 가로채 더 확장된 행위로 대신 처리한다.
> ###### 확장된 객체는 동일한 인터페이스를 적용한다.
> ###### 클라이언트는 요청된 객체가 원본인지 파생된 객체인지 모른다. 죽 투명성이 부여 되었다.

## 구성요소
> ###### ```Component```, ```ConcreteComponent```, ```Decorator```, ```ConcreateDecorator```가 있다.
> ###### 쇼핑몰에서 상품과 옵션을 선택하는 예제이다.
#### 1. Component
> ###### 공통 기능을 정의하는 인터페이스 이다.
```java
public interface Component {
    public String product();
    public int price();
}
```
#### 2. Concrete Component
> ###### ```Component```를 구현한다.
```java
public class Product1 implements Component{
    @Override
    public String product() {
        return "원피스";
    }

    @Override
    public int price() {
        return 20000;
    }
}
public class Product2 implements Component{
    @Override
    public String product() {
        return "블라우스";
    }

    @Override
    public int price() {
        return 25000;
    }
}
```
#### 3. Decorator
> ###### ```Component```인터페이스를 적용해 추상 클래스를 생성한다.
```java
public abstract class Decorator implements Component{
    @Override
    public abstract String product();

    @Override
    public abstract int price();
}
```
#### 4. Concrete Decorator
> ###### ```Decorator```를 이용해 구현한다.
```java
public class i7 extends Decorator{
    private Component base;
    public i7(Component base) {
        this.base = base;
    }

    @Override
    public String product() {
        return this.base.product()+",i7";
    }

    @Override
    public int price() {
        return this.base.price()+475000;
    }
}
public class ssd256 extends Decorator{
    private Component base;

    public ssd256(Component base) {
        this.base = base;
    }

    @Override
    public String product() {
        return this.base.product()+",ssd256";
    }

    @Override
    public int price() {
        return this.base.price()+110000;
    }
}
```
> ###### 위 두 클래스 모두 ```Decorator```를 상속 받았으므로 ```Component``` 인터페이스이다.

#### 5. 실행
```java
    ...
    public static void main(String[] args) {
	    Product1 p1 = new Product1();
        i7 spec1 = new i7(p1);
        ssd256 spec2 = new ssd256(spec1);

        System.out.println("상품:" + spec2.product());
        System.out.println("가격:" + spec2.price());
        
    }
    ...
```
#### 실행 결과
```aidl
상품:원피스,i7,ssd256
가격:605000
```
## 관련 패턴
> ###### 복합체 패턴, 어댑터 패턴, 전략 패턴