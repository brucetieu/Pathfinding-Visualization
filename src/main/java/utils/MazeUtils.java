package utils;

import maze.Maze;
import pathfinding.Node;

import java.util.Random;


/**
 * Static maze utility class.
 */
public class MazeUtils {

    public enum Directions {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }
    private static int r1, r2, c1, c2;
    private static Random rand = new Random();

    public static int getR1() {
        return r1;
    }

    public void setR1(int r1) {
        this.r1 = r1;
    }

    public static int getR2() {
        return r2;
    }

    public void setR2(int r2) {
        this.r2 = r2;
    }

    public static int getC1() {
        return c1;
    }

    public void setC1(int c1) {
        this.c1 = c1;
    }

    public static int getC2() {
        return c2;
    }

    public void setC2(int c2) {
        this.c2 = c2;
    }

    /**
     * Generate a start node at an odd place on the maze. Do this so that startNode != endNode.
     * @return the Node object.
     */
    public static Node generateStartNode() {
        r1 = rand.nextInt(Maze.MAX_HEIGHT);
        while (r1 % 2 == 0) r1 = rand.nextInt(Maze.MAX_HEIGHT);

        // Generate random odd col for end node.
        c1 = rand.nextInt(Maze.MAX_WIDTH);
        while (c1 % 2 == 0) c1 = rand.nextInt(Maze.MAX_WIDTH);

        return new Node(r1, c1);
    }

    /**
     * Generate an end node at an even place on the maze. Do this so that startNode != endNode.
     * @return The node object.
     */
    public static Node generateEndNode() {
        // Generate random even row for start node.
        r2 = rand.nextInt(Maze.MAX_HEIGHT);
        while (r2 % 2 != 0) r2 = rand.nextInt(Maze.MAX_HEIGHT);

        // Generate random even col for end node.
        c2 = rand.nextInt(Maze.MAX_WIDTH);
        while (c2 % 2 != 0) c2 = rand.nextInt(Maze.MAX_WIDTH);

        return new Node(r2, c2);

    }
}
