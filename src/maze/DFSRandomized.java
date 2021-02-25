package maze;

import pathfinding.Node;

import javax.swing.JPanel;

public class DFSRandomized extends JPanel {

    private Node[][] maze;

    private int height;
    private int width;

    public DFSRandomized(Node[][] maze) {
        this.maze = maze;
        width = this.maze[0].length;
        height = this.maze.length;

    }
}
