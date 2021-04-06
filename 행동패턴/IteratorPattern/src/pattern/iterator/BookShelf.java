package pattern.iterator;

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
