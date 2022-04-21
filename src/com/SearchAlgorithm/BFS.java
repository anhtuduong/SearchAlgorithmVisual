package com.SearchAlgorithm;

import com.Board;
import com.Main;
import com.Pixel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class BFS extends SearchAlgorithm{

    private LinkedList<Pixel> queue;

    public BFS(Board board) {
        super(board);
        queue = new LinkedList<>();
    }

    public boolean findTarget(Pixel root) {

        expanding(root);
        System.out.println("Queue: " + queue.size());

        if(targetFound){
            Pixel temp = target;
            while(temp.getNeighborsList().getFirst() != root){
                temp = temp.getNeighborsList().pop();
                pathList.push(temp);
            }
        }

        return targetFound;
    }

    private void expanding(Pixel current) {

        if(current.isTarget()){
            targetFound = true;
            target = current;
        }
        else{
            if(current.isDefault()){
                searchAnimation.getChildren().add(current.changeAnimation(Main.PIXEL_SEARCHING));
            }
            current.setVisited(true);

            //Process on neighbors
            int x = current.getPosX();
            int y = current.getPosY();

            Pixel up = board.getPixel(x, y-1);
            if(up != null && !up.isVisited() && !up.isWall() && !queue.contains(up)){
                queue.addLast(up);
                up.addNeighbor(current);
            }

            Pixel right = board.getPixel(x+1, y);
            if(right != null && !right.isVisited() && !right.isWall() && !queue.contains(right)){
                queue.addLast(right);
                right.addNeighbor(current);
            }

            Pixel down = board.getPixel(x, y+1);
            if(down != null && !down.isVisited() && !down.isWall() && !queue.contains(down)){
                queue.addLast(down);
                down.addNeighbor(current);
            }

            Pixel left = board.getPixel(x-1, y);
            if(left != null && !left.isVisited() && !left.isWall() && !queue.contains(left)){
                queue.addLast(left);
                left.addNeighbor(current);
            }

            expanding(queue.removeFirst());

        }
    }
}
