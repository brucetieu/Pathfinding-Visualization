package maze;

import pathfinding.Node;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Visualizing the maze
public class Visualization extends JFrame {

    private Maze maze;
    private final int START_X = 250;
    private final int START_Y = 20;
    private final int RECT_SIZE = 25;
    private JPanel mainPanel;
    private JPanel controllerPanel;
    private JButton generateMazeBtn;


    public Visualization() {

        maze = new Maze();

        mainPanel = new JPanel();
        controllerPanel = new JPanel();
        mainPanel.setLayout(new GridLayout());
        mainPanel.setBackground(Color.white);
        controllerPanel.setLayout(new FlowLayout());
        controllerPanel.setPreferredSize(new Dimension(250, this.getHeight()));
        controllerPanel.setBackground(Color.lightGray);

        generateMazeBtn = new JButton("Generate Maze");
        mainPanel.add(maze);
        controllerPanel.add(generateMazeBtn);

        generateMazeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DFSRandomized dfsRandomized = new DFSRandomized(maze.getMaze());
                maze.resetMaze();
                dfsRandomized.generateMaze();
                repaint();
            }
    });



        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(mainPanel, BorderLayout.CENTER);

        this.add(controllerPanel, BorderLayout.WEST);
        this.setSize(1201, 796);
        this.setResizable(false);
        this.setTitle("Pathfinding Visualizer");
        this.setLocationRelativeTo(null);
        this.setVisible(true);


    }

    public void paint(Graphics g) {
        super.paint(g);

        for (int row = 0; row < maze.getMaze().length; row++) {
            for (int col = 0; col < maze.getMaze()[0].length; col++) {

                if (maze.getMaze()[row][col].isStart() && !maze.getMaze()[row][col].isWall()) g.setColor(Color.green);
                else if (!maze.getMaze()[row][col].isWall())  g.setColor(Color.white);
                else if (maze.getMaze()[row][col].isWall()) g.setColor(Color.black);


                // Fill rect according to what each node is
                g.fillRect(RECT_SIZE * col + START_X, RECT_SIZE * row + START_Y, RECT_SIZE, RECT_SIZE);

                // Draw rectangle borders
                g.setColor(Color.BLACK);
                g.drawRect(RECT_SIZE * col + START_X, RECT_SIZE * row + START_Y, RECT_SIZE, RECT_SIZE);

            }
        }


    }


}
