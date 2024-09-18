package org.example;

/**
 * @author lys612411
 */
public class Book implements Cloneable{
    private int number;
    private String name;
    private String author;
    private String publish;

    public Book(int number, String name, String author, String pub) {
        this.number = number;
        this.name = name;
        this.author = author;
        this.publish = pub;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPub() {
        return publish;
    }

    public void setPub(String pub) {
        this.publish = pub;
    }

    @Override
    public String toString() {
        return "Book{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", pub='" + publish + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
