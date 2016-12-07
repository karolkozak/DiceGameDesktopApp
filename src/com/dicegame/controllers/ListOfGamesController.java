package com.dicegame.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Karol on 07/12/2016.
 */
public class ListOfGamesController implements Initializable {

    @FXML
    private TableView listOfGames;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //get data from server and show in table
    }

    @FXML
    public void handleUpdateAction(ActionEvent actionEvent) {
        //update
    }

    @FXML
    public void handleJoinAction(ActionEvent actionEvent) {
        //getSelected
    }

    @FXML
    public void handleWatchAction(ActionEvent actionEvent) {
        //getSelected
    }

    @FXML
    public void handleCreateGameAction(ActionEvent actionEvent) throws IOException {
        Parent createGame = FXMLLoader.load(getClass().getResource("../view/createGame.fxml"));
        Scene home_page = new Scene(createGame);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(home_page);
        app_stage.show();
    }
}
