# 방문자 패턴 (Visitor Pattern)
#### 공통된 객체의 데이터 구조와 처리를 분라하는 패턴이다.

---
## 분리
* 방문자 패턴은  공통된 처리 로직을 분리한다.
#### 1. 공통된 로직
```java
public class Product {
    protected String name;
    protected int price;
    protected int num;
    /*
    * Getter & Setter
    * */
    ...
    
    public int getTax(int tax){
        return price*num *tax/100;
    }
}
public class Cart extends Product{
    public Cart(String name, int price, int num) {
        this.num = num;
        this.price = price;
        this.name = name;
    }

    @Override
    public int getTax(int tax) {
        return this.price*this.num*tax/100;
    }

    public void list(){
        System.out.println(name+", 수량:"+num+", 가격:"+num*price);
    }
}
```
> ###### 데이터를 처리하는 행위를 분리 했다.

#### 2. 처리 객체
```java
    ...
    public static void main(String[] args) {
	    Cart cart = new Cart("컵라면",900,2);
	    cart.list();
    }
    ...
```
**실행 결과**
```aidl
컵라면, 수량:2, 가격:1800
```
> ###### 객체의 데이터와 행위 로직을 분리한 것을 실행 했다.

---
## 원소 객체

#### 1. 원소(Element)
> ###### 원소 객체는  데이터를 보관하는 데이터 객체이다.
> ###### 객체는 일반적으로 "능동적인" 데이터 접근 방식을 사용하지만, 방문자 패턴의 원소 객체는 외부로 부터 자신의 데이터에 접근할 수 있는 "수동적" 방식을 사용한다.
> ###### 외부로 부터 자신의 객체에 접근하는 것을 허용하기 위해서는 관계를 설정해야 한다.
> ###### 원소 객체는 관계를 설정하기위해 ```accept()```메서드를 추가로 갖고있다.
> ###### 모든 원소 객체는 ```accept()```메서드를 동일하게 갖고 있어야 하므로 인터페이스를 적용한다.
```java
public interface Visitable {
    public void accept(방문자(Visitor));
}
```

#### 2. 구현
> ######  concrete element를 구현한다.
```java
public class Cart extends Product implements Visitable{
    public Cart(String name, int price, int num) {
        this.num = num;
        this.price = price;
        this.name = name;
    }

    @Override
    public int getTax(int tax) {
        return this.price*this.num*tax/100;
    }

    public void list(){
        System.out.println(name+", 수량:"+num+", 가격:"+num*price);
    }

    @Override
    public void accept(방문자(visitor)) {
        방문자.공통된로직(주문 등등)
    }
}
```
> ###### 방문자 패턴은 실제 처리 로직을 다른 객체(방문자 객체)로 분리하여 위임한다.
> ###### ```accept()```를 통해 위임되는 방문자 객체를 전달 받는다. 

#### 3. 캡슐화 실패
> ###### 방문자 패턴은 방문하는 외부 객체에 자신의 모든 데이터와 행위의 점근을 허용한다.
> ###### 객체의 모든 연산은 공개된 인터페이스로, 연산 작업이 외부에 노출된다.

---
## 방문자

#### 1. 방문자 호출
> ###### 원소 객체의 ```accept()```는 외부로부터 Visitor 객체를 전달 받고, 매개변수로 전달 받은 Visitor객체의 order()메서드(공통된 로직)를 실행한다.

#### 2. 인터페이스
```java
public interface Visitor {
    public void order(Visitable visitable);
}
```
> ###### '주문(order)'라는 공통된 로직을 처리하는 ```order()```메서드를 인터페이스를 만든다.
```java
public class Cart extends Product implements Visitable{
    ...
    ....
    @Override
    public void accept(Visitor visitor) {
        visitor.order(this);
    }
}
```
> ###### 위와 같이 방문자에게 원소 객체는 자기 자신을 전달해 행위를 방문자에게 위임한다.

#### 3. 구체적 방문자 (Concrete Visitor)
```java
public class Visitant implements Visitor{

    private int total;
    private int num;

    public Visitant() {
        total = 0;
        num = 0;
    }

    @Override
    public void order(Visitable visitable) {
        System.out.println("==상품 내역==");
        if(visitable instanceof Cart){
            System.out.print("상품명" + ((Cart) visitable).getName());
            System.out.print(" 수량:" + ((Cart) visitable).getNum());
            int cartTotal = ((Cart) visitable).getPrice() * ((Cart) visitable).getNum();
            System.out.println(" 가격:" + cartTotal);

            total += cartTotal;
            System.out.println("합계" + this.total + ", 주문 건수" + ++num);

        }
    }
}
```
#### 4. 패턴 실행
> ###### 구조와 행위를 분리한 방문자 패턴을 실행한다.
```java
    ...
    public static void main(String[] args) {
        Visitant visitant = new Visitant();
	    Cart cart = new Cart("컵라면",900,2);
	    Cart cart2 = new Cart("음료수",1900,5);
	    cart.accept(visitant);
	    cart2.accept(visitant);
    }
    ...
```
**실행 결과**
```aidl
==상품 내역==
상품명컵라면 수량:2 가격:1800
합계1800, 주문 건수1
==상품 내역==
상품명음료수 수량:5 가격:9500
합계11300, 주문 건수2
```

---
## 관련 패턴
> ###### 반복자 패턴, 복합체 패턴, 인터프리터 패턴