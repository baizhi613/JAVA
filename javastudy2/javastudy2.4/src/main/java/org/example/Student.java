package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author lys612411
 */
public class Student {
    String name;
    String sex;
    String classes;

    public Student(String name, String sex, String classes) {
        this.name = name;
        this.sex = sex;
        this.classes = classes;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", classes='" + classes + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Student stu1 = new Student("张三","男","软件工程班");
        Student stu2 = new Student("张四","男","软件工程班");
        Student stu3 = new Student("张五","女","网络工程班");
        Student stu4 = new Student("张六","男","云计算工程班");
        Student stu5 = new Student("张七","女","云计算工程班");

        List<Student> studentList =new ArrayList<>();
        studentList.add(stu1);
        studentList.add(stu2);
        studentList.add(stu3);
        studentList.add(stu4);
        studentList.add(stu5);

        Iterator<Student> iterator=studentList.iterator();
        while(iterator.hasNext()){
            Student s=iterator.next();
            System.out.println(s.toString());
        }

        Iterator<Student> iterator2=studentList.iterator();
        while(iterator2.hasNext()){
            Student s=iterator2.next();
            System.out.println(s);
            if(s.getName().equalsIgnoreCase("张六")){
                System.out.println("删除当前元素；"+s);
                iterator2.remove();
            }
        }
        System.out.println("删除之后："+studentList);

        ListIterator<Student> iterator3=studentList.listIterator();
        System.out.println("\n由前向后遍历:");
        while (iterator3.hasNext()){
            System.out.println(iterator3.next()+",");
        }
        System.out.println("\n由后向前遍历:");
        while (iterator3.hasPrevious()){
            System.out.println(iterator3.previous()+",");
        }
    }
}