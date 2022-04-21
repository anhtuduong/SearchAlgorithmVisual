package com;

import javafx.animation.FillTransition;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.SplittableRandom;

public class Pixel extends Rectangle {

    public enum PIXEL_MODE {FINDER, TARGET, WALL, DEFAULT, LOCK};
    public static PIXEL_MODE createMode = PIXEL_MODE.FINDER;
    private PIXEL_MODE mode;
    private int x;
    private int y;
    private boolean visited;
    private LinkedList<Pixel> neighborsList;
    private Board board;

    public Pixel(Board board) {
        this.board = board;
        setWidth(Main.PIXEL_SIZE_X);
        setHeight(Main.PIXEL_SIZE_Y);
        setFill(Main.PIXEL_DEFAULT);
        setStroke(Color.BROWN);
        setStrokeWidth(0.1);
        setStrokeType(StrokeType.INSIDE);
        mode = PIXEL_MODE.DEFAULT;
        visited = false;
        neighborsList = new LinkedList<>();
        setMouseAction();
    }
    public Pixel(int x, int y, Board board) {
        this.board = board;
        this.x = x;
        this.y = y;
        setWidth(Main.PIXEL_SIZE_X);
        setHeight(Main.PIXEL_SIZE_Y);
        setFill(Main.PIXEL_DEFAULT);
        setStroke(Color.BROWN);
        setStrokeWidth(0.1);
        setStrokeType(StrokeType.INSIDE);
        mode = PIXEL_MODE.DEFAULT;
        visited = false;
        neighborsList = new LinkedList<>();
        setMouseAction();
    }

    public FillTransition changeAnimation(Color color) {
        FillTransition change = new FillTransition(Duration.millis(Main.DELAY), this);
        change.setToValue(color);
        visited = true;
        return change;
    }
    public int getPosX() {
        return x;
    }
    public int getPosY() {
        return y;
    }
    public void clear() {
        visited = false;
        setMode(PIXEL_MODE.DEFAULT);
        neighborsList = new LinkedList<>();
    }
    public PIXEL_MODE getMode() {
        return mode;
    }
    public void setMode(PIXEL_MODE mode) {
        switch (mode) {
            case FINDER:
                this.mode = mode;
                setFill(Main.PIXEL_FINDER);
                break;
            case TARGET:
                this.mode = mode;
                setFill(Main.PIXEL_TARGET);
                break;
            case WALL:
                this.mode = mode;
                setFill(Main.PIXEL_WALL);
                break;
            case DEFAULT:
                this.mode = mode;
                setFill(Main.PIXEL_DEFAULT);
        }
    }
    public boolean isVisited() {
        return visited;
    }
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    public boolean isTarget() {
        return mode == PIXEL_MODE.TARGET;
    }
    public boolean isDefault() {
        return mode == PIXEL_MODE.DEFAULT;
    }
    public boolean isFinder() {
        return mode == PIXEL_MODE.FINDER;
    }
    public boolean isWall() {
        return mode == PIXEL_MODE.WALL;
    }
    public void addNeighbor(Pixel neighbor) {
        neighborsList.push(neighbor);
    }
    public LinkedList<Pixel> getNeighborsList() {
        return neighborsList;
    }

    public void setMouseAction() {
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                switch (createMode) {
                    case FINDER:
                        mode = createMode;
                        setFill(Main.PIXEL_FINDER);
                        if(board.getFinder() != null){
                            board.getFinder().setMode(PIXEL_MODE.DEFAULT);
                        }
                        board.setFinder(Pixel.this);
                        break;
                    case TARGET:
                        mode = createMode;
                        setFill(Main.PIXEL_TARGET);
                        if(board.getTarget() != null){
                            board.getTarget().setMode(PIXEL_MODE.DEFAULT);
                        }
                        board.setTarget(Pixel.this);
                        break;
                    case WALL:
                        mode = createMode;
                        setFill(Main.PIXEL_WALL);
                        break;
                    case DEFAULT:
                        mode = createMode;
                        setFill(Main.PIXEL_DEFAULT);
                }
            }
        });

    }
}
