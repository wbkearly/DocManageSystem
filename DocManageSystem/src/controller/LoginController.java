package controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController extends Application {

    private static Stage loginState;
    @FXML private Text actionTarget;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        loginState = new Stage();
        loginState = primaryStage;
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/view/LoginFrame.fxml"));
        Scene scene = new Scene(root, 800, 600);

        loginState.setTitle("档案管理系统");
        loginState.setScene(scene);
        loginState.show();

    }

    // 处理登录事件
    public void loginAction(ActionEvent actionEvent) {

        new Thread(()-> {
            try {
                actionTarget.setText("点击了登录按钮");

                Thread.sleep(1000);
                actionTarget.setText("");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // 处理退出事件
    public void exitAction(ActionEvent actionEvent) {

        loginState.close();
    }
}
