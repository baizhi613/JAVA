package org.example;

/**
 * @author lys612411
 */
public class TestPoint {
    public static void main(String[] args) {
        Point<Integer> point1=new Point<>(10,10);
        System.out.println(point1);

        Point<Double> point2=new Point<>(12.88,129.65);
        System.out.println(point2);

        Point<String> point3=new Point<>("东经180度","北纬210度");
        System.out.println(point3);

        Point<String> point4=new Point<>("东经180度","北纬210度");

        System.out.println(point1.equalPoint(10,10));
        System.out.println(point1.equalPoint(10,11));
        System.out.println(point2.equalPoint(199.88,129.65));
        System.out.println(point3.equalPoint("东经80度","北纬20度"));
    }
}
