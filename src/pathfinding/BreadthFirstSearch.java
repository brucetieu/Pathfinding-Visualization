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

    public BreadthFirstSearch(Maze maze, Node startNode, Node endNode) {
        this.maze = maze;
        this.startNode = startNode;
        this.endNode = endNode;
        this.edgeTo = new Node[Maze.MAX_HEIGHT][Maze.MAX_WIDTH];
        this.path = new Stack<>();
        bfs();
    }

    public Iterable<Node> pathTo(Node endNode) {

        for (Node x = endNode; !x.equals(this.startNode); x = edgeTo[x.getRow()][x.getCol()]) {
            this.maze.maze[x.getRow()][x.getCol()].setPath(true);
            Delay.delay(10);
            this.maze.update();
            this.path.push(x);
        }
        this.maze.maze[endNode.getRow()][endNode.getCol()].setPath(false);
        this.maze.maze[endNode.getRow()][endNode.getCol()].setEnd(true);
        this.maze.maze[endNode.getRow()][endNode.getCol()].setVisited(false);
        this.maze.update();
        this.path.push(this.startNode);
        return this.path;
    }

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

            findAdjacent(row, col, queue, currentNode);

            Delay.delay(5);
            maze.update();

        }
        return null;
    }

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
