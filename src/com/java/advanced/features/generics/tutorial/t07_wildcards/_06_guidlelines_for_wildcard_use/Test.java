package com.java.advanced.features.generics.tutorial.t07_wildcards._06_guidlelines_for_wildcard_use;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author wangzhichao
 * @since 2020/4/25
 */
class NaturalNumber {
    private int i;

    public NaturalNumber(int i) {
        this.i = i;
    }
}

class EvenNumber extends NaturalNumber {

    public EvenNumber(int i) {
        super(i);
    }
}
public class Test {
    public static void main(String[] args) {
        List<EvenNumber> le = new ArrayList<>();
        List<? extends NaturalNumber> ln = le;
//        ln.add(new EvenNumber(35)); // 编译错误
        ln.add(null); // ok
        ln.clear(); // ok
        Iterator<? extends NaturalNumber> iterator = ln.iterator();
        while (iterator.hasNext()) {
            NaturalNumber naturalNumber = iterator.next();
            iterator.remove();
        }
    }
}
