package maze;

import pathfinding.Node;
import pathfinding.Wall;
import utils.MazeUtils;

import java.util.*;

public class RandomizedPrims {

    private Maze maze;
    private Node startNode;
    private Node endNode;
    private boolean[][] marked;
    private Set<Wall> listOfWalls;
    private Random rand = new Random();

    public RandomizedPrims(Maze maze, Node startNode, Node endNode) {
        this.maze = maze;
        this.startNode = startNode;
        this.endNode = endNode;
        marked = new boolean[Maze.MAX_HEIGHT][Maze.MAX_WIDTH];
        listOfWalls = new HashSet<>();
    }

}
