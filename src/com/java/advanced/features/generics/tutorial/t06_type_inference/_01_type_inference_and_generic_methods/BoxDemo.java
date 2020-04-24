package com.java.advanced.features.generics.tutorial.t06_type_inference._01_type_inference_and_generic_methods;

import com.java.advanced.features.generics.tutorial.t02_generic_types._02_a_generic_version_of_the_box_class.Box;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhichao
 * @since 2020/4/23
 */
public class BoxDemo {
    public static <U> void addBox(U u, List<Box<U>> boxes) {
        Box<U> box = new Box<>();
        box.set(u);
        boxes.add(box);
    }

    public static <U> void outputBoxes(List<Box<U>> boxes) {
        int counter = 0;
        for (Box<U> box : boxes) {
            U boxContents = box.get();
            System.out.println(
                    "Box #" + counter + " contains [" +
                            boxContents.toString() + "]"
            );
            counter++;
        }
    }

    public static void main(String[] args) {
        ArrayList<Box<Integer>> listOfIntegerBoxes =
                new ArrayList<>();
        BoxDemo.<Integer>addBox(Integer.valueOf(10), listOfIntegerBoxes);
        BoxDemo.addBox(Integer.valueOf(20), listOfIntegerBoxes);
        BoxDemo.addBox(Integer.valueOf(30), listOfIntegerBoxes);
        BoxDemo.outputBoxes(listOfIntegerBoxes);
    }
}