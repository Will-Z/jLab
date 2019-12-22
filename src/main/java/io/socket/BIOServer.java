package io.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 传统socket实现  BIO
 * @author will
 * @date 2019/12/22
 */
public class BIOServer {
    private static final int port = 4343;
    public static void main(String[] args) {

        // server (send message)
        Runnable server = () -> {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                while (true) {
                    Socket socket = serverSocket.accept();
                    ExecutorService es = Executors.newFixedThreadPool(10);
                    es.execute(() -> {
                        try (PrintWriter printWriter = new PrintWriter(socket.getOutputStream())) {
                            printWriter.println("hello world");
                            printWriter.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        new Thread(server).start();

        // client (receive message and print)
        try (Socket cSocket = new Socket(InetAddress.getLocalHost(), port)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
            bufferedReader.lines().forEach(s -> System.out.println("[client] " + s));
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
