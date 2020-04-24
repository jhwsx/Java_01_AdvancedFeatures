package com.java.advanced.features.generics.tutorial.t05_generics_inheritance_subtypes;

import java.util.Collection;
import java.util.List;

/**
 * @author wangzhichao
 * @since 2020/4/23
 */
interface PayloadList<E, P> extends List<E> {
    void setPayload(int index, P val);
}
public class GenericClassesAndSubtyping {
    public static void main(String[] args) {
        // 只要类型实参 String 是一样的，类型之间的子类化关系就在。
        PayloadList<String, String> stringStringPayloadList = null;
        PayloadList<String, Integer> stringIntegerPayloadList = null;
        PayloadList<String, Exception> stringExceptionPayloadList = null;
        List<String> stringList;
        stringList = stringStringPayloadList;
        stringList = stringIntegerPayloadList;
        stringList = stringExceptionPayloadList;
        Collection<String> stringCollection;
        stringCollection = stringList;
    }
}
