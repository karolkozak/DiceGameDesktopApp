package com.dicegame.window;

import com.dicegame.configuration.ConfigTopic;
import com.dicegame.controllers.RequestController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import java.util.ArrayList;

@SpringBootApplication
public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        primaryStage.setTitle("Gra w kosci");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public static void main(String[] args) {

        // Launch the application
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        RequestController.jmsTemplate=jmsTemplate;

        launch(args);

    }
}
