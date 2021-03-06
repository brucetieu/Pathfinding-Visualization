package maze;

import pathfinding.BreadthFirstSearch;
import pathfinding.DepthFirstSearch;
import pathfinding.Node;
import utils.MazeUtils;

import javax.swing.*;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;



/**
 * Visualizing the maze. Extend JPanel
 */
public class Visualization extends JPanel {

    private Maze maze;

    private JFrame jFrame;
    private JPanel mainPanel;
    private JPanel controllerPanel;
    private JButton generateMazeBtn;
    private JLabel mazeOptionsLabel;
    private JComboBox mazeOptionsComboBox;
    private JLabel pathFindingOptionsLabel;
    private JComboBox pathFindingComboBox;
    private JLabel changeSpeedLabel;
    private JButton solveMazeBtn;

    private Node startNode;
    private Node endNode;

    private final String[] mazeGenerationOptions = {"Random DFS", "Open Maze", "Prim's","Recursive Division", "Kruskal's"};
    private final String[] pathFindingOptions = {"Depth First Search", "Breadth First Search"};

    public static JSlider speedSlider;

    public Visualization() {
        initComponents();
        performActions();
    }

    /**
     * Add actions to the buttons.
     */
    private void performActions() {
        generateMazeBtn.addActionListener(e -> {
            startNode = MazeUtils.generateStartNode();
            endNode = MazeUtils.generateEndNode();

            // Since Swing is single threaded, using a SwingWorker allows us to to perform multiple lengthy GUI-interaction tasks in a background thread.
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    int selectedIndex = mazeOptionsComboBox.getSelectedIndex();
                    if (selectedIndex == 0) {
                        maze.resetMaze(startNode, endNode);
                        RandomizedDFS randomizedDFS = new RandomizedDFS(maze, startNode, endNode);
                        randomizedDFS.generateMaze();

                    }
                    else if (selectedIndex == 1) {
                        OpenMaze openMaze = new OpenMaze(maze);
                        maze.resetMaze(startNode, endNode);
                        openMaze.generateMaze();
                    }
                    else if (selectedIndex == 2) {
                        maze.resetMaze(startNode, endNode);
                        RandomizedPrims randomizedPrims = new RandomizedPrims(maze, startNode, endNode);
                        randomizedPrims.generateMaze();
                    }
                    return null;
                }
            };
            worker.execute();
        });

        solveMazeBtn.addActionListener(e -> {
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    int selectedIndex = pathFindingComboBox.getSelectedIndex();
                    if (selectedIndex == 0) {
                        DepthFirstSearch dfs = new DepthFirstSearch(maze, startNode, endNode);
                    }
                    else if (selectedIndex == 1) {
                        System.out.println("Bfs selected");
                        BreadthFirstSearch bfs = new BreadthFirstSearch(maze, startNode, endNode);
                    }
                    return null;
                }
            };
            worker.execute();
        });
    }

    /**
     * Initialize all the Swing components.
     */
    private void initComponents() {
        jFrame = new JFrame();

        mainPanel = new JPanel();
        controllerPanel = new JPanel();
        mainPanel.setLayout(new GridLayout());
        mainPanel.setBackground(Color.white);
        controllerPanel.setPreferredSize(new Dimension(250, this.getHeight()));
        controllerPanel.setBackground(Color.lightGray);

        mazeOptionsLabel = new JLabel("Choose a Maze Generation method");
        mazeOptionsComboBox = new JComboBox(mazeGenerationOptions);
        pathFindingOptionsLabel = new JLabel("Choose a Path Finding method");
        pathFindingComboBox = new JComboBox(pathFindingOptions);
        changeSpeedLabel = new JLabel("Change animation speed");
        speedSlider = new JSlider();

        solveMazeBtn = new JButton("Solve Maze");
        generateMazeBtn = new JButton("Generate Maze");

        maze = new Maze();

        controllerPanel.add(mazeOptionsLabel);
        controllerPanel.add(mazeOptionsComboBox);
        controllerPanel.add(generateMazeBtn);
        controllerPanel.add(pathFindingOptionsLabel);
        controllerPanel.add(pathFindingComboBox);
        controllerPanel.add(changeSpeedLabel);
        controllerPanel.add(speedSlider);
        controllerPanel.add(solveMazeBtn);

        mainPanel.add(maze);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(mainPanel);

        jFrame.add(controllerPanel, BorderLayout.WEST);
        jFrame.setSize(1201, 796);
        jFrame.setResizable(false);
        jFrame.setTitle("Pathfinding Visualizer");
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}



