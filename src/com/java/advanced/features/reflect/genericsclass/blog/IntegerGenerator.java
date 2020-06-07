package com.java.advanced.features.reflect.genericsclass.blog;

import java.io.Serializable;
import java.util.Random;

/**
 * @author wangzhichao
 * @since 2020/6/7
 */
public class IntegerGenerator implements Generator<Integer>, Serializable {
    Random random = new Random();
    @Override
    public Integer next() {
        return random.nextInt();
    }
}
