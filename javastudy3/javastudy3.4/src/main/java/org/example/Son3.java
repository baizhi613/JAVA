package org.example;

/**
 * @author lys612411
 */
public class Son3<T> extends Father<String> {
    T otherData;

    public Son3(String data, T otherData) {
        super(data);
        this.otherData = otherData;
    }

    @Override
    public String toString(){
        return "Son3[childData="+fatherData+",其他数据"+otherData+"]";
    }

}
