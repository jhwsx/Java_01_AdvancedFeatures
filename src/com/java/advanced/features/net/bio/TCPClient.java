package com.java.advanced.features.net.bio;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author wangzhichao
 * @since 2022/4/13
 */
public class TCPClient {
    public static void main(String[] args) throws IOException {
        // 2，客户端发起和服务器的连接
        // 2.1，创建客户端本地套接字（隐式捆绑到本地 port）
        Socket socket = new Socket();
        // 2.2，指定服务器进程的 IP 地址和端口号
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 10001);
        // 2.3，与服务器进程建立连接
        socket.connect(inetSocketAddress);
        // 这两个流顺序不能写反了，要先写后读
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())) {
            String text = "hello,tcp!";
            System.out.println("客户端发送的文本：" + text);
            objectOutputStream.writeUTF(text);
            objectOutputStream.flush();

            String s = objectInputStream.readUTF();
            System.out.println("客户端收到的文本：" + s);
        } finally {
            socket.close();
        }
    }
}
