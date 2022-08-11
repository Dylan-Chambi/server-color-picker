package com.example.colorpickerserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServerSocket {
    private static MyServerSocket instance;
    private static ServerSocket serverSocket;
    private static String serverIP;
    private static int port;

    private MyServerSocket() throws IOException {
        serverSocket = new ServerSocket();
    }
    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        MyServerSocket.serverIP = serverIP;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        MyServerSocket.port = port;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        MyServerSocket.serverSocket = serverSocket;
    }

    public static MyServerSocket getInstance() throws IOException {
        if (instance == null) {
            instance = new MyServerSocket();
        }
        return instance;
    }

}
