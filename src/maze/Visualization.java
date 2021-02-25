package maze;

import javax.swing.*;
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

    private final String[] mazeGenerationOptions = {"Random DFS", "Recursive Division", "Prim's", "Kruskal's"};


    public Visualization() {

        maze = new Maze();

        mainPanel = new JPanel();
        controllerPanel = new JPanel();
        mainPanel.setLayout(new GridLayout());
        mainPanel.setBackground(Color.white);
        controllerPanel.setLayout(new FlowLayout());
        controllerPanel.setPreferredSize(new Dimension(250, this.getHeight()));
        controllerPanel.setBackground(Color.lightGray);

        JLabel mazeOptionsLabel = new JLabel("Choose a Maze Generation method");
        JComboBox mazeOptionsComboBox = new JComboBox(mazeGenerationOptions);

        generateMazeBtn = new JButton("Generate Maze");
        mainPanel.add(maze);

        generateMazeBtn.addActionListener(e -> {
            int selectedIndex = mazeOptionsComboBox.getSelectedIndex();
            if (selectedIndex == 0) {
                RandomizedDFS randomizedDFS = new RandomizedDFS(maze.getMaze());
                maze.resetMaze();
                randomizedDFS.generateMaze();
                repaint();
            }
        });

        controllerPanel.add(mazeOptionsLabel);
        controllerPanel.add(mazeOptionsComboBox);
        controllerPanel.add(generateMazeBtn);



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
                else if (maze.getMaze()[row][col].isEnd() && !maze.getMaze()[row][col].isWall()) g.setColor(Color.red);
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
