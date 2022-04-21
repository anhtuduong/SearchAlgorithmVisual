package com;

import com.SearchAlgorithm.BFS;
import com.SearchAlgorithm.DFS;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Setting extends VBox {

    private Board board;

    private Button finderButton;
    private Button targetButton;
    private Button wallButton;
    private Button randomBoard;
    private Button clear;
    private Button startDFS;
    private Button startBFS;
    private ArrayList<Button> buttonList;

    public static Text countTarget = new Text();
    private ArrayList<Text> infoList;

    public Setting(Board board) {

        this.board = board;
        setAlignment(Pos.BASELINE_CENTER);
        setWidth(Main.SETTING_SIZE_X);
        setHeight(Main.SETTING_SIZE_Y);
        setSpacing(1);

        finderButton = new Button("Finder");
        targetButton = new Button("Target");
        wallButton = new Button("Wall");
        randomBoard = new Button("Random Board");
        clear = new Button("Clear");
        startDFS = new Button("Start DFS");
        startBFS = new Button("Start BFS");

        buttonList = new ArrayList<>();
        buttonList.add(finderButton);
        buttonList.add(targetButton);
        buttonList.add(wallButton);
        buttonList.add(randomBoard);
        buttonList.add(clear);
        buttonList.add(startDFS);
        buttonList.add(startBFS);
        for(Button i : buttonList){
            getChildren().add(i);
        }

        infoList = new ArrayList<>();
        infoList.add(countTarget);
        for(Text i : infoList){
            getChildren().add(i);
        }

        buttonSetAction();
        updateInfo();
    }

    public static void updateInfo() {
        //countTarget.setText("Number of target: " + Board.targetList.size());
    }

    private void buttonSetAction() {
        finderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Pixel.createMode = Pixel.PIXEL_MODE.FINDER;
            }
        });
        targetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Pixel.createMode = Pixel.PIXEL_MODE.TARGET;
            }
        });
        wallButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Pixel.createMode = Pixel.PIXEL_MODE.WALL;
            }
        });
        randomBoard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                board.randomBoard();
                for(Button i : buttonList){
                    i.setDisable(false);
                }
                Pixel.createMode = Pixel.PIXEL_MODE.FINDER;
            }
        });
        clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for(Node i : board.getChildren()){
                    Pixel temp = (Pixel) i;
                    temp.clear();
                }
                board.setFinder(null);
                board.setTarget(null);

                for(Button i : buttonList){
                    i.setDisable(false);
                }
                Pixel.createMode = Pixel.PIXEL_MODE.FINDER;
            }
        });
        startDFS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(board.getFinder() != null){
                    Pixel.createMode = Pixel.PIXEL_MODE.LOCK;
                    board.startSearching(new DFS(board));

                    for(Button i : buttonList){
                        i.setDisable(true);
                    }
                    randomBoard.setDisable(false);
                    clear.setDisable(false);
                }
            }
        });
        startBFS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(board.getFinder() != null){
                    Pixel.createMode = Pixel.PIXEL_MODE.LOCK;
                    board.startSearching(new BFS(board));

                    for(Button i : buttonList){
                        i.setDisable(true);
                    }
                    randomBoard.setDisable(false);
                    clear.setDisable(false);
                }
            }
        });
    }
}
