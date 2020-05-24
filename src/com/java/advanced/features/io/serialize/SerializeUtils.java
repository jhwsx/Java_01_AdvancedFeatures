package com.java.advanced.features.io.serialize;

import java.io.*;

public class SerializeUtils {
    public static <T> void writeObject(String filePath, T obj) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
        oos.close();
    }

    public static <T> T readObject(String filePath) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        T result = (T) ois.readObject();
        ois.close();
        return result;
    }
}
