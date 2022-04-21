package com.SearchAlgorithm;

import com.Board;
import com.Main;
import com.Pixel;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.LinkedList;

public abstract class SearchAlgorithm {

    protected Board board;
    protected Pixel target;
    protected boolean targetFound;
    protected SequentialTransition searchAnimation;
    protected SequentialTransition pathAnimation;
    protected LinkedList<Pixel> pathList;
    protected long pathCount;

    public SearchAlgorithm(Board board) {
        this.board = board;
        targetFound = false;
        pathAnimation = new SequentialTransition();
        searchAnimation = new SequentialTransition();
        pathList = new LinkedList<>();
        pathCount = 0;
    }
    public void setTargetFound(boolean targetFound) {
        this.targetFound = targetFound;
    }
    public boolean isTargetFound() {
        return targetFound;
    }

    public void start() {
        findTarget(this.board.getFinder());

        while(pathList.size() != 0){
            pathCount++;
            pathAnimation.getChildren().add(pathList.pop().changeAnimation(Main.PIXEL_PATH));
        }
        searchAnimation.play();
        searchAnimation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pathAnimation.play();
            }
        });
    }

    public boolean findTarget(Pixel current) {
        return targetFound;
    }
}
