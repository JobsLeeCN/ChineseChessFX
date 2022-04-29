package org.jobsl.cgames.cchess;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jobsl.cgames.cchess.controller.MainController;

import java.net.URL;

/**
 * 游戏启动器
 *
 * @author JobsLee
 */
public class GameLauncher extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // fxml loader
        URL location = getClass().getResource("/fxml/ChineseChess.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = fxmlLoader.load();
        // init canvas
        MainController mainController = fxmlLoader.getController();
        primaryStage.setTitle("中国象棋FX完美版");
        primaryStage.setScene(new Scene(root));
        primaryStage.setOnCloseRequest(event -> mainController.destory());
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
