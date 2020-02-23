package com.java.advanced.features.generics.genericerase;

// 这是画笔类
class Paint {
    void draw() {
        System.out.println("Paint.draw() called");
    }
}
// 这是画家类，它是一个泛型类
class Painter<T> {
    private T t;

    public Painter(T t) {
        this.t = t;
    }
    // 画家开始工作
    public void work() {
        // 这里打算调用 Paint 类的 draw() 方法
        // 但实际上，编译器已经提示：Cannot resolve method 'draw()'
        // t.draw();
    }

}

public class EraseBadEffect {
    public static void main(String[] args) {
        Paint paint = new Paint();
        Painter<Paint> painter = new Painter<>(paint);
        painter.work();
    }
}


