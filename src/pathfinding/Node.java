package pathfinding;

public class Node {

    private int row;
    private int col;
    private boolean isWall;
    private boolean start;
    private boolean end;


    /**
     * Initialize a node to a specific (x,y) position in the grid.
     * @param row The row in the grid.
     * @param col The col in the grid.
     */
    public Node(int row, int col) {
        this.row = row;
        this.col = col;
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
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}
