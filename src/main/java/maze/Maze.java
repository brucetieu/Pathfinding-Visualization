package maze;

import pathfinding.Node;
import utils.Delay;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Graphics;
import java.awt.Color;


/**
 * Create a maze. Extend JPanel so that we can use paintComponent to paint the maze.
 */
public class Maze extends JPanel implements ChangeListener {

    public static final int MAX_HEIGHT = 31;
    public static final int MAX_WIDTH = 38;
    public Node[][] maze = new Node[MAX_HEIGHT][MAX_WIDTH];
    private final int RECT_SIZE = 25;

    private int animationSpeed = 50; // set an initial animation speed.

    /**
     * Initialize a fully walled maze.
     */
    public Maze() {

        for (int row = 0; row < MAX_HEIGHT; row++) {
            for (int col = 0; col < MAX_WIDTH; col++) {
                maze[row][col] = new Node(row, col, true, false, false, false, false);
            }
        }
    }

    /**
     * Reset the maze to be fully walled with starting and ending nodes.
     * @param startNode Starting position.
     * @param endNode Ending position.
     */
    public void resetMaze(Node startNode, Node endNode) {
        for (int row = 0; row < MAX_HEIGHT; row++) {
            for (int col = 0; col < MAX_WIDTH; col++) {

                // All paths are closed.
                maze[row][col] = new Node(row, col, true, false, false, false, false);
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

    /**
     * Everytime we instantiate Maze, the maze will be painted accordingly to the colors set below.
     * @param g Graphics parameter to draw and set colors.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < MAX_HEIGHT; row++) {
            for (int col = 0; col < MAX_WIDTH; col++) {

                // Set the colors according to each node in the maze. E.g if a node has been visited it's colored blue. When I call the update() method below,
                // The maze will repaint according to the following configurations.
                if (maze[row][col].isPath() && maze[row][col].isVisited() && !maze[row][col].isWall())
                    g.setColor(Color.magenta);
                else if (maze[row][col].isVisited() && !maze[row][col].isWall() && !maze[row][col].isStart())
                    g.setColor(Color.blue);
                else if (maze[row][col].isStart() && !maze[row][col].isWall()) g.setColor(Color.green);
                else if (maze[row][col].isEnd() && !maze[row][col].isWall()) g.setColor(Color.red);
                else if (!maze[row][col].isWall()) g.setColor(Color.white);
                else if (maze[row][col].isWall()) g.setColor(Color.black);

                // Multiply RECT_SIZE by row and col to place it correctly on the maze.
                g.fillRect(RECT_SIZE * col, RECT_SIZE * row, RECT_SIZE, RECT_SIZE);

                // Draw rectangle's borders.
                g.setColor(Color.BLACK);
                g.drawRect(RECT_SIZE * col, RECT_SIZE * row, RECT_SIZE, RECT_SIZE);
            }
        }

    }

    /**
     * Need this method to repaint the maze in other classes. The idea is I pass in a Maze object to the other class so that I can have access to this method
     * and can repaint whenever I need to via maze.update(). Invoking repaint() calls the paintComponent().
     */
    public void update() {
        Visualization.speedSlider.addChangeListener(this);
        Delay.delay(animationSpeed);
        repaint();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        animationSpeed = (int) source.getValue();
    }
}
