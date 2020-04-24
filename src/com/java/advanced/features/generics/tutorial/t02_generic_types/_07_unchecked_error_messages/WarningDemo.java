package com.java.advanced.features.generics.tutorial.t02_generic_types._07_unchecked_error_messages;

import com.java.advanced.features.generics.tutorial.t02_generic_types._02_a_generic_version_of_the_box_class.Box;

/**
 * @author wangzhichao
 * @since 2020/4/23
 */
public class WarningDemo {
    // @SuppressWarnings("unchecked") // 加上这句压制警告后，使用 -Xlint:unchecked, 不会出现警告了。
    public static void main(String[] args) {
        Box<Integer> bi;
        bi = createBox();
    }

    static Box createBox() {
        return new Box();
    }
}
/*
直接使用 IDE 编译，是不会出现的。

需要使用 javac 命令来编译。

G:\IdeaProjects\Java_01_AdvancedFeatures\src>javac com/java/advanced/features/generics/tutorial/t02_generic_types/_07_unchecked_error_messages/WarningDemo.java
注: com\java\advanced\features\generics\tutorial\t02_generic_types\_07_unchecked_error_messages\WarningDemo.java使用了未经检查或不安全的操作。
注: 有关详细信息, 请使用 -Xlint:unchecked 重新编译。
 */

/*
G:\IdeaProjects\Java_01_AdvancedFeatures\src>javac -Xlint:unchecked -encoding utf-8 com/java/advanced/features/generics/tutorial/t02_generic_types/_07_unchecked
_error_messages/WarningDemo.java
com\java\advanced\features\generics\tutorial\t02_generic_types\_07_unchecked_error_messages\WarningDemo.java:12: 警告: [unchecked] 未经检查的转换
        bi = createBox();
                      ^
  需要: Box<Integer>
  找到:    Box
1 个警告
 */
