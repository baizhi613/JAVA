package org.example;

/**
 * @author lys612411
 */
public class Son1 extends Father<String>{
    public Son1(String data) {
        super(data);
    }

    @Override
    public String toString(){
        return "Son1 [childData="+fatherData+"]";
    }
}
