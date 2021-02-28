package pathfinding;

import java.util.Objects;

public class Node {

    private int row;
    private int col;
    private boolean isWall;
    private boolean isStart;
    private boolean isEnd;
    private boolean isPath;
    private boolean isVisited;

    /**
     * Initialize a node to a specific (x,y) position in the grid.
     * @param row The row in the grid.
     * @param col The col in the grid.
     * @param isWall Is this node a wall?
     * @param isStart Is this node the start node?
     * @param isEnd Is this node the end node?
     * @param isPath Is this node a part of a path to the end node?
     */
    public Node(int row, int col, boolean isWall, boolean isStart, boolean isEnd, boolean isVisited, boolean isPath) {
        this.row = row;
        this.col = col;
        this.isWall = isWall;
        this.isStart = isStart;
        this.isEnd = isEnd;
        this.isVisited = isVisited;
        this.isPath = isPath;
    }

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

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public boolean isPath() {
        return isPath;
    }

    public void setPath(boolean path) {
        isPath = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return row == node.row && col == node.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, isWall, isStart, isEnd, isVisited);
    }
}
