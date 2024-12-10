package org.baizhi;

import java.util.ArrayList;
import java.util.List;

public class LibraryManager {
    private Library library;
    private List<BorrowRecord> borrowRecords;

    public LibraryManager(Library library) {
        this.library = library;
        this.borrowRecords = new ArrayList<>();
    }

    public boolean borrowBook(User user, String bookTitle) {
        Book book = library.findBookByTitle(bookTitle);
        if (book != null) {
            book.borrow();
            user.borrowBook(book);
            borrowRecords.add(new BorrowRecord(user, book));
            return true;
        }
        return false;
    }

    public boolean returnBook(User user, String bookTitle) {
        Book book = user.getBorrowedBooks().stream()
                .filter(b -> b.getTitle().equals(bookTitle))
                .findFirst()
                .orElse(null);
        if (book != null) {
            book.returnBook();
            user.returnBook(book);
            borrowRecords.removeIf(record -> record.getBook().equals(book) && record.getUser().equals(user));
            return true;
        }
        return false;
    }

    public List<BorrowRecord> getBorrowRecords() {
        return borrowRecords;
    }
}
