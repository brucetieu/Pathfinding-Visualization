package maze;

import pathfinding.Node;
import pathfinding.Wall;
import utils.Delay;
import utils.MazeUtils;

import java.util.*;


/**
 * Randomized Depth First Search maze generation.
 * https://en.wikipedia.org/wiki/Maze_generation_algorithm#Randomized_depth-first_search
 */
public class RandomizedDFS {

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
        for (Wall randomWall : findRandomDirs(row, col)) {
            carvePath(randomWall);
        }
    }


    /**
     * Find all the adjacent nodes 2 nodes away from the current node in each direction.
     * @param row The current node's row position.
     * @param col The current node's column position.
     * @return A randomized list of walls.
     */
    private List<Wall> findRandomDirs(int row, int col) {
        List<Wall> walls = new ArrayList<>();

        if (row - 2 >= 0) {
            walls.add(new Wall(MazeUtils.Directions.NORTH, this.maze.maze[row - 2][col]));
        }
        if (row + 2 < Maze.MAX_HEIGHT) {
            walls.add(new Wall(MazeUtils.Directions.SOUTH, this.maze.maze[row + 2][col]));
        }
        if (col - 2 >= 0) {
            walls.add(new Wall(MazeUtils.Directions.WEST, this.maze.maze[row][col - 2]));
        }
        if (col + 2 < Maze.MAX_WIDTH) {
            walls.add(new Wall(MazeUtils.Directions.EAST, this.maze.maze[row][col + 2]));
        }

        // Shuffle to ensure randomness.
        Collections.shuffle(walls);
        return walls;

    }

    /**
     * After picking a direction, we carve a path 2 nodes long/wide in that direction. The current node passed in is a wall. If the second wall is unvisited,
     * We break the walls between it and do a dfs on that chosen node. This is what "Remove the wall between the current cell and the chosen cell" means
     * given my Node / Maze arrangement.
     *
     *        *                                           dfs
     *      wall | wall | wall | wall  --> wall | path | path | wall
     *
     *
     * @param wall The wall (Direction, Node)
     */
    private void carvePath(Wall wall) {
        if (wall.getDirection() == MazeUtils.Directions.NORTH) {
            Node node = wall.getNode();
            if (!marked[node.getRow()][node.getCol()]) {
                this.maze.maze[node.getRow() + 1][node.getCol()].setWall(false);  // Carve upward path.
                this.maze.maze[node.getRow()][node.getCol()].setWall(false);
                this.maze.update();
                dfs(node.getRow(), node.getCol());
            }
        } else if (wall.getDirection() == MazeUtils.Directions.SOUTH) {
            Node node = wall.getNode();
            if (!marked[node.getRow()][node.getCol()]) {
                this.maze.maze[node.getRow() - 1][node.getCol()].setWall(false);  // Carve downward path.
                this.maze.maze[node.getRow()][node.getCol()].setWall(false);
                this.maze.update();
                dfs(node.getRow(), node.getCol());
            }
        } else if (wall.getDirection() == MazeUtils.Directions.WEST) {
            Node node = wall.getNode();
            if (!marked[node.getRow()][node.getCol()]) {
                this.maze.maze[node.getRow()][node.getCol() + 1].setWall(false);  // Carve left path.
                this.maze.maze[node.getRow()][node.getCol()].setWall(false);
                this.maze.update();
                dfs(node.getRow(), node.getCol());
            }
        } else if (wall.getDirection() == MazeUtils.Directions.EAST) {
            Node node = wall.getNode();
            if (!marked[node.getRow()][node.getCol()]) {
                this.maze.maze[node.getRow()][node.getCol() - 1].setWall(false);  // Carve right path.
                this.maze.maze[node.getRow()][node.getCol()].setWall(false);
                this.maze.update();
                dfs(node.getRow(), node.getCol());
            }
        }

    }
}