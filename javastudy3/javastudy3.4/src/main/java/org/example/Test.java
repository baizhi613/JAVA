package org.example;

/**
 * @author lys612411
 */
public class Test {
    public static void main(String[] args) {
        Son1 son1 = new Son1("son1");
        System.out.println(son1.toString());

        Son2<String> son2 = new Son2<>("son2");
        System.out.println(son2.toString());

        Son3<String> son3 = new Son3<>("son3", "otherData");
        System.out.println(son3.toString());

        Son4<String,String> son4 = new Son4<>("son4","otherData");
        System.out.println(son4.toString());
    }


}
