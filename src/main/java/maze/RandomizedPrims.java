package maze;

import pathfinding.Node;

import java.util.ArrayList;
import java.util.List;

public class RandomizedPrims {

    private Maze maze;
    private Node startNode;
    private Node endNode;
    private List<Node> listOfWalls;

    public RandomizedPrims(Maze maze, Node startNode, Node endNode) {
        this.maze = maze;
        this.startNode = startNode;
        this.endNode = endNode;
        listOfWalls = new ArrayList<>();
    }
}
