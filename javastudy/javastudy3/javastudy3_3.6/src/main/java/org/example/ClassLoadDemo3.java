package org.example;

/**
 * @author lys612411
 */
public class ClassLoadDemo3 {
    public static void main(String[] args) {
        try{
            Class<?> clazzTeacher=ClassLoader.getSystemClassLoader().loadClass("org.example.Teacher");
            System.out.println(clazzTeacher.getClassLoader());

            Teacher teacher=(Teacher) clazzTeacher.newInstance();
            teacher.setName("张三");
            teacher.setAge(45);
            teacher.setProfessional("教授");
            System.out.println(teacher.toString());
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (InstantiationException e){
            e.printStackTrace();
        }catch (IllegalAccessError e){
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
