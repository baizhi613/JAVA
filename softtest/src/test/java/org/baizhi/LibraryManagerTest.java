package org.baizhi;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LibraryManagerTest {
    private Library library;
    private LibraryManager libraryManager;
    private User user;

    @Before
    public void setUp() {
        library = new Library();
        libraryManager = new LibraryManager(library);
        user = new User("张三");

        library.addBook(new Book("Java从入门到精通", "李四"));
        library.addBook(new Book("数据结构", "王五"));
    }

    @Test
    public void testBorrowBookSuccess() {
        boolean result = libraryManager.borrowBook(user, "Java从入门到精通");
        assertTrue(result);
        assertEquals(1, user.getBorrowedBooks().size());
        assertEquals("Java从入门到精通", user.getBorrowedBooks().get(0).getTitle());
    }

    @Test
    public void testBorrowBookNotAvailable() {
        libraryManager.borrowBook(user, "Java从入门到精通");
        boolean result = libraryManager.borrowBook(user, "Java从入门到精通");
        assertFalse(result);
    }

    @Test
    public void testReturnBookSuccess() {
        libraryManager.borrowBook(user, "Java从入门到精通");
        boolean result = libraryManager.returnBook(user, "Java从入门到精通");
        assertTrue(result);
        assertEquals(0, user.getBorrowedBooks().size());
    }

    @Test
    public void testReturnBookNotBorrowed() {
        boolean result = libraryManager.returnBook(user, "不存在的书");
        assertFalse(result);
    }
}
