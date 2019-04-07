package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    static Scene scene;
    static Parent root;
    static String rootStyle;
    @Override
    public void start(Stage primaryStage) throws Exception{
        root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Tic Tac Toe Game - by Krystian Wolański");
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        Controller.stage = (Stage) Main.scene.getWindow();

        rootStyle = root.getStyle();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
