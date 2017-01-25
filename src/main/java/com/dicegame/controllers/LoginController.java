package com.dicegame.controllers;

import com.dicegame.interfaces.Requestable;
import com.dicegame.model.Account;
import com.dicegame.model.containers.LoginContainer;
import com.google.gson.Gson;
import javafx.application.Platform;
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
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField nickField;

    @FXML
    private Button loginButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    Requestable serverCommunicator = new RequestControllerMocked();

    @FXML
    public void handleLoginAction(ActionEvent actionEvent) throws IOException {
        loginButton.setDisable(true);
        String nick = nickField.getText();
        if(isValid(nick)) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(serverCommunicator.login(nick)) {
                        //create Account
                        Account userAccount = Account.getInstance();
                        userAccount.setNick(nick);

                        loginButton.setDisable(false);

                        Parent createGame = null;
                        try {
                            createGame = FXMLLoader.load(getClass().getResource("../view/listOfGames.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene home_page = new Scene(createGame);
                        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        Platform.runLater(() -> app_stage.setScene(home_page));
                        Platform.runLater(() -> app_stage.show());

                    }else{
                        loginButton.setDisable(false);
                        nickField.setText("");
                    }
                }
            }).start();


        }else{
            nickField.setText("");
        }

    }

    private boolean isValid(String nick){
        return nick.matches("[^!$()-+~#@*+%{}<>\\[\\]|\"_^/\\\\]*") && !nick.equals("");
    }

}
