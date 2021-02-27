package maze;

import pathfinding.Node;
import utils.Delay;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Randomized Depth First Search maze generation.
 * https://en.wikipedia.org/wiki/Maze_generation_algorithm#Randomized_depth-first_search
 */
public class RandomizedDFS{

    private Maze maze;
    private boolean[][] visited;  // Maintain a 2d boolean array to mark which nodes have been visited.

    private Random rand = new Random();
    private int height;
    private int width;
    private int r1, r2, c1, c2;
    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public RandomizedDFS(Maze maze) {
        this.maze = maze;
        width = this.maze.getMaze()[0].length;
        height = this.maze.getMaze().length;

        this.visited = new boolean[height][width];
    }

    public int getR1() {
        return r1;
    }

    public void setR1(int r1) {
        this.r1 = r1;
    }

    public int getR2() {
        return r2;
    }

    public void setR2(int r2) {
        this.r2 = r2;
    }

    public int getC1() {
        return c1;
    }

    public void setC1(int c1) {
        this.c1 = c1;
    }

    public int getC2() {
        return c2;
    }

    public void setC2(int c2) {
        this.c2 = c2;
    }

    /**
     * Generate the maze with randomized dfs.
     */
    public void generateMaze() {

        generateStartNode();
        generateEndNode();

        // DFS from the start node.
        dfs(r1, c1);
    }

    /**
     * Perform a dfs on a node.
     * @param row The row in the maze.
     * @param col The col in the maze.
     */
    private void dfs(int row, int col) {

        // Mark current cell as visited.
        this.visited[row][col] = true;

        // While the current cell has any unvisited neighbor cells...
        for (Node node : findAdjacent(row, col)) {

            // Choose one of the neighboring cells (given randomly).
            if (!this.visited[node.getRow()][node.getCol()]) {
                this.maze.getMaze()[row][col].setWall(false);  // Remove the wall between the current cell and the chosen cell.

                Delay.delay(5);
                this.maze.update();
                dfs(node.getRow(), node.getCol());  // Invoke dfs recursively for a chosen cell.
            }
        }

    }

    /**
     * Find the adjacent unvisited nodes given a current node.
     * @param row The row position of this adjacent unvisited node.
     * @param col The col position of this adjacent unvisited node.
     * @return A list of adjacent nodes.
     */
    private List<Node> findAdjacent(int row, int col) {
        List<Node> adjNeighbors = new ArrayList<>();

        // Top
        if (row-1 >= 0 && this.maze.getMaze()[row-1][col].isWall()) {
            adjNeighbors.add(new Node(row-1, col, true, false, false, false, false));
        }
        // Bottom
        if (row+1 < height && this.maze.getMaze()[row+1][col].isWall()) {
            adjNeighbors.add(new Node(row+1, col, true, false, false, false, false));
        }
        // Left
        if (col-1 >= 0 && this.maze.getMaze()[row][col-1].isWall()) {
            adjNeighbors.add(new Node(row, col-1, true, false, false, false, false));
        }
        // Right
        if (col+1 < width && this.maze.getMaze()[row][col+1].isWall()) {
            adjNeighbors.add(new Node(row, col+1, true, false, false, false, false));
        }

        // Important! We must shuffle the list randomly, hence 'randomized' dfs.
        Collections.shuffle(adjNeighbors);
        return adjNeighbors;

    }

    /**
     * Generate a start node at an odd place on the maze.
     */
    private void generateStartNode() {
        r1 = rand.nextInt(height);
        while (r1 % 2 == 0) r1 = rand.nextInt(height);

        // Generate random odd col for end node.
        c1 = rand.nextInt(width);
        while (c1 % 2 == 0) c1 = rand.nextInt(width);

        this.maze.getMaze()[r1][c1].setStart(true);
        this.maze.getMaze()[r1][c1].setWall(false);
    }

    /**
     * Generate an end node at an even place on the maze. Do this so that startNode != endNode.
     */
    private void generateEndNode() {
        // Generate random even row for start node.
        r2 = rand.nextInt(height);
        while (r2 % 2 != 0) r2 = rand.nextInt(height);

        // Generate random even col for end node.
        c2 = rand.nextInt(width);
        while (c2 % 2 != 0) c2 = rand.nextInt(width);

        this.maze.getMaze()[r2][c2].setEnd(true);
        this.maze.getMaze()[r2][c2].setWall(false);
    }

}
