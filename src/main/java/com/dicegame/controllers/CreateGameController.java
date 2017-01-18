package com.dicegame.controllers;
import com.dicegame.interfaces.Requestable;
import com.dicegame.model.BotConfiguration;
import com.dicegame.model.BotLevel;
import com.dicegame.model.Configuration;
import com.dicegame.model.GameType;
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
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Karol on 07/12/2016.
 */
public class CreateGameController implements Initializable {

    @FXML
    private ComboBox<String> modeComboBox;
    private ObservableList<String> modeComboBoxItems = FXCollections.observableArrayList();

    @FXML
    private Spinner numberOfPlayersSpinner;

    @FXML
    private ComboBox<String> botLevel;
    private ObservableList<String> botLevelItems = FXCollections.observableArrayList();

    @FXML
    private TableView botsTable;

    @FXML
    private TableColumn<BotConfiguration, String> botNameColumn;

    @FXML
    private TableColumn<BotConfiguration, String> botLevelColumn;

    private ObservableList<BotConfiguration> bots = FXCollections.observableArrayList();

    @FXML
    private TextField botName;

    @FXML
    private TextField points;

    Requestable serverCommunicator;

    @FXML
    public void handleCreateGameAction(ActionEvent actionEvent) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Create game exception!");

        GameType gameType = GameType.get(modeComboBox.getValue());
        //System.out.println(gameType);
        if(gameType == null) {
            alert.setContentText("Game type cannot be null!");
            alert.showAndWait();
            return;
        }
        Integer numOfPlayers = 0;
        Integer numberOfPointsToWin = 0;
        try {
            numOfPlayers = (Integer)numberOfPlayersSpinner.getValue();
            if(numOfPlayers == 0) {
                alert.setContentText("Number of players cannot be 0!");
                alert.showAndWait();
                return;
            }
            numOfPlayers -= bots.size();
            if(numOfPlayers < 0) {
                alert.setContentText("Number of players is less than number of bots!");
                alert.showAndWait();
                return;
            } else if(numOfPlayers == 0) {
                Alert alertConf = new Alert(AlertType.CONFIRMATION);
                alertConf.setTitle("Number of players exception!");
                alertConf.setHeaderText("Number of players equals number of bots. You will join only as observer.");
                alertConf.setContentText("Are you ok with this?");
                Optional<ButtonType> result = alertConf.showAndWait();
                if (result.get() != ButtonType.OK){
                    return;
                }
            }
        } catch (Exception e) {
            alert.setContentText("Number of players cannot be string!");
            alert.showAndWait();
            return;
        }
        try {
            numberOfPointsToWin = Integer.parseInt(points.getText());
        } catch (NumberFormatException e) {
            alert.setContentText("Number of points cannot be string or empty!");
            alert.showAndWait();
            return;
        }
        Configuration configuration = new Configuration(numOfPlayers,
                                                        gameType,
                                                        numberOfPointsToWin,
                                                        bots);

        serverCommunicator = new RequestController();
        if(serverCommunicator.createGame(configuration)) {

            Parent createGame = null;
            try {
                createGame = FXMLLoader.load(getClass().getResource("../view/listOfGames.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene home_page = new Scene(createGame);
            Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            app_stage.setScene(home_page);
            app_stage.show();
        }
    }

    @FXML
    public void handleAddBotAction(ActionEvent actionEvent) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Create bot exception!");
        alert.setHeaderText(null);
        String name = botName.getText();
        if(!name.equals("") && isValid(name) && !isAlreadyUsed(name)) {
            BotLevel bLevel = BotLevel.get(botLevel.getValue());
            if(bLevel == null) {
                alert.setContentText("Bot level cannot be null!");
                alert.showAndWait();
                return;
            }
            bots.add(new BotConfiguration(name, bLevel));
            botsTable.setItems(bots);
        } else{
            alert.setContentText("Name field is empty or given name already exists!");
            alert.showAndWait();
        }
        botName.setText("");
    }

    @FXML
    public void handleRemoveBotAction(ActionEvent actionEvent) {
        botsTable.getItems().removeAll(botsTable.getSelectionModel().getSelectedItem());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        modeComboBoxItems.add("Poker");
        modeComboBoxItems.add("N+");
        modeComboBoxItems.add("N*");

        modeComboBox.setItems(modeComboBoxItems);

        numberOfPlayersSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(
                0, 10000, 0));
        
        numberOfPlayersSpinner.setEditable(false);

        botLevelItems.add("Latwy");
        botLevelItems.add("Mistrz");

        botLevel.setItems(botLevelItems);

        botNameColumn.setCellValueFactory(dataValue -> dataValue.getValue().getBotNameProperties());
        botLevelColumn.setCellValueFactory(dataValue -> dataValue.getValue().getBotLevelProperties());

        botsTable.setEditable(false);
        botsTable.setItems(bots);
    }


    @FXML
    public void handleBackAction(ActionEvent actionEvent) throws IOException {
        Parent back = FXMLLoader.load(getClass().getResource("../view/listOfGames.fxml"));
        Scene list_game_page = new Scene(back);
        Stage back_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        back_stage.setScene(list_game_page);
        back_stage.show();
    }

    private boolean isValid(String nick){
        return nick.matches("[^!$()-+~#@*+%{}<>\\[\\]|\"_^/\\\\]*");
    }

    private boolean isAlreadyUsed(String name){
        for (BotConfiguration bot : bots) {
            if (name.equals(bot.getName())) {
                return true;
            }
        }
        return false;
    }
}
