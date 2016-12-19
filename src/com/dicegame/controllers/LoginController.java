package com.dicegame.controllers;

import com.dicegame.interfaces.Requestable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField nickField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    Requestable serverCommunicator;

    @FXML
    public void handleLoginAction(ActionEvent actionEvent) throws IOException {

        String nick = nickField.getText();
        if(isValid(nick)) {

            try {
                serverCommunicator.login(nick);
            }
            catch (Exception e){

            }

            Parent createGame = FXMLLoader.load(getClass().getResource("../view/listOfGames.fxml"));
            Scene home_page = new Scene(createGame);
            Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            app_stage.setScene(home_page);
            app_stage.show();
        }
    }

    private boolean isValid(String nick){
        return true;
    }
}
