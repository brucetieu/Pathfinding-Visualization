package maze;

import pathfinding.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Create a maze
public class Maze extends JPanel {

    public static final int MAX_HEIGHT = 31;
    public static final int MAX_WIDTH = 38;

    private Node[][] maze = new Node[MAX_HEIGHT][MAX_WIDTH];

    private final int START_X = 0;
    private final int START_Y = 0;
    private final int RECT_SIZE = 25;

    public Maze() {

        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                Node node = new Node(row, col, true, false, false, false, false);
                maze[row][col] = node; // All paths are closed.
            }
        }
    }

    public Node[][] getMaze() {
        return maze;
    }

    public void resetMaze(Node startNode, Node endNode) {
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                Node node = new Node(row, col, true, false, false, false, false);
                maze[row][col] = node; // All paths are closed.
            }
        }

        // Set the start and end nodes in the maze.
        int r1 = startNode.getRow();
        int c1 = startNode.getCol();
        int r2 = endNode.getRow();
        int c2 = endNode.getCol();

        maze[r1][c1].setStart(true);
        maze[r1][c1].setWall(false);
        maze[r2][c2].setEnd(true);
        maze[r2][c2].setWall(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {

                if (maze[row][col].isPath() && maze[row][col].isVisited() && !maze[row][col].isWall())
                    g.setColor(Color.magenta);
                else if (maze[row][col].isVisited() && !maze[row][col].isWall() && !maze[row][col].isStart())
                    g.setColor(Color.blue);
                else if (maze[row][col].isStart() && !maze[row][col].isWall()) g.setColor(Color.green);
                else if (maze[row][col].isEnd() && !maze[row][col].isWall()) g.setColor(Color.red);
                else if (!maze[row][col].isWall()) g.setColor(Color.white);
                else if (maze[row][col].isWall()) g.setColor(Color.black);

                // Fill rect according to what each node is
                g.fillRect(RECT_SIZE * col + START_X, RECT_SIZE * row + START_Y, RECT_SIZE, RECT_SIZE);

                // Draw rectangle borders
                g.setColor(Color.BLACK);
                g.drawRect(RECT_SIZE * col + START_X, RECT_SIZE * row + START_Y, RECT_SIZE, RECT_SIZE);
            }
        }

    }


    public void update() {
        repaint();
    }

}
