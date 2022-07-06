package com.java.advanced.features.net.bio;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

/**
 * @author wangzhichao
 * @since 2022/4/13
 */
public class TCPServer {
    public static void main(String[] args) throws IOException {
        // 1，服务器必须首先处于运行状态
        // 1.1，创建一个欢迎 socket
        try (ServerSocket welcomeSocket = new ServerSocket()) {
            // 1.2，绑定本地端口，表示服务器在哪个端口上监听
            welcomeSocket.bind(new InetSocketAddress(10001));
            System.out.println("Server start......");
            while (true) {
                // 1.3，在欢迎 socket 上阻塞式地等待接收用户的连接。
                // 3，当与客户端连接请求到来时，服务器接收来自客户端的请求，解除阻塞式等待，返回一个新的 socket，即连接 socket，用于与客户端通信。
                try (Socket connectionSocket = welcomeSocket.accept();
                     ObjectInputStream objectInputStream = new ObjectInputStream(connectionSocket.getInputStream());
                     ObjectOutputStream objectOutputStream = new ObjectOutputStream(connectionSocket.getOutputStream())) {
                        // 读取客户端的消息
                        String text = objectInputStream.readUTF();
                        System.out.println("服务器收到的文本: " + text);

                        // 服务端的输出
                        String result = text.toUpperCase(Locale.US);
                        System.out.println("服务端返回的文本：" + result);
                        objectOutputStream.writeUTF(result);
                        // 一定要刷新才可以
                        objectOutputStream.flush();
                }
            }
        } finally {
            System.out.println("Server end......");
        }
    }
}
