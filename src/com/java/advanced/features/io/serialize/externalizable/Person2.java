package com.java.advanced.features.io.serialize.externalizable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
// 参考文章：https://www.jianshu.com/p/3a3df818c57c
public class Person2 implements Externalizable {
    private String name;
    private int age;
    // 增加了无参数的构造方法
    public Person2() {}

    public Person2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(this.name);
        out.writeInt(this.age);
        // 错误写法：输出结果不正确
//        out.writeInt(this.age);
//        out.writeUTF(this.name);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = in.readUTF();
        this.age = in.readInt();
        // 错误写法：报错：Exception in thread "main" java.io.EOFException
//        this.age = in.readInt();
//        this.name = in.readUTF();
    }
}
