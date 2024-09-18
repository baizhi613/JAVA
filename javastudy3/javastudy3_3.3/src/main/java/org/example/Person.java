package org.example;

/**
 * @author lys612411
 */
public class Person {
    public String name;
    public String sex;
    protected String birthday;

    public Person() {
    }

    public Person(String name){
        this.name=name;
    }

    public Person(String name, String sex, String birthday) {
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void say(){
        System.out.println("hello");
    }

    public void song(String name,String sex){
        System.out.println("name:"+name+" sex:"+sex);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}