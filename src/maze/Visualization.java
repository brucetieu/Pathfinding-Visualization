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

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


// Visualizing the maze
public class Visualization extends JPanel {

    private Maze maze;

    private JPanel mainPanel;
    private JPanel controllerPanel;
    private JButton generateMazeBtn;

    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    private Node startNode;
    private Node endNode;

    private final String[] mazeGenerationOptions = {"Random DFS", "Open Maze", "Recursive Division", "Prim's", "Kruskal's"};
    private final String[] pathFindingOptions = {"Depth First Search", "Breadth First Search"};


    public Visualization() {

        JFrame jFrame = new JFrame();

        mainPanel = new JPanel();
        controllerPanel = new JPanel();
        mainPanel.setLayout(new GridLayout());
        mainPanel.setBackground(Color.white);
        controllerPanel.setLayout(new FlowLayout());
        controllerPanel.setPreferredSize(new Dimension(250, this.getHeight()));
        controllerPanel.setBackground(Color.lightGray);

        JLabel mazeOptionsLabel = new JLabel("Choose a Maze Generation method");
        JComboBox mazeOptionsComboBox = new JComboBox(mazeGenerationOptions);
        JLabel pathFindingOptionsLabel = new JLabel("Choose a Path Finding method");
        JComboBox pathFindingComboBox = new JComboBox(pathFindingOptions);


        JButton solveMazeBtn = new JButton("Solve Maze");
        generateMazeBtn = new JButton("Generate Maze");

        maze = new Maze();


        generateMazeBtn.addActionListener(e -> {
            startNode = MazeUtils.generateStartNode();
            endNode = MazeUtils.generateEndNode();

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


        controllerPanel.add(mazeOptionsLabel);
        controllerPanel.add(mazeOptionsComboBox);
        controllerPanel.add(generateMazeBtn);
        controllerPanel.add(pathFindingOptionsLabel);
        controllerPanel.add(pathFindingComboBox);
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



