package maze;

import pathfinding.Node;

import javax.swing.JPanel;

// Create a maze
public class Maze extends JPanel{

    private final Node[][] maze = new Node[31][38];

    public Maze() {
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                Node node = new Node(row, col, true, false, false);
                maze[row][col] = node; // All paths are open
            }
        }
    }

    public Node[][] getMaze() {
        return maze;
    }

    public void resetMaze() {
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                Node node = new Node(row, col, true, false, false);
                maze[row][col] = node; // All paths are open
            }
        }
    }
}
