package org.example;

import java.io.*;

/**
 * @author lys612411
 */
public class ObjectTransientTest {
    public void seriallizeObjectToFile(String fileName,Person p){
        try{
            File file=new File(fileName);
            FileOutputStream fos=new FileOutputStream(file);
            ObjectOutputStream oos=new ObjectOutputStream(fos);

            oos.writeObject(p);

            oos.close();
            fos.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Person deserializeObjectFromFile(String fileName){
        Person person=null;
        try{
            ObjectInputStream in =new ObjectInputStream(new FileInputStream(fileName));

            person=(Person) in.readObject();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return person;
    }

    public static void main(String[] args) {
        ObjectTransientTest oet=new ObjectTransientTest();
        String fileName="D:\\code\\JAVA\\javastudy4.2\\person.txt";
        Person person=new Person("李四","42011199999","女",24);
        System.out.println("序列化前Person数据:"+person.toString());
        oet.seriallizeObjectToFile(fileName,person);
        Person p=new Person();
        p=oet.deserializeObjectFromFile(fileName);
        System.out.println("反序列化后Person数据："+p.toString());
    }
}
