package maze;

import pathfinding.Node;
import utils.Delay;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Randomized Depth First Search maze generation.
 * https://en.wikipedia.org/wiki/Maze_generation_algorithm#Randomized_depth-first_search
 */
public class RandomizedDFS {

    private Maze maze;
    private boolean[][] marked;
    private Node startNode;
    private Node endNode;

    public RandomizedDFS(Maze maze, Node startNode, Node endNode) {
        this.maze = maze;
        this.startNode = startNode;
        this.endNode = endNode;

        this.marked = new boolean[Maze.MAX_HEIGHT][Maze.MAX_WIDTH];
    }


    /**
     * Generate the maze with randomized dfs.
     */
    public void generateMaze() {
        dfs(this.startNode.getRow(), this.startNode.getCol());
    }


    /**
     * Perform a dfs on a node.
     *
     * @param row The row in the maze.
     * @param col The col in the maze.
     */
    private void dfs(int row, int col) {

        // Mark current cell as visited.
        this.marked[row][col] = true;

        // While the current cell has any unvisited neighbor cells...
        for (Node node : findAdjacent(row, col)) {

            // Choose one of the neighboring cells (given randomly).
            if (!this.marked[node.getRow()][node.getCol()]) {
                this.maze.getMaze()[row][col].setWall(false);  // Remove the wall between the current cell and the chosen cell.

                Delay.delay(5);
                this.maze.update();
                dfs(node.getRow(), node.getCol());  // Invoke dfs recursively for a chosen cell.
            }
        }

    }

    /**
     * Find the adjacent unvisited nodes given a current node.
     *
     * @param row The row position of this adjacent unvisited node.
     * @param col The col position of this adjacent unvisited node.
     * @return A list of adjacent nodes.
     */
    private List<Node> findAdjacent(int row, int col) {
        List<Node> adjNeighbors = new ArrayList<>();

        // Top
        if (row - 1 >= 0 && this.maze.getMaze()[row - 1][col].isWall()) {
            adjNeighbors.add(new Node(row - 1, col, true, false, false, false, false));
        }
        // Bottom
        if (row + 1 < Maze.MAX_HEIGHT && this.maze.getMaze()[row + 1][col].isWall()) {
            adjNeighbors.add(new Node(row + 1, col, true, false, false, false, false));
        }
        // Left
        if (col - 1 >= 0 && this.maze.getMaze()[row][col - 1].isWall()) {
            adjNeighbors.add(new Node(row, col - 1, true, false, false, false, false));
        }
        // Right
        if (col + 1 < Maze.MAX_WIDTH && this.maze.getMaze()[row][col + 1].isWall()) {
            adjNeighbors.add(new Node(row, col + 1, true, false, false, false, false));
        }

        // Important! We must shuffle the list randomly, hence 'randomized' dfs.
        Collections.shuffle(adjNeighbors);
        return adjNeighbors;

    }

}