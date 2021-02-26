package pathfinding;

import utils.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch {

    private Node[][] maze;
//    private boolean[][] visited;  // Maintain a 2d boolean array to mark which nodes have been visited.

    private int height;
    private int width;
    private Node startNode;
    private Node endNode;

    private Node[][] edgeTo;
    Stack<Node> path;


    public DepthFirstSearch(Node[][] maze, Node startNode, Node endNode) {
        this.maze = maze;
        this.width = this.maze[0].length;
        this.height = this.maze.length;

        this.startNode = startNode;
        this.endNode = endNode;

        this.edgeTo = new Node[height][width];

        this.path = new Stack<>();

        dfs(this.startNode.getRow(), this.startNode.getCol());
    }


    public Iterable<Node> pathTo(Node endNode) {
        if (!hasPathTo(endNode)) return null;

        for (Node x = endNode; !x.equals(this.startNode); x = edgeTo[x.getRow()][x.getCol()])
            this.path.push(x);
        this.path.push(this.startNode);
        return this.path;
    }

    public boolean hasPathTo(Node endNode) {
        return this.maze[endNode.getRow()][endNode.getCol()].isVisited();
    }

    private void dfs(int row, int col) {
        this.maze[row][col].setVisited(true);

        for (Node node : findAdjacent(row, col)) {
            if (!this.maze[node.getRow()][node.getCol()].isVisited()) {
                this.edgeTo[node.getRow()][node.getCol()] = new Node(row, col);
                dfs(node.getRow(), node.getCol());
            }
        }
    }

    public Stack<Node> getPath() {
        return this.path;
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
        if (row-1 >= 0 && !this.maze[row-1][col].isWall()) {
            adjNeighbors.add(new Node(row-1, col, false, false, false, false));
        }
        // Bottom
        if (row+1 < height && !this.maze[row+1][col].isWall()) {
            adjNeighbors.add(new Node(row+1, col, false, false, false, false));
        }
        // Left
        if (col-1 >= 0 && !this.maze[row][col-1].isWall()) {
            adjNeighbors.add(new Node(row, col-1, false, false, false, false));
        }
        // Right
        if (col+1 < width && !this.maze[row][col+1].isWall()) {
            adjNeighbors.add(new Node(row, col+1, false, false, false, false));
        }

        return adjNeighbors;

    }


}
