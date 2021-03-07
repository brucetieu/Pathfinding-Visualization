package maze;

import pathfinding.Node;
import pathfinding.Wall;
import utils.MazeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Generate a maze using Prim's algorithm.
 * https://en.wikipedia.org/wiki/Maze_generation_algorithm#Randomized_Prim's_algorithm
 */
public class RandomizedPrims {

    private Maze maze;
    private Node startNode;
    private Node endNode;
    private boolean[][] marked;
    private List<Wall> listOfWalls;
    private Random rand = new Random();

    /**
     * Initialize variables for maze creation.
     *
     * @param maze      The maze.
     * @param startNode A random starting node.
     * @param endNode   A random ending node.
     */
    public RandomizedPrims(Maze maze, Node startNode, Node endNode) {
        this.maze = maze;
        this.startNode = startNode;
        this.endNode = endNode;
        marked = new boolean[Maze.MAX_HEIGHT][Maze.MAX_WIDTH];
        listOfWalls = new ArrayList<>();
    }

    /**
     * Generate the maze.
     */
    public void generateMaze() {
        // Pick a cell, mark it as part of the maze.
        marked[this.startNode.getRow()][this.startNode.getCol()] = true;

        // Add the walls of the cell to the wall list.
        addWalls(this.startNode.getRow(), this.startNode.getCol());

        // While there are walls in the list:
        while (!listOfWalls.isEmpty()) {

            // Pick a random wall from the list.
            Wall randomWall = listOfWalls.get(rand.nextInt(listOfWalls.size()));

            // Remove the (random) wall from the list.
            removeWall(randomWall);

            if (randomWall.getDirection() == MazeUtils.Directions.NORTH) {
                Node randNode = randomWall.getNode();
                breakWallNorth(randNode);
            } else if (randomWall.getDirection() == MazeUtils.Directions.SOUTH) {
                Node randNode = randomWall.getNode();
                breakWallSouth(randNode);
            } else if (randomWall.getDirection() == MazeUtils.Directions.EAST) {
                Node randNode = randomWall.getNode();
                breakWallEast(randNode);
            } else if (randomWall.getDirection() == MazeUtils.Directions.WEST) {
                Node randNode = randomWall.getNode();
                breakWallWest(randNode);
            }
        }

    }

    /**
     * Remove the randomly chosen wall from the list of walls.
     *
     * @param randomWall The random wall to be removed.
     */
    private void removeWall(Wall randomWall) {
        listOfWalls.remove(randomWall);
    }


    /**
     * Add neighboring walls.
     *
     * @param row Current cell row.
     * @param col Current cell col.
     */
    private void addWalls(int row, int col) {

        if (row - 1 >= 0) {
            if (this.maze.maze[row - 1][col].isWall()) {
                listOfWalls.add(new Wall(MazeUtils.Directions.NORTH, this.maze.maze[row - 1][col]));
            }

        }
        if (row + 1 < Maze.MAX_HEIGHT) {
            if (this.maze.maze[row + 1][col].isWall()) {
                listOfWalls.add(new Wall(MazeUtils.Directions.SOUTH, this.maze.maze[row + 1][col]));
            }

        }
        if (col - 1 >= 0) {
            if (this.maze.maze[row][col - 1].isWall()) {
                listOfWalls.add(new Wall(MazeUtils.Directions.WEST, this.maze.maze[row][col - 1]));
            }

        }
        if (col + 1 < Maze.MAX_WIDTH) {
            if (this.maze.maze[row][col + 1].isWall()) {
                listOfWalls.add(new Wall(MazeUtils.Directions.EAST, this.maze.maze[row][col + 1]));
            }

        }
    }

    /**
     * If wall is in the North, make the (random) wall a passage and mark the unvisited cell as part of the maze.
     *
     * @param randNode The wall node.
     */
    private void breakWallNorth(Node randNode) {
        if (randNode.getRow() - 1 >= 0 && !marked[randNode.getRow() - 1][randNode.getCol()]) {
            this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
            this.maze.maze[randNode.getRow() - 1][randNode.getCol()].setWall(false);
            marked[randNode.getRow() - 1][randNode.getCol()] = true;
            addWalls(randNode.getRow() - 1, randNode.getCol());
        } else if (randNode.getRow() + 1 < Maze.MAX_HEIGHT && !marked[randNode.getRow() + 1][randNode.getCol()]) {
            this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
            this.maze.maze[randNode.getRow() + 1][randNode.getCol()].setWall(false);
            this.maze.update();
            marked[randNode.getRow() + 1][randNode.getCol()] = true;
            addWalls(randNode.getRow() + 1, randNode.getCol());
        }
    }

    /**
     * If wall is in the South, make the (random) wall a passage and mark the unvisited cell as part of the maze.
     *
     * @param randNode The random wall node.
     */
    private void breakWallSouth(Node randNode) {
        // If only one of the two cells that the wall divides is visited, then:
        if (randNode.getRow() - 1 >= 0 && !marked[randNode.getRow() - 1][randNode.getCol()]) {

            // Make the wall a passage and mark the unvisited cell as part of the maze.
            this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
            this.maze.maze[randNode.getRow() - 1][randNode.getCol()].setWall(false);
            this.maze.update();
            marked[randNode.getRow() - 1][randNode.getCol()] = true;
            addWalls(randNode.getRow() - 1, randNode.getCol());  // Add the neighboring walls of the cell to the wall list.
        } else if (randNode.getRow() + 1 < Maze.MAX_HEIGHT && !marked[randNode.getRow() + 1][randNode.getCol()]) {
            this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
            this.maze.maze[randNode.getRow() + 1][randNode.getCol()].setWall(false);
            this.maze.update();
            marked[randNode.getRow() + 1][randNode.getCol()] = true;
            addWalls(randNode.getRow() + 1, randNode.getCol());
        }

    }

    /**
     * If wall is in the West, make the (random) wall a passage and mark the unvisited cell as part of the maze.
     *
     * @param randNode The random wall node.
     */
    private void breakWallWest(Node randNode) {
        if (randNode.getCol() - 1 >= 0 && !marked[randNode.getRow()][randNode.getCol() - 1]) {
            this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
            this.maze.maze[randNode.getRow()][randNode.getCol() - 1].setWall(false);
            this.maze.update();
            marked[randNode.getRow()][randNode.getCol() - 1] = true;
            addWalls(randNode.getRow(), randNode.getCol() - 1);
        } else if (randNode.getCol() + 1 < Maze.MAX_WIDTH && !marked[randNode.getRow()][randNode.getCol() + 1]) {
            this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
            this.maze.maze[randNode.getRow()][randNode.getCol() + 1].setWall(false);
            this.maze.update();
            marked[randNode.getRow()][randNode.getCol() + 1] = true;
            addWalls(randNode.getRow(), randNode.getCol() + 1);
        }
    }

    /**
     * If wall is in the East, make the (random) wall a passage and mark the unvisited cell as part of the maze.
     *
     * @param randNode The random wall node.
     */
    private void breakWallEast(Node randNode) {
        if (randNode.getCol() - 1 >= 0 && !marked[randNode.getRow()][randNode.getCol() - 1]) {
            this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
            this.maze.maze[randNode.getRow()][randNode.getCol() - 1].setWall(false);
            this.maze.update();
            marked[randNode.getRow()][randNode.getCol() - 1] = true;
            addWalls(randNode.getRow(), randNode.getCol() - 1);
        } else if (randNode.getCol() + 1 < Maze.MAX_WIDTH && !marked[randNode.getRow()][randNode.getCol() + 1]) {
            this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
            this.maze.maze[randNode.getRow()][randNode.getCol() + 1].setWall(false);
            this.maze.update();
            marked[randNode.getRow()][randNode.getCol() + 1] = true;
            addWalls(randNode.getRow(), randNode.getCol() + 1);
        }
    }
}