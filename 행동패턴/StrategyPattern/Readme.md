# 전략 패턴(Strategy Pattern)
#### 객체 내부에서 해결해야 하는 목적을 알고리즘 객체로 분리 적용하는 기법이다.
#### 실제 내부 동작을 외부 알고리즘 객체로 분리하여 유연하게 동작을 변경시킬 수 있다.

---
## 문제
#### 프로그램은 반복되는 문제를 해결하는데, 이때 해결 방법을 알고리즘이라고 한다.
#### 1. 전략 (What To)
> ###### 전략은 어떤 목표를 정하고 진행하는 큰 틀을 말한다.
> ###### 프로그램에서 전략은 실행하는 큰 틀을 의미한다.
> ###### 한 번 계획한 전략을 수정하는 일은 쉽지 않다.
> ###### 개발이 진행되고 있거나 완료된 프로그램에서 전략을 수정하면 많은 시간과 비용이 소모된다.
> ###### 또한, 잘못 설계된 전략은 프로그램에 치명적인 결과를 가져온다.

#### 2. 전술 (How To)
> ###### 전략을 짜면서 정한 목표를 달성하기 위한 상세 내용이다.
> ###### 즉 전술은 실제 알고리즘이 동작하는 상세 내용이다.

---
## 알고리즘

#### 1. 해결 방법의 변화
> ###### 한 번 개발된 프로그램은 산업 현장에서 생각보다 오랫동안 사용되고, 이러한 프로그램은 시간이 지나면서 변화가 필요하다.
> ###### 프로그램의 생명력은 변화에 어떻게 잘 대처하는가에 따라 결정된다.
> ###### 잦은 코드 수정으로 변경사항이 누적되면 수정이 어려워 진다.
> ###### 외부적으로 큰 환경 변화가 있을 때는 코드 수정이 어렵다.
> ###### 큰 변화가 요구되면 기존의 방법을 버리고 새로운 방법을 도입하는 것이 현명하며 이때 리팩터링이 필요하다.

#### 2. 다양성
> ###### 프로그램의 목표는 주어진 문제를 해결하는 것
> ###### 문제를 해결하는 방법은 매우 다양하며, 발생한 문제는 다르지만 유사한 방법으로 해결할 수도 있다.
> ###### 문제를 해결하는 방법을 다르게 적용하는 경우, 관련 코드도 같이 수정해야 하며 이때 코드를 찾고 오래된 코드를 다시 분석하는 등 많은 시간이 필요하다.

#### 3. 분리
> ###### 프로그램이 외부 변화에 보다 쉽게 적응하려면 변화가 예상되는 부분을 분리하는 것이 좋다.

#### 4. 알고리즘
> ###### 알고리즘 동작을 추상화해 처리한다.
> ###### 사용자는 알고리즘의 내부 실행 동작을 이해할 필요가 없으며, 인터페이스만 확인하고 절차에 따라 호출해 사용하면 된다.

---
## 분리

#### 1. 템플릿 메서드
> ###### 템플릿 메서드 패턴은 공통된 기능을 분리하여 템플릿하고, 추상화를 통해 상황별로 다르게 처리할 수 있도록 실제 동작을 분리한다.
> ###### 즉 하나의 알고리즘을 다양한 방식으로 재정의할 수 있도록 행동을 분리하는 패턴이다.
> ###### 하지만 템플릿도 코드 일부분을 수정해야 하는 경우가 발생한다. 이러한 수정은 OCP를 위반한다.

#### 2. 캡슐화
> ###### 분리된 알고리즘을 별도의 클래스로 캡슐화 한다.
> ###### 패턴에서는 알고리즘의 캡슐화를 ```전략```이라고 하며 캡슐화된 알고리즘은 상황에 맞게 교체하며 사용한다.
> ###### ```템플릿 메서드```처럼 알고리즘 일부만 변경하는 것이 아니라행위의 전체를  변경할 떄 사용한다.

#### 3. 구조 유지
> ###### 내부적인 구조를 유지 하기위해서 인터페이스를 적용하여 설계한다.

---
## 인터페이스

#### 1. 호환성
> ###### 전략 패턴은 변화되는 부분을 찾아 알고맂므으로 분리한다.
> ###### 구조의 일부분을 알고리즘을 분리할 때는 구조적 호환성을 유지하는 것이 중요하다.
> ###### 다음은 게임의 캐릭터가 사용하는 ```무기```인터페이스의 예시이다.
```java
public interface Weapon {
    public void attack();
}
```

#### 2. 구체화
> ###### ```Weapon```인터페이스를 적용해 무기 클래스를 생성한다.
```java
public class Knife implements Weapon{
    @Override
    public void attack() {
        System.out.println("칼로 공격!");
    }
}
public class Gun implements Weapon{
    @Override
    public void attack() {
        System.out.println("총 발포!");
    }
}
```
> ###### 인터페이스를 활용해 캐릭터의 무기 종류를 각각 다른 알고리즘 클래스로 분리했다.

---
## 전략

#### 1. 개발 부채
> ###### 문제를 해결하기 위해 다양한 방법을 모두 구현할 수는 없다.
> ###### 실제 개발 현장에서는 당장 성능이 떨어지더라도 동작하는 코드를 만드는 것을 우선시한다.
> ###### 알고리즘을 실제 코드와 결합해서 설계하면 향후 유지 보수가 힘들어진다.
> ###### 이때 알고리즘을 추상화해 전략 패턴으로 전환한다.

#### 2. 추상화
> ###### ```Strategy```는 추상 클래스이고, 인터페이스는 전략을 적용하기위한 추상 메서드이다.
```java
public abstract class Strategy {
    protected Weapon delegate;
    
    public void setWeapon(Weapon weapon){
        System.out.println("무기 교환");
        this.delegate = weapon;
    }
            
    abstract public void attack();
}
```
#### 3. 구체화

> ###### 실제적인 하위 클래스를 구현한다. 전략 패턴은 특정 알고리즘에 종속되어 동작하지 않으며, 언제든지 알고리즘을 변경해 적용할 수 있다.
```java
public class Character extends Strategy{
    @Override
    public void attack() {
        if(this.delegate == null){
            System.out.println("맨손 공격!");
        }else{
            this.delegate.attack();
        }
    }
}
```
> ###### 개발자는 어떤 무기가 개발 되어 있는지 상관없이 캐릭터 객체를 생성할 수 있다.
> ###### 필요한 무기는 ```delegate```프로퍼티를 통해 위임 처리한다.

#### 4. 실시간 교체 
> ###### 런타임에서 알고리즘을 손쉽게 교체할 수 있다.

---
## 전략 실행
#### 1. 실행
```java
public class Main {

    public static void main(String[] args) {
	    Character character = new Character();
	    character.attack();

	    character.setWeapon(new Knife());
	    character.attack();

	    character.setWeapon(new Gun());
	    character.attack();
    }
}
```
**실행 결과**
```aidl
맨손 공격!
무기 교환
칼로 공격!
무기 교환
총 발포!
```

#### 2. 행동 통합과 객체
> ###### 객체는 상태와 행동을 갖고있다.
> ###### 행동을 알고리즘을 분리하지만 행동을 처리하면서 상태를 사용할 수 없는것은 아니다.
> ###### 복수의 행동을 조건문을 ㅗ처리하는 것보다 단일 알고맂므으로 객체를 분리하는것이 좋다

#### 3. 동적 처리와 매개변수
> ###### 전략패턴은 상황별로 알고리즘을 사용할 수 있지만 모든 상황별로 알고리줌을 미리 구현할 필요는 없다.
> ###### 다른 패턴은 객체가 확장되거나 변형되면 형태를 바꾸지만 전략 패턴은 객체의 형태를 변경하는것이 아니라 다른종류의 행동 객체로 교체한다.

---
## 관련 패턴
#### 플라이 웨이트, 추상 팩토리, 상태 패턴 