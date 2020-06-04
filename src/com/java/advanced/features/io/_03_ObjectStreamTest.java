package com.java.advanced.features.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
// 注意：因为文件头的存在。
// 序列化时使用一个 Person 类，反序列化时使用另一个不在同一个包下的 Person 类，是不行的；
// 反序列化一个空文件也是不行的。
public class _03_ObjectStreamTest {
    public static void main(String[] args) {
//        testObjectOutputStream();
//        testObjectInputStream();

//        testObjectOutputStreamByArray();
//        testObjectInputStreamByArray();

        testObjectOutputStreamByList();
        testObjectInputStreamByList();
    }

    private static void testObjectOutputStream() {
        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(
                            new FileOutputStream(
                                    new File("testtxt/_03_ObjectStreamTest.txt")
                            )
                    );
            for (int i = 0; i < 10; i++) {
                oos.writeObject(new Person("王志超" + i, 20 + i));
            }
            oos.writeObject(null);
            oos.close();
            System.out.println("testObjectOutputStream end");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testObjectInputStream() {
        try {
            ObjectInputStream ois =
                    new ObjectInputStream(
                            new BufferedInputStream(
                                    new FileInputStream(
                                            new File("testtxt/_03_ObjectStreamTest.txt")
                                    )
                            )
                    );
            // TODO 这种判断结束的为什么运行起来抛异常
//            while (ois.available() != -1) {
//                Object object = ois.readObject();
//                Person person = (Person) object;
//                System.out.println(person);
//            }
            int i = 11;
            while (i > 0) {
                Object object = ois.readObject();
                Person person = (Person) object;
                System.out.println(person);
                i--;
            }
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void testObjectOutputStreamByArray() {
        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(
                            new FileOutputStream(
                                    new File("testtxt/_03_ObjectStreamTestArray.txt")
                            )
                    );
            Person[] personArray = new Person[10];
            for (int i = 0; i < 10; i++) {
                personArray[i] = new Person("王" + i, i);
            }
            oos.writeObject(personArray);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testObjectInputStreamByArray() {
        try {
            ObjectInputStream ois =
                    new ObjectInputStream(
                            new BufferedInputStream(
                                    new FileInputStream(
                                            new File("testtxt/_03_ObjectStreamTestArray.txt")
                                    )
                            )
                    );
            Object object = ois.readObject();
            Person[] personArray = (Person[]) object;
            for (Person person : personArray) {
                System.out.println(person);
            }
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void testObjectOutputStreamByList() {
        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(
                            new FileOutputStream(
                                    new File("testtxt/_03_ObjectStreamTestList.txt")
                            )
                    );
            List<Person> personList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                personList.add(new Person("超" + i, i));
            }
            oos.writeObject(personList);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testObjectInputStreamByList() {
        try {
            ObjectInputStream ois =
                    new ObjectInputStream(
                            new BufferedInputStream(
                                    new FileInputStream(
                                            new File("testtxt/_03_ObjectStreamTestList.txt")
                                    )
                            )
                    );
            Object object = ois.readObject();
            List<Person> personList = (List<Person>) object;
            for (Person person : personList) {
                System.out.println(person);
            }
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
