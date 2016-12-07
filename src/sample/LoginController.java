package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void handleLoginAction(ActionEvent actionEvent) throws IOException {
        Parent createGame = FXMLLoader.load(getClass().getResource("listOfGames.fxml"));
        Scene home_page = new Scene(createGame);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(home_page);
        app_stage.show();
    }
}
