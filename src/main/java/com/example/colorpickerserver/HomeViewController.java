package com.example.colorpickerserver;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;


public class HomeViewController implements Initializable {
    @FXML
    private TextField serverIPTF;
    @FXML
    private TextField portTF;
    @FXML
    private Button btnConnect;

    @FXML
    public void onClickStartServer(ActionEvent actionEvent) throws UnknownHostException {

        String serverIP = (serverIPTF.getText().isEmpty()) ? ( serverIPTF.getPromptText() ): serverIPTF.getText();
        String port = (portTF.getText().isEmpty()) ? ( portTF.getPromptText() ): portTF.getText();


            try {
                MyServerSocket.getInstance().setServerIP(serverIP);
                MyServerSocket.getInstance().setPort(Integer.parseInt(port));
                Parent root = FXMLLoader.load(ServerController.class.getResource("server-view.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            serverIPTF.setPromptText(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        portTF.setPromptText("6000");
    }


}
