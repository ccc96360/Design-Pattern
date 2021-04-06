package pattern.iterator;

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
