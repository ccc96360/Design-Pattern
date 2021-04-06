# 반복자 패턴 (Iterator Pattern)
#### 내부 구조를 노출하지 않고 집합체를 통해 원소에 순차적으로 접근할 수 있는 방법을 제공한다.

---
## 집합체 
> ###### 반복자는 객체의 효율적인 집합 관리를 위해 별도의 집합체(Aggregate)를 갖고있다.

#### 1. 집합 객체 
> ###### 집합체는 단순 배열과 달리 복수의 객체를 가진 복합객체이다.
> ###### 집합체는 다른말로 컬렉션(collection)이라고 한다.

#### 2. 인터페이스
> ###### 집합체를 위한 인터페이스를 설계한다.
> ###### 다음 예제는 책들의 집합체인 책 선반(BookShelf)을 위한 집합체 인터페이스이다.
```java
public interface Aggregate {
    public Iterator iterator();
}
```
#### 3. 컬렉션
> ###### 책(Book)객체의 집합체인(```Aggregate```) BookShelf(```ConcreteAggregate```) 를 만든다
> ###### 자바에서 기본으로 제공해 주는 ```java.util.Iterator``` 인터페이스를 활용한다.
```java
import java.util.Iterator;

public class BookShelf implements Aggregate {
    private Book[] books;
    private int last = 0;

    public BookShelf(int maxSize) {
        this.books = new Book[maxSize];
    }

    public Book getBookAt(int index){
        return books[index];
    }

    public void addBook(Book book){
        books[last++] = book;
    }

    public int length(){
        return last;
    }

    @Override
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
}
```
> ###### ```iterator()```메서드를 통해 집합체 ```BookShelf```를 순회 할 수 있는 ```Iterator``` 객체 ```BookShelfIterator```를 반환한다.

---
## 반복자

#### 1. 인터페이스
```java
public interface mIterator {
    public boolean hasNext();
    public Object next();
}
```
> ###### 위와 같이 인터페이스를 만들어 줘도 돼지만 ```java.util.Iterator``` 인터페이스가 이미 있으므로 이를 활용한다.
> > ###### [OpenJDK Iterator소스 코드](http://hg.openjdk.java.net/lambda/lambda/jdk/file/tip/src/share/classes/java/util/Iterator.java)
#### 2. 반복 객체
> ###### ```java.util.Iterator```를 적용해 구현한다.
```java
import java.util.Iterator;

public class BookShelfIterator implements Iterator {
    private BookShelf bookShelf;
    private int index = 0;

    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
    }

    @Override
    public boolean hasNext() {
        if(index < bookShelf.length()){
            return true;
        }
        return false;
    }

    @Override
    public Object next() {
        Book book = bookShelf.getBookAt(index);
        index++;
        return book;
    }
}
```
> ###### hasNext()는 다음 객체의 존재 유무를 확인하고 next()는 객체를 반환한다.

#### 3. 결합 관계
> ###### 반복자의 객체 생성은 집합 객체(```BookShelf``)에 의해 이루어진다. 따라서 의존 관계가 발생된다.
> ###### 반복자 객체(BookShelfIterator)는 집합 객체를 의존성 주입받는다.
> ###### 반복자 객체는 컬렉션이 가진 객체를 순환하는 제어부이다. (외부로 제어부가 분리된것이다.)
> ###### 집합체와 반복자는 양방향성의 강력한 결합 관계를 갖는다.

---
## 실행 결과

#### 1. client
```java
public class Book {
    private String name;
    private int page;

    public Book(String name, int page) {
        this.name = name;
        this.page = page;
    }

    public String getName() {
        return name;
    }

    public int getPage() {
        return page;
    }
}

import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
	    BookShelf bookShelf = new BookShelf(10);
	    bookShelf.addBook(new Book("Harry Potter1", 500));
	    bookShelf.addBook(new Book("Harry Potter2", 600));
	    bookShelf.addBook(new Book("Harry Potter3", 700));
	    bookShelf.addBook(new Book("Harry Potter4", 800));
	    bookShelf.addBook(new Book("Harry Potter5", 900));
	    bookShelf.addBook(new Book("Harry Potter6", 1000));
	    bookShelf.addBook(new Book("Harry Potter7", 1100));

//        mIterator it = bookShelf.iterator();
        Iterator it = bookShelf.iterator();
        while(it.hasNext()){
            Book book = (Book) it.next();
            System.out.println(book.getName()+" "+book.getPage());
        }
    }
}
```
> ###### Client 입장에서 BookShelfIterator가 있는지 모른다.
> ###### 또한, Book객체의 배열이 배열로 구현되었는지,ArrayList인지 HashMap인지 조차도 모른다.
> ###### 즉, 내부 구현을 노출 시키지 않고 순회할 수 있다.
> ###### 만약 반복자 객체를 수정한다면 다양하게 순회가능하다.
> ###### 다른 Aggregate더라도 Iterator 인터페이스는 동일하기 때문에 동일하게 사용하면 된다.

#### 실행 결과
```aidl
Harry Potter1 500
Harry Potter2 600
Harry Potter3 700
Harry Potter4 800
Harry Potter5 900
Harry Potter6 1000
Harry Potter7 1100
```
#### 2. 관련 패턴
> ###### 방문자 패턴, 팩토리 메서드, 복합체 패턴