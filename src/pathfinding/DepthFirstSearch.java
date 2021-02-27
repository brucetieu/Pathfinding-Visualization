package pathfinding;

import maze.Maze;
import utils.Delay;

import java.util.*;

public class DepthFirstSearch {

    private Maze maze;
//    private boolean[][] visited;  // Maintain a 2d boolean array to mark which nodes have been visited.

    private int height;
    private int width;
    private Node startNode;
    private Node endNode;

    private Node[][] edgeTo;
//    List<List<Node>> paths;
    private Stack<Node> path;


    public DepthFirstSearch(Maze maze, Node startNode, Node endNode) {
        this.maze = maze;
        this.width = this.maze.getMaze()[0].length;
        this.height = this.maze.getMaze().length;

        this.startNode = startNode;
        this.endNode = endNode;

        this.edgeTo = new Node[height][width];

        this.path = new Stack<>();


        dfs();
//        dfs(this.startNode.getRow() + 1, this.startNode.getCol() + 1);
    }



    public Iterable<Node> pathTo(Node endNode) {
//        if (!hasPathTo(endNode)) return null;

        for (Node x = endNode; !x.equals(this.startNode); x = edgeTo[x.getRow()][x.getCol()]) {
            System.out.println(x.getRow() + " " + x.getCol());
//            x.setPath(true);
            this.maze.getMaze()[x.getRow()][x.getCol()].setPath(true);
            Delay.delay(5);
            this.maze.update();
            this.path.push(x);
        }
        this.path.push(this.startNode);
        return this.path;
    }

    public boolean hasPathTo(Node endNode) {
        return this.maze.getMaze()[endNode.getRow()][endNode.getCol()].isVisited();
    }

    private Iterable<Node> dfs() {
        Stack<Node> stack = new Stack<>();

        stack.push(this.startNode);

        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();

            if (currentNode.equals(this.endNode)) {

                // TODO: Get the path we took to get to the end node. Then draw that path.
                return pathTo(this.endNode);
            }

            this.maze.getMaze()[currentNode.getRow()][currentNode.getCol()].setVisited(true);

            for (Node node : findAdjacent(currentNode.getRow(), currentNode.getCol())) {
                if (!this.maze.getMaze()[node.getRow()][node.getCol()].isVisited()) {
                    this.edgeTo[node.getRow()][node.getCol()] = currentNode;
                    this.maze.update();
                    Delay.delay(15);
                    stack.push(node);
                }
            }
        }
        return null;
    }

//    private void dfs(int row, int col) {
//
//        if (this.maze.getMaze()[row][col].isStart()) return;
//        if (this.maze.getMaze()[row][col].isEnd()) {
//
//            return;
//        }
//
//
//        this.maze.getMaze()[row][col].setVisited(true);
//
//        for (Node node : findAdjacent(row, col)) {
//            if (!this.maze.getMaze()[node.getRow()][node.getCol()].isVisited()) {
//                this.edgeTo[node.getRow()][node.getCol()] = new Node(row, col);
//                Delay.delay(1);
//                this.maze.update();
//                dfs(node.getRow(), node.getCol());
//            }
//        }
//    }

//    public Stack<Node> getPath() {
//        return this.path;
//    }

    /**
     * Find the adjacent unvisited nodes given a current node.
     * @param row The row position of this adjacent unvisited node.
     * @param col The col position of this adjacent unvisited node.
     * @return A list of adjacent nodes.
     */
    private List<Node> findAdjacent(int row, int col) {
        List<Node> adjNeighbors = new ArrayList<>();

        // Top
        if (row-1 >= 0 && !this.maze.getMaze()[row-1][col].isWall()) {
            adjNeighbors.add(new Node(row-1, col, false, false, false, false, false));
        }
        // Bottom
        if (row+1 < height && !this.maze.getMaze()[row+1][col].isWall()) {
            adjNeighbors.add(new Node(row+1, col, false, false, false, false, false));
        }
        // Left
        if (col-1 >= 0 && !this.maze.getMaze()[row][col-1].isWall()) {
            adjNeighbors.add(new Node(row, col-1, false, false, false, false, false));
        }
        // Right
        if (col+1 < width && !this.maze.getMaze()[row][col+1].isWall()) {
            adjNeighbors.add(new Node(row, col+1, false, false, false, false, false));
        }

        return adjNeighbors;

    }

}
