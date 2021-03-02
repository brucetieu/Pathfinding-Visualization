package pathfinding;

import maze.Maze;

import java.util.*;

/**
 * Depth first search algorithm to find a path from start to end node.
 */
public class DepthFirstSearch {

    private Maze maze;
    private Node startNode;
    private Node endNode;
    private Node[][] edgeTo;
    private Stack<Node> path;

    /**
     * Initialize variables for the depth first search
     * @param maze The maze. It's important to pass the maze object in to gain access of the update() method for repainting the maze again.
     * @param startNode The starting position to begin the search.
     * @param endNode The destination node.
     */
    public DepthFirstSearch(Maze maze, Node startNode, Node endNode) {
        this.maze = maze;
        this.startNode = startNode;
        this.endNode = endNode;
        this.edgeTo = new Node[Maze.MAX_HEIGHT][Maze.MAX_WIDTH];
        this.path = new Stack<>();
        dfs();
    }

    /**
     * Return any single path to get to the end node from the starting node.
     * @param endNode The destination node
     * @return A list containing the path (in this case a stack).
     */
    public Iterable<Node> pathTo(Node endNode) {

        for (Node x = endNode; !x.equals(this.startNode); x = edgeTo[x.getRow()][x.getCol()]) {
            this.maze.maze[x.getRow()][x.getCol()].setPath(true);
            this.maze.update(25);
            this.path.push(x);
        }
        this.path.push(this.startNode);
        return this.path;
    }


    /**
     * Perform depth first search.
     * @return An iterable containing the path, if it exists.
     */
    private Iterable<Node> dfs() {

        Stack<Node> stack = new Stack<>();

        maze.maze[this.startNode.getRow()][this.startNode.getCol()].setVisited(true);
        stack.push(this.startNode);

        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();

            if (currentNode.equals(this.endNode)) {

                // Get the path we took to get to the end node. Then draw that path.
                return pathTo(this.endNode);
            }

            this.maze.maze[currentNode.getRow()][currentNode.getCol()].setVisited(true);

            // Push adjacent unvisited nodes to the stack.
            for (Node node : findAdjacent(currentNode.getRow(), currentNode.getCol())) {
                if (!this.maze.maze[node.getRow()][node.getCol()].isVisited()) {
                    this.edgeTo[node.getRow()][node.getCol()] = currentNode;
                    this.maze.update(15);
                    stack.push(node);
                }
            }
        }
        return null;
    }


    /**
     * Find adjacent open nodes to the current row, col.
     * @param row The row of node in the maze.
     * @param col The col of node in the maze.
     * @return A list of all adjacent nodes.
     */
    private List<Node> findAdjacent(int row, int col) {
        List<Node> adjNeighbors = new ArrayList<>();

        // Top
        if (row-1 >= 0 && !this.maze.maze[row-1][col].isWall()) {
            adjNeighbors.add(this.maze.maze[row-1][col]);
        }
        // Bottom
        if (row+1 < Maze.MAX_HEIGHT && !this.maze.maze[row+1][col].isWall()) {
            adjNeighbors.add(this.maze.maze[row+1][col]);
        }
        // Left
        if (col-1 >= 0 && !this.maze.maze[row][col-1].isWall()) {
            adjNeighbors.add(this.maze.maze[row][col-1]);
        }
        // Right
        if (col+1 < Maze.MAX_WIDTH && !this.maze.maze[row][col+1].isWall()) {
            adjNeighbors.add(this.maze.maze[row][col+1]);
        }

        return adjNeighbors;

    }

}
