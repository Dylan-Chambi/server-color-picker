package com.example.colorpickerserver;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.shape.Polygon;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ServerController implements Initializable {
    @FXML
    private Polygon polygonShapeID;
    @FXML
    private Label clientConnectionID;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
        ServerSocket serverSocket = MyServerSocket.getInstance().getServerSocket();
        serverSocket.bind(new InetSocketAddress(MyServerSocket.getInstance().getServerIP(), MyServerSocket.getInstance().getPort()));

        new Thread(() -> {
            try {
                System.out.println("In thread " + Thread.currentThread().getName());
                    Socket socket = serverSocket.accept();

                Platform.runLater(() -> {
                clientConnectionID.setText(socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
                });
                    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                while (true) {
                    try {
                    String color = dataInputStream.readUTF();

                        Platform.runLater(() -> {
                            polygonShapeID.setFill(javafx.scene.paint.Color.valueOf(color));
                            clientConnectionID.setText(socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
                        });
                    } catch (IOException e) {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}