package pathfinding;

import javax.swing.*;
import java.awt.*;

public class Node {

    private int row;
    private int col;
    private boolean isWall;
    private boolean isStart;
    private boolean isEnd;

    /**
     * Initialize a node to a specific (x,y) position in the grid.
     * @param row The row in the grid.
     * @param col The col in the grid.
     * @param isWall Is this node a wall?
     * @param isStart Is this node the start node?
     * @param isEnd Is this node the end node?
     */
    public Node(int row, int col, boolean isWall, boolean isStart, boolean isEnd) {
        this.row = row;
        this.col = col;
        this.isWall = isWall;
        this.isStart = isStart;
        this.isEnd = isEnd;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isWall() {
        return isWall;
    }

    public void setWall(boolean wall) {
        isWall = wall;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

}
