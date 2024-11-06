package org.example;

/**
 * @author lys612411
 */
public class Father<T> {
    T fatherData;

    public  Father(T data){
        this.fatherData=data;
    }

    @Override
    public String toString() {
        return "Father{" +
                "fatherData=" + fatherData +
                '}';
    }
}