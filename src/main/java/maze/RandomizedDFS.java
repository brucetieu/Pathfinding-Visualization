package maze;

import pathfinding.Node;
import utils.Delay;

import java.util.*;


/**
 * Randomized Depth First Search maze generation.
 * https://en.wikipedia.org/wiki/Maze_generation_algorithm#Randomized_depth-first_search
 */
public class RandomizedDFS {

    enum Directions {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }

    private Maze maze;
    private boolean[][] marked;
    private Node startNode;
    private Node endNode;

    /**
     * Initialize variables for the randomized dfs.
     *
     * @param maze      The maze.
     * @param startNode The starting node.
     * @param endNode   The ending node (destination).
     */
    public RandomizedDFS(Maze maze, Node startNode, Node endNode) {
        this.maze = maze;
        this.startNode = startNode;
        this.endNode = endNode;
        marked = new boolean[Maze.MAX_HEIGHT][Maze.MAX_WIDTH];
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
        marked[row][col] = true;

        // Choose a random, unvisited neighboring cell, and remove the wall between current cell and chosen cell (carve a path).
        for (HashMap<Directions, Node> randomDir : findRandomDirs(row, col)) {
            carvePath(randomDir);
        }
    }


    /**
     * Find all the adjacent nodes 2 nodes away from the current node in each direction.
     * @param row The current node's row position.
     * @param col The current node's column position.
     * @return A randomized list of hashmaps.
     */
    private List<HashMap<Directions, Node>> findRandomDirs(int row, int col) {
        List<HashMap<Directions, Node>> dirs = new ArrayList<>();

        if (row - 2 >= 0) {
            HashMap<Directions, Node> map = new HashMap<>();
            map.put(Directions.NORTH, this.maze.maze[row - 2][col]);
            dirs.add(map);
        }
        if (row + 2 < Maze.MAX_HEIGHT) {
            HashMap<Directions, Node> map = new HashMap<>();
            map.put(Directions.SOUTH, this.maze.maze[row + 2][col]);
            dirs.add(map);
        }
        if (col - 2 >= 0) {
            HashMap<Directions, Node> map = new HashMap<>();
            map.put(Directions.WEST, this.maze.maze[row][col - 2]);
            dirs.add(map);
        }
        if (col + 2 < Maze.MAX_WIDTH) {
            HashMap<Directions, Node> map = new HashMap<>();
            map.put(Directions.EAST, this.maze.maze[row][col + 2]);
            dirs.add(map);
        }

        // Shuffle to ensure randomness.
        Collections.shuffle(dirs);
        return dirs;

    }

    /**
     * After picking a direction, we carve a path 2 nodes long/wide in that direction. The current node passed in is a wall. If the second wall is unvisited,
     * We break the walls between it and do a dfs on that chosen node. This is what "Remove the wall between the current cell and the chosen cell" means
     * given my Node class arrangement.
     *
     *        *                                           dfs
     *      wall | wall | wall | wall  --> wall | path | path | wall
     *
     *
     * @param dir The direction (north, east, south, or west).
     */
    private void carvePath(HashMap<Directions, Node> dir) {
        if (dir.containsKey(Directions.NORTH)) {
            Node node = dir.get(Directions.NORTH);
            if (!marked[node.getRow()][node.getCol()]) {
                this.maze.maze[node.getRow() + 1][node.getCol()].setWall(false);  // Carve upward path.
                this.maze.maze[node.getRow()][node.getCol()].setWall(false);
                this.maze.update();
                Delay.delay(15);
                dfs(node.getRow(), node.getCol());
            }
        } else if (dir.containsKey(Directions.SOUTH)) {
            Node node = dir.get(Directions.SOUTH);
            if (!marked[node.getRow()][node.getCol()]) {
                this.maze.maze[node.getRow() - 1][node.getCol()].setWall(false);  // Carve downward path.
                this.maze.maze[node.getRow()][node.getCol()].setWall(false);
                this.maze.update();
                Delay.delay(15);
                dfs(node.getRow(), node.getCol());
            }
        } else if (dir.containsKey(Directions.WEST)) {
            Node node = dir.get(Directions.WEST);
            if (!marked[node.getRow()][node.getCol()]) {
                this.maze.maze[node.getRow()][node.getCol() + 1].setWall(false);  // Carve left path.
                this.maze.maze[node.getRow()][node.getCol()].setWall(false);
                this.maze.update();
                Delay.delay(15);
                dfs(node.getRow(), node.getCol());
            }
        } else if (dir.containsKey(Directions.EAST)) {
            Node node = dir.get(Directions.EAST);
            if (!marked[node.getRow()][node.getCol()]) {
                this.maze.maze[node.getRow()][node.getCol() - 1].setWall(false);  // Carve right path.
                this.maze.maze[node.getRow()][node.getCol()].setWall(false);
                this.maze.update();
                Delay.delay(15);
                dfs(node.getRow(), node.getCol());
            }
        }

    }
}