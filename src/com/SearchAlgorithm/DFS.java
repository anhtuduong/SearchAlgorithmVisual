package com.SearchAlgorithm;

import com.Board;
import com.Main;
import com.Pixel;

public class DFS extends SearchAlgorithm{

    public DFS(Board board) {
        super(board);
    }

    public boolean findTarget(Pixel current) {

        if(current.isTarget()){
            targetFound = true;
            return true;
        }
        if(current.isDefault()){
            searchAnimation.getChildren().add(current.changeAnimation(Main.PIXEL_SEARCHING));
        }
        current.setVisited(true);

        //Process on neighbors
        int x = current.getPosX();
        int y = current.getPosY();

        Pixel up = board.getPixel(x, y-1);
        if(up != null && !up.isVisited() && !up.isWall()){
            if(findTarget(up) == true){
                if(current.isDefault()){
                    pathList.push(current);
                }
                return true;
            }
        }
        Pixel right = board.getPixel(x+1, y);
        if(right != null && !right.isVisited() && !right.isWall()){
            if(findTarget(right) == true){
                if(current.isDefault()){
                    pathList.push(current);
                }
                return true;
            }
        }
        Pixel down = board.getPixel(x, y+1);
        if(down != null && !down.isVisited() && !down.isWall()){
            if(findTarget(down) == true){
                if(current.isDefault()){
                    pathList.push(current);
                }
                return true;
            }
        }
        Pixel left = board.getPixel(x-1, y);
        if(left != null && !left.isVisited() && !left.isWall()){
            if(findTarget(left) == true){
                if(current.isDefault()){
                    pathList.push(current);
                }
                return true;
            }
        }

        return false;
    }
}
