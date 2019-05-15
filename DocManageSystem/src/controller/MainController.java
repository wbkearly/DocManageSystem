package controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainController extends Application {

    private static Stage mainState;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        mainState = primaryStage;
        primaryStage.show();
    }
}
