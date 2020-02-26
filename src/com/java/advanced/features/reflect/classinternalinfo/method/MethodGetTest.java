package com.java.advanced.features.reflect.classinternalinfo.method;

import com.java.advanced.features.reflect.Apple;

import java.lang.reflect.Method;

public class MethodGetTest {
    public static void main(String[] args) {
        Class<Apple> appleClass = Apple.class;
        System.out.println("1, 演示 public Method[] getDeclaredMethods()");
        System.out.println("获取本类声明的所有方法对象，但不包括继承的方法");
        Method[] declaredMethods = appleClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }

        System.out.println("2, 演示 public Method getDeclaredMethod(String name, Class<?>... parameterTypes)");
        try {
            System.out.println("获取 void checkSize(Integer size)");
            Method checkSizeMethod = appleClass.getDeclaredMethod("checkSize", Integer.class);
            // 下面的写法会抛出异常：java.lang.NoSuchMethodException: com.java.advanced.features.reflect.Apple.checkSize(int)
            // Method checkSizeMethod = appleClass.getDeclaredMethod("checkSize", int.class);
            System.out.println(checkSizeMethod);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("获取 private void checkPrice(float price)");
             Method checkPriceField = appleClass.getDeclaredMethod("checkPrice", float.class);
            // 下面的写法会抛出异常：java.lang.NoSuchMethodException: com.java.advanced.features.reflect.Apple.checkPrice(java.lang.Float)
            // Method checkPriceField = appleClass.getDeclaredMethod("checkPrice", Float.class);
            System.out.println(checkPriceField);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        System.out.println("3, 演示 public Method[] getMethods()");
        Method[] methods = appleClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        System.out.println("4, 演示 public Method getMethod(String name, Class<?>... parameterTypes)");
        try {
            System.out.println("获取 private void checkPrice(float price)");
            Method checkPriceField = appleClass.getMethod("checkPrice", float.class);
        } catch (NoSuchMethodException e) {
            System.out.println(e);
        }

        try {
            System.out.println("获取  public String getColor()");
            Method colorMethod = appleClass.getMethod("getColor");
            System.out.println(colorMethod);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
/*
1, 演示 public Method[] getDeclaredMethods()
获取本类声明的所有方法对象，但不包括继承的方法
void com.java.advanced.features.reflect.Apple.checkSize(java.lang.Integer)
private void com.java.advanced.features.reflect.Apple.checkPrice(float)
public java.lang.String com.java.advanced.features.reflect.Apple.getColor()
public float com.java.advanced.features.reflect.Apple.getPrice()
public static int com.java.advanced.features.reflect.Apple.getCount()
public boolean com.java.advanced.features.reflect.Apple.initColorAndPrice(java.lang.String,float)
public int com.java.advanced.features.reflect.Apple.getSize()
public void com.java.advanced.features.reflect.Apple.setSize(int)
2, 演示 public Method getDeclaredMethod(String name, Class<?>... parameterTypes)
获取 void checkSize(Integer size)
void com.java.advanced.features.reflect.Apple.checkSize(java.lang.Integer)
获取 private void checkPrice(float price)
private void com.java.advanced.features.reflect.Apple.checkPrice(float)
3, 演示 public Method[] getMethods()
public java.lang.String com.java.advanced.features.reflect.Apple.getColor()
public float com.java.advanced.features.reflect.Apple.getPrice()
public static int com.java.advanced.features.reflect.Apple.getCount()
public boolean com.java.advanced.features.reflect.Apple.initColorAndPrice(java.lang.String,float)
public int com.java.advanced.features.reflect.Apple.getSize()
public void com.java.advanced.features.reflect.Apple.setSize(int)
public java.lang.String com.java.advanced.features.reflect.Fruit.getTaste()
public void com.java.advanced.features.reflect.Fruit.setTaste(java.lang.String)
public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
public final void java.lang.Object.wait() throws java.lang.InterruptedException
public boolean java.lang.Object.equals(java.lang.Object)
public java.lang.String java.lang.Object.toString()
public native int java.lang.Object.hashCode()
public final native java.lang.Class java.lang.Object.getClass()
public final native void java.lang.Object.notify()
public final native void java.lang.Object.notifyAll()
4, 演示 public Method getMethod(String name, Class<?>... parameterTypes)
获取 private void checkPrice(float price)
java.lang.NoSuchMethodException: com.java.advanced.features.reflect.Apple.checkPrice(float)
获取  public String getColor()
public java.lang.String com.java.advanced.features.reflect.Apple.getColor()
 */