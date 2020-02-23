package com.java.advanced.features.generics.genericerase.fix;

// 这是画笔类
class Paint {
    void draw() {
        System.out.println("Paint.draw() called");
    }
}

// 这是画家类，它是一个泛型类
class Painter<T extends Paint> {
    private T t;

    public Painter(T t) {
        this.t = t;
    }
    // 画家开始工作
    public void work() {
        // 这里打算调用 Paint 类的 draw() 方法
         t.draw();
    }

}

public class EraseBadEffectFixed {
    public static void main(String[] args) {
        Paint paint = new Paint();
        Painter<Paint> painter = new Painter<>(paint);
        painter.work();
    }
}

/*
打印结果：
Paint.draw() called
 */
