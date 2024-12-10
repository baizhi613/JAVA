package org.baizhi;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public Book findBookByTitle(String title) {
        return books.stream().filter(book -> book.getTitle().equals(title) && !book.isBorrowed()).findFirst().orElse(null);
    }
}
