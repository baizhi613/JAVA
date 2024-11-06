package org.example;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * @author lys612411
 */
public class ComparatorTest {
    public static void main(String[] args) {
        Teacher t1=new Teacher("张军",45,"男");
        Teacher t2=new Teacher("李爱玲",56,"女");
        Teacher t3=new Teacher("王建国",24,"男");
        Teacher t4=new Teacher("何方",38,"女");

        List<Teacher> teacherList=new ArrayList<Teacher>();
        teacherList.add(t1);
        teacherList.add(t2);
        teacherList.add(t3);
        teacherList.add(t4);

        System.out.println("---原始数据。按添加的顺序排列---");
        for(Teacher t:teacherList){
            System.out.println(t.toString());
        }

        teacherList.sort(new Comparator<Teacher>() {
            @Override
            public int compare(Teacher o1, Teacher o2) {
                return o1.getAge()-o2.getAge();
            }
        });
        System.out.println("---按年龄升序排序以后遍历的结果---");
        for(Teacher t:teacherList){
            System.out.println(t.toString());
        }

        teacherList.sort(new Comparator<Teacher>() {
            @Override
            public int compare(Teacher o1, Teacher o2) {
                if(o1==null||o2==null){
                    return -1;
                }
                int num1=o1.getSex().compareTo(o2.getSex());
                int num2=num1==0?(o1.getAge()-o2.getAge()):num1;
                return num2;
            }
        });
        System.out.println("---按性别升序排序以后遍历的结果---");
        for(Teacher t:teacherList){
            System.out.println(t.toString());
        }

        teacherList.sort(new Comparator<Teacher>() {
            @Override
            public int compare(Teacher o1, Teacher o2) {
                Collator instance=Collator.getInstance(Locale.CHINA);
                return instance.compare(o1.getName(),o2.getName());
            }
        });
        System.out.println("---按姓名升序排序以后遍历的结果---");
        for(Teacher t:teacherList){
            System.out.println(t.toString());
        }
    }
}
