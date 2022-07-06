package com.java.advanced.features.net.bio;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * @author wangzhichao
 * @since 2022/7/6
 */
public class UDPClient {
    public static void main(String[] args) {
        try {
            // 3，创建客户端 socket
            try (DatagramSocket client = new DatagramSocket()) {
                // 4，向指定的服务端发送文本
                String text = "hello, udp!";
                System.out.println("客户端发送的文本：" + text);
                byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
                DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length,
                        new InetSocketAddress("localhost", 12000));
                client.send(packet);
                // 6，接收来自指定服务端的文本
                byte[] buffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                client.receive(receivePacket);
                String result = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("客户端接收到的文本：" + result);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
