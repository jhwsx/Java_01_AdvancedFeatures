package com.java.advanced.features.generics.tutorial.t04_bounded_type_parameters._00_bound;

/**
 * 限定的类型参数允许调用在范围中定义的方法。
 *
 * @author wangzhichao
 * @since 2020/4/23
 */
public class NaturalNumber<T extends Integer> {
    private T n;

    public NaturalNumber(T n) {
        this.n = n;
    }

    public boolean isEven() {
        // 限定类型参数允许调用在范围中定义的方法。
        return n.intValue() % 2 == 0;
    }
}
