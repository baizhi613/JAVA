package org.example;

/**
 * @author lys612411
 */
public class Son2<T> extends Father<T> {
    public Son2(T data){
        super(data);
    }

    @Override
    public String toString() {
        return "Son2 [childData="+fatherData+"]";
    }
}
