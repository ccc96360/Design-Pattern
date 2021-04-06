package pattern.iterator;

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
