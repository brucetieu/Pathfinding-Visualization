package maze;

import pathfinding.DepthFirstSearch;
import pathfinding.Node;
import utils.MazeUtils;

import javax.swing.*;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


// Visualizing the maze
public class Visualization extends JPanel {

    private Maze maze;
    private MazeUtils mazeUtils;

    private final int START_X = 250;
    private final int START_Y = 20;
    private final int RECT_SIZE = 25;
    private JPanel mainPanel;
    private JPanel controllerPanel;
    private JButton generateMazeBtn;

    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    private Node startNode;
    private Node endNode;

    private final String[] mazeGenerationOptions = {"Random DFS", "Open Maze", "Recursive Division", "Prim's", "Kruskal's"};
    private final String[] pathFindingOptions = {"Depth First Search"};


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


//        mazeUtils = new MazeUtils(maze);
//        startNode = mazeUtils.generateStartNode();
//        endNode = mazeUtils.generateEndNode();
//        startNode = new Node(0,0);
//        endNode = new Node(Maze.MAX_HEIGHT-1, Maze.MAX_WIDTH-1);

        maze = new Maze();


        generateMazeBtn.addActionListener(e -> {
            startNode = mazeUtils.generateStartNode();
            endNode = mazeUtils.generateEndNode();
            new Thread(() -> {
                int selectedIndex = mazeOptionsComboBox.getSelectedIndex();
                if (selectedIndex == 0) {
                    maze.resetMaze(startNode, endNode);
                    RandomizedDFS randomizedDFS = new RandomizedDFS(maze, startNode, endNode);
                    randomizedDFS.generateMaze();
//                    startNode = mazeUtils.generateStartNode();
//                    endNode = mazeUtils.generateEndNode();

//                    startNode = maze.getMaze()[mazeUtils.getR1()][mazeUtils.getC1()];
//                    endNode = maze.getMaze()[mazeUtils.getR2()][mazeUtils.getC2()];
//                    startNode = maze.getMaze()[randomizedDFS.getR1()][randomizedDFS.getC1()];
//                    endNode = maze.getMaze()[randomizedDFS.getR2()][randomizedDFS.getC2()];

                }

                else if (selectedIndex == 1) {
                    OpenMaze openMaze = new OpenMaze(maze);
//                    maze.resetMaze();
//                    openMaze.generateMaze();
//                    startNode = maze.getMaze()[mazeUtils.getR1()][mazeUtils.getC1()];
//                    endNode = maze.getMaze()[mazeUtils.getR2()][mazeUtils.getC2()];
                }
            }).start();
        });


        solveMazeBtn.addActionListener(e -> {
            new Thread(() -> {
                int selectedIndex = pathFindingComboBox.getSelectedIndex();
                if (selectedIndex == 0) {
                    System.out.println("this workds");
                    System.out.println(startNode.getRow() + " " + startNode.getCol());
                    DepthFirstSearch dfs = new DepthFirstSearch(maze, startNode, endNode);

                }
            }).start();
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



