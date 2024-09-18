package org.example;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author lys612411
 */
public class Main {
    public static void main(String[] args) {
        Set<Book> books=new HashSet<>();
        books.add(new Book(1,"java","lys","javac"));
        books.add(new Book(2,"C","lys2","cc"));
        books.add(new Book(3,"C++","lys3","cpp"));
        books.add(new Book(4,"PHP","lys4","PHP.c"));
        books.add(new Book(5,"python","lys5","python.c"));
        books.add(new Book(6,"javascript","lys6","javascript.c"));
        books.add(new Book(7,"html","lys7","html.c"));
        books.add(new Book(8,"css","lys8","css.c"));
        books.add(new Book(9,"go","lys9","go.c"));
        books.add(new Book(10,"rust","lys0","rust.c"));

        for (Book book:books){
            System.out.println(book);
        }
        System.out.println();

        Iterator<Book> it=books.iterator();
        while(it.hasNext()){
            Book book=it.next();
            System.out.println(book);
        }
        System.out.println();

        books.add(new Book(11,"666","666","666"));
        for (Book book:books){
            System.out.println(book);
        }
        System.out.println();

        //books.clear();

        if(books.isEmpty()){
            System.out.println("不为空");
        }

        System.out.println(books.size());

        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getNumber()==1) {
            iterator.remove(); // 使用迭代器的remove方法移除元素
            }
        }

        for (Book book:books){
            System.out.println(book);
        }
        System.out.println();
    }
}

