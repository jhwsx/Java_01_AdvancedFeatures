package com.java.advanced.features.net.bio;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * java之UDP协议下socket编程（单播广播多播）https://juejin.cn/post/6844903918535720973
 *
 * @author wangzhichao
 * @since 2022/7/6
 */
public class UDPServer {
    public static void main(String[] args) {
            // 1，创建服务端 socket，指定端口号
            try (DatagramSocket server = new DatagramSocket(null)) {
                server.bind(new InetSocketAddress(12000));
                while (true) {
                    byte[] buffer = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(buffer, 0, buffer.length);
                    // 2，服务端等待来自客户端的分组
                    server.receive(receivePacket);
                    byte[] data = receivePacket.getData();
                    int length = receivePacket.getLength();
                    String text = new String(data, 0, length);
                    System.out.println("服务端接收到的文本：" + text);

                    String result = text.toUpperCase(Locale.US);
                    byte[] bytes = result.getBytes(StandardCharsets.UTF_8);
                    System.out.println("服务端返回的文本：" + result);
                    // 5，向客户端发送数据
                    int port = receivePacket.getPort();
                    InetAddress address = receivePacket.getAddress();
                    DatagramPacket sendPacket = new DatagramPacket(bytes, 0, bytes.length, address, port);
                    server.send(sendPacket);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
}
