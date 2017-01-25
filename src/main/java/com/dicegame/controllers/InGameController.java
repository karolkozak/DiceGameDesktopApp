package com.dicegame.controllers;

import com.dicegame.interfaces.Requestable;
import com.dicegame.model.*;
import com.dicegame.model.containers.InGameRow;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Created by Karol on 07/01/2017.
 */
public class InGameController implements Initializable {

    @FXML
    private TableView<InGameRow> resultsTable;
    @FXML
    private TableColumn<InGameRow, String> playerColumn;
    @FXML
    private TableColumn<InGameRow, String> diceColumn;
    @FXML
    private TableColumn<InGameRow, String> pointsColumn;
    @FXML
    private TableColumn<InGameRow, String> activeColumn;
    @FXML
    private CheckBox box1;
    @FXML
    private CheckBox box2;
    @FXML
    private CheckBox box3;
    @FXML
    private CheckBox box4;
    @FXML
    private CheckBox box5;
    @FXML
    private Button roll;
    @FXML
    private Button pass;
    @FXML
    private Button surrender;
    @FXML
    private Label winningLabel;

    private ObservableList<InGameRow> playersInGame = FXCollections.observableArrayList();
    private Requestable serverCommunicator = new RequestController();
    private GameState gameState;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerColumn.setCellValueFactory(dataValue -> dataValue.getValue().getPlayerNameProperties());
        diceColumn.setCellValueFactory(dataValue -> dataValue.getValue().getListOfDiceProperties());
        pointsColumn.setCellValueFactory(dataValue -> dataValue.getValue().getPointsProperties());
        activeColumn.setCellValueFactory(dataValue -> dataValue.getValue().getActiveProperties());

        resultsTable.setEditable(false);
        resultsTable.setItems(playersInGame);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                gameplay();
            }
        });
        t.start();
    }

    public void handleSurrenderAction(ActionEvent actionEvent) throws IOException {
        serverCommunicator.quitGame(Account.getInstance().getGameID());
        Account.getInstance().setGameID(0);

        Parent createGame = FXMLLoader.load(getClass().getResource("../view/listOfGames.fxml"));
        Scene home_page = new Scene(createGame);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(home_page);
        app_stage.show();
    }

    public void handleRollAction(ActionEvent actionEvent) {
        Set<Integer> move = new HashSet<Integer>();
        if(box1.isSelected()) move.add(1);
        if(box2.isSelected()) move.add(2);
        if(box3.isSelected()) move.add(3);
        if(box4.isSelected()) move.add(4);
        if(box5.isSelected()) move.add(5);

        if(!move.isEmpty()) {
            serverCommunicator.makeMove(new Move(move));
        }
    }

    public void handlePassAction(ActionEvent actionEvent) {
        Set<Integer> move = new HashSet<Integer>();
        serverCommunicator.makeMove(new Move(move));
    }

    public void gameplay(){

        pass.setDisable(true);
        roll.setDisable(true);
        this.gameState = serverCommunicator.updateGame(1);
        this.updateTable(this.gameState);

        int mock = 0;
        while(!this.gameState.getStatus().equals(GameStatus.STOPPED)){

            System.out.println(this.gameState.getStatus().toString());

            if(this.gameState.getStatus().equals(GameStatus.STARTED)){
                if(this.gameState.getActivePlayer().equals(Account.getInstance().getNick())){
                    pass.setDisable(false);
                    roll.setDisable(false);
                }
            }
            this.gameState = serverCommunicator.updateGame(mock);

            this.updateTable(this.gameState);

            pass.setDisable(true);
            roll.setDisable(true);

            mock=2;
        }

        Player whoWon = this.gameState.getListOfPlayers().get(0);

        for(Player player: this.gameState.getListOfPlayers()) {
            if(whoWon.getPoints() > player.getPoints()) {
                whoWon = player;
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game finished!");
        alert.setHeaderText(null);
        alert.setContentText("Player: " + whoWon.getName() + " with: " + whoWon.getPoints() + " points.");
        alert.showAndWait();

        System.out.println(this.gameState.getStatus().toString());

        System.out.println("FINITO");

    }

    /**
     * this method is called after receive gameState (call update method by server)
     */
    public void updateTable(GameState gameState) {
        List<Player> players = gameState.getListOfPlayers();


        String toPrintLabel = gameState.getWinningNumber().equals(0) ? "" :gameState.getWinningNumber().toString();

        Platform.runLater(() -> winningLabel.setText(toPrintLabel));
        //remove previous state
        resultsTable.getItems().removeAll(resultsTable.getItems());
        players.forEach(player -> {
            String active = "";
            if(player.getName().equals(gameState.getActivePlayer())){
                active = "A";
            }
            this.playersInGame.add(new InGameRow(player,active));

            if(player.getName().equals(Account.getInstance().getNick())){

                Platform.runLater(() -> box1.setText(player.getDice().get(0).toString())); // trzeba tak bo nawala Not fx thread exeption
                Platform.runLater(() -> box2.setText(player.getDice().get(1).toString()));
                Platform.runLater(() -> box3.setText(player.getDice().get(2).toString()));
                Platform.runLater(() -> box4.setText(player.getDice().get(3).toString()));
                Platform.runLater(() -> box5.setText(player.getDice().get(4).toString()));
        }
        });

        resultsTable.setItems(this.playersInGame);
    }
}
