package org.example;

/**
 * @author lys612411
 */
public class Son4<E,T> extends Father<T> {
    E otherData;

    public Son4(E e,T t)
    {
        super(t);
        this.otherData = e;
    }

    @Override
    public String toString()
    {
        return "Son4[childData="+fatherData+",其他数据"+otherData+"]";
    }
}
