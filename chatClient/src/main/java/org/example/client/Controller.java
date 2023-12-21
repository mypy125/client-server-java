package org.example.client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Network network;

    @FXML
    TextField msgField;
    @FXML
    TextArea mainArea;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        network = new Network();
    }

    public void sendMassageAction(ActionEvent actionEvent){

    }
}
