package com;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    public static double BOARD_SIZE_X = 700;
    public static double BOARD_SIZE_Y = 700;
    public static double SETTING_SIZE_X = 250;
    public static double SETTING_SIZE_Y = BOARD_SIZE_Y;
    public static int PIXEL_NUM_X = 25;
    public static int PIXEL_NUM_Y = 25;
    public static double PIXEL_SIZE_X = BOARD_SIZE_X / PIXEL_NUM_X;
    public static double PIXEL_SIZE_Y = BOARD_SIZE_Y / PIXEL_NUM_Y;

    public static Color PIXEL_DEFAULT = Color.valueOf("#FFFAD6");
    public static Color PIXEL_FINDER = Color.valueOf("#4D9DE0");
    public static Color PIXEL_TARGET = Color.valueOf("#CA2E55");
    public static Color PIXEL_WALL = Color.valueOf("#454D5E");
    public static Color PIXEL_SEARCHING = Color.valueOf("#E7DBDA");
    public static Color PIXEL_PATH = Color.valueOf("#2AFC98");

    public static int DELAY = 10;
    public static int WALL_PERCENT = 30;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Board board = new Board(PIXEL_NUM_X, PIXEL_NUM_Y);

        HBox root = new HBox(board, new Setting(board));

        primaryStage.setTitle("Path Finder");
        primaryStage.setScene(new Scene(root, BOARD_SIZE_X + SETTING_SIZE_X, BOARD_SIZE_Y));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
