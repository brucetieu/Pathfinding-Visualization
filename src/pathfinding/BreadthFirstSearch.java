package pathfinding;

import maze.Maze;
import utils.Delay;
import utils.SeparateThread;

import javax.swing.*;
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

//            Delay.delay(3);
//            maze.update();
            if (row-1 >= 0 && !this.maze.maze[row - 1][col].isWall()) {
                if (!this.maze.maze[row - 1][col].isVisited()) {
                    this.maze.maze[row - 1][col].setVisited(true);
//                    Delay.delay(3);
//                    maze.update();
                    queue.add(this.maze.maze[row - 1][col]);
                    this.edgeTo[row - 1][col] = currentNode;
                }
            }
            if (row+1 < Maze.MAX_HEIGHT && !this.maze.maze[row + 1][col].isWall()) {
                if (!this.maze.maze[row + 1][col].isVisited()) {
                    this.maze.maze[row + 1][col].setVisited(true);
//                    Delay.delay(3);
//                    maze.update();
                    queue.add(this.maze.maze[row + 1][col]);
                    this.edgeTo[row + 1][col] = currentNode;
                }
            }
            if (col-1 >= 0 && !this.maze.maze[row][col - 1].isWall()) {
                if (!this.maze.maze[row][col - 1].isVisited()) {
                    this.maze.maze[row][col - 1].setVisited(true);
//                    Delay.delay(3);
//                    maze.update();
                    queue.add(this.maze.maze[row][col - 1]);
                    this.edgeTo[row][col - 1] = currentNode;
                }
            }
            if (col+1 < Maze.MAX_WIDTH && !this.maze.maze[row][col + 1].isWall()) {
                if (!this.maze.maze[row][col + 1].isVisited()) {
                    this.maze.maze[row][col + 1].setVisited(true);
//                    Delay.delay(3);
//                    maze.update();
                    queue.add(this.maze.maze[row][col + 1]);
                    this.edgeTo[row][col + 1] = currentNode;
                }
            }

            Delay.delay(5);
            maze.update();

//            for (Node node : findAdjacent(currentNode.getRow(), currentNode.getCol())) {
//                if (!maze.maze[node.getRow()][node.getCol()].isVisited()) {
//                    this.edgeTo[node.getRow()][node.getCol()] = currentNode;
//                    this.maze.maze[currentNode.getRow()][currentNode.getCol()].setVisited(true);
//                    Delay.delay(5);
//                    maze.update();
//                    queue.add(node);
//                }
//            }
        }
        return null;
    }

    private List<Node> findAdjacent(int row, int col) {
        List<Node> adjNeighbors = new ArrayList<>();

        // Top
        if (row - 1 >= 0 && !this.maze.maze[row - 1][col].isWall()) {
            adjNeighbors.add(this.maze.maze[row - 1][col]);
        }
        // Bottom
        if (row + 1 < Maze.MAX_HEIGHT && !this.maze.maze[row + 1][col].isWall()) {
            adjNeighbors.add(this.maze.maze[row + 1][col]);
        }
        // Left
        if (col - 1 >= 0 && !this.maze.maze[row][col - 1].isWall()) {
            adjNeighbors.add(this.maze.maze[row][col - 1]);
        }
        // Right
        if (col + 1 < Maze.MAX_WIDTH && !this.maze.maze[row][col + 1].isWall()) {
            adjNeighbors.add(this.maze.maze[row][col + 1]);
        }

        return adjNeighbors;

    }
}
