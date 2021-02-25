package maze;

import pathfinding.Node;

import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

public class DFSRandomized extends JPanel {

    private Node[][] maze;
    private boolean[][] visited;

    private int height;
    private int width;

    public DFSRandomized(Node[][] maze) {
        this.maze = maze;
        width = this.maze[0].length;
        height = this.maze.length;

        this.visited = new boolean[][][width][height];
    }

    public void generateMaze() {
        this.maze[0][0].setStart(true);
        this.maze[0][0].setWall(false);

        dfs(0, 0);
    }

    private void dfs(int row, int col) {

        this.visited[row][col] = true;


    }

    private List<Node> findAdjacent(int row, int col) {
        List<Node> adjNeighbors = new ArrayList<>();



    }
}
