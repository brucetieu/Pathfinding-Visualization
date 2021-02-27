package maze;

import pathfinding.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Create a maze
public class Maze extends JPanel {

    private final int MAX_HEIGHT = 31;
    private final int MAX_WIDTH = 38;
    private final Node[][] maze = new Node[MAX_HEIGHT][MAX_WIDTH];

    private final int START_X = 0;
    private final int START_Y = 0;
    private final int RECT_SIZE = 25;


    public Maze() {
        resetMaze();
    }

    public Node[][] getMaze() {
        return maze;
    }

    public void resetMaze() {
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                Node node = new Node(row, col, true, false, false, false, false);
                maze[row][col] = node; // All paths are closed.
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {

                if (maze[row][col].isPath() && maze[row][col].isVisited() && !maze[row][col].isWall()) g.setColor(Color.magenta);
                else if (maze[row][col].isVisited() && !maze[row][col].isWall() && !maze[row][col].isStart()) g.setColor(Color.blue);
                else if (maze[row][col].isStart() && !maze[row][col].isWall()) g.setColor(Color.green);
                else if (maze[row][col].isEnd() && !maze[row][col].isWall()) g.setColor(Color.red);
                else if (!maze[row][col].isWall())  g.setColor(Color.white);
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
