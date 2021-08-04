package com.java.advanced.features.io.bixiangdong.p20_sequenceinputstream;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * 需求：
 * 把video1.txt，video2.txt，video3.txt 合并成一个 video.txt
 *
 * @author wangzhichao
 * @since 2021/8/3
 */
public class SequenceInputStreamDemo {
    public static void main(String[] args) throws IOException {
        List<FileInputStream> list = new ArrayList<>();
        list.add(new FileInputStream("video1.txt"));
        list.add(new FileInputStream("video2.txt"));
        list.add(new FileInputStream("video3.txt"));
        Enumeration<FileInputStream> enumeration = Collections.enumeration(list);
        SequenceInputStream sequenceInputStream = new SequenceInputStream(enumeration);
        BufferedReader br = new BufferedReader(new InputStreamReader(sequenceInputStream));
        BufferedWriter bw = new BufferedWriter(new FileWriter("video.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
            bw.flush();
        }
        bw.close();
        br.close();
    }
}
