package com.java.advanced.features.reflect.genericsclass;

import java.io.Serializable;

/**
 * @author wangzhichao
 * @since 2020/5/25
 */
public class PointerGenericityImpl<T extends Number & Serializable>
        implements PointInterface<T, Integer> {
}
