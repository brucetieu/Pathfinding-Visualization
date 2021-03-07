package pathfinding;

import maze.Maze;
import utils.Delay;

import java.util.*;

public class BreadthFirstSearch {

    private Maze maze;
    private Node startNode;
    private Node endNode;
    private Node[][] edgeTo;
    private Stack<Node> path;

    /**
     * Parametrized constructor, initialize the maze and start and end nodes.
     * @param maze The maze.
     * @param startNode The starting node.
     * @param endNode The ending node.
     */
    public BreadthFirstSearch(Maze maze, Node startNode, Node endNode) {
        this.maze = maze;
        this.startNode = startNode;
        this.endNode = endNode;
        this.edgeTo = new Node[Maze.MAX_HEIGHT][Maze.MAX_WIDTH];
        this.path = new Stack<>();
        bfs();
    }

    /**
     * Get any path from startNode to endNode.
     * @param endNode The destination node.
     * @return An iterable which contains the path from end to source.
     */
    public Iterable<Node> pathTo(Node endNode) {

        // We are adding the paths from the end to the start. Since it's a stack, the path is in reverse order.
        for (Node x = endNode; !x.equals(this.startNode); x = edgeTo[x.getRow()][x.getCol()]) {
            this.maze.maze[x.getRow()][x.getCol()].setPath(true);
            this.maze.update();
            this.path.push(x);
        }

        // Recolor the end node to be red.
        this.maze.maze[endNode.getRow()][endNode.getCol()].setPath(false);
        this.maze.maze[endNode.getRow()][endNode.getCol()].setEnd(true);
        this.maze.maze[endNode.getRow()][endNode.getCol()].setVisited(false);
        this.maze.update();
        this.path.push(this.startNode);
        return this.path;
    }

    /**
     * Perform breadth first search on the maze to find the path.
     * @return An iterable which contains the path from start to end.
     */
    private Iterable<Node> bfs() {

        Queue<Node> queue = new LinkedList<>();

        maze.maze[this.startNode.getRow()][this.startNode.getCol()].setVisited(true);
        queue.add(this.startNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (currentNode.equals(this.endNode)) {

                // Get the path we took to get to the end node. Then draw that path.
                return pathTo(this.endNode);
            }

            int row = currentNode.getRow();
            int col = currentNode.getCol();

            // Find adjacent unvisited open nodes, add them to the queue.
            findAdjacent(row, col, queue, currentNode);

            maze.update();

        }
        return null;
    }

    /**
     * Find adjacent unvisited and open nodes to the current visited node.
     * @param row The current row in maze.
     * @param col The current col in maze.
     * @param queue Maintain a queue for bfs.
     * @param currentNode The current node.
     */
    private void findAdjacent(int row, int col, Queue<Node> queue, Node currentNode) {
        if (row-1 >= 0 && !this.maze.maze[row - 1][col].isWall()) {
            if (!this.maze.maze[row - 1][col].isVisited()) {
                this.maze.maze[row - 1][col].setVisited(true);
                queue.add(this.maze.maze[row - 1][col]);
                this.edgeTo[row - 1][col] = currentNode;
            }
        }
        if (row+1 < Maze.MAX_HEIGHT && !this.maze.maze[row + 1][col].isWall()) {
            if (!this.maze.maze[row + 1][col].isVisited()) {
                this.maze.maze[row + 1][col].setVisited(true);
                queue.add(this.maze.maze[row + 1][col]);
                this.edgeTo[row + 1][col] = currentNode;
            }
        }
        if (col-1 >= 0 && !this.maze.maze[row][col - 1].isWall()) {
            if (!this.maze.maze[row][col - 1].isVisited()) {
                this.maze.maze[row][col - 1].setVisited(true);
                queue.add(this.maze.maze[row][col - 1]);
                this.edgeTo[row][col - 1] = currentNode;
            }
        }
        if (col+1 < Maze.MAX_WIDTH && !this.maze.maze[row][col + 1].isWall()) {
            if (!this.maze.maze[row][col + 1].isVisited()) {
                this.maze.maze[row][col + 1].setVisited(true);
                queue.add(this.maze.maze[row][col + 1]);
                this.edgeTo[row][col + 1] = currentNode;
            }
        }
    }
}
