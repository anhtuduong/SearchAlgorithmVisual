package com;

import com.SearchAlgorithm.SearchAlgorithm;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class Board extends GridPane {

    private int X;
    private int Y;

    private Pixel finder;
    private Pixel target;

    public Board(int xLen, int yLen) {
        X = xLen;
        Y = yLen;
        for(int x=0; x<X; x++){
            for(int y=0; y<Y; y++){
                add(new Pixel(x, y, this), x, y);
            }
        }
    }

    public void randomBoard() {
        for(Node i : getChildren()){
            Pixel temp = (Pixel) i;
            temp.clear();
            switch (new Random().nextInt(Main.WALL_PERCENT/10)){
                case 0:
                    temp.setMode(Pixel.PIXEL_MODE.WALL);
            }
        }
        finder = null;
        target = null;
    }
    public Pixel getFinder() {
        return finder;
    }
    public void setFinder(Pixel finder) {
        this.finder = finder;
    }
    public Pixel getTarget() {
        return target;
    }
    public void setTarget(Pixel target) {
        this.target = target;
    }
    public Pixel getPixel(int x, int y) {
        Pixel result = null;
        for(int i=0; i<getChildren().size(); i++){
            Pixel temp = (Pixel) getChildren().get(i);
            if(getColumnIndex(temp) == x && getRowIndex(temp) == y){
                result = temp;
                break;
            }
        }
        return result;
    }
    public void startSearching(SearchAlgorithm algorithm){
        algorithm.start();
    }

}
