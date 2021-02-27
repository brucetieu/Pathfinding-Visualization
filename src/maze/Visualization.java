package maze;

import pathfinding.DepthFirstSearch;
import pathfinding.Node;

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
    private final int START_X = 250;
    private final int START_Y = 20;
    private final int RECT_SIZE = 25;
    private JPanel mainPanel;
    private JPanel controllerPanel;
    private JButton generateMazeBtn;

    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    private Node startNode;
    private Node endNode;

    private final String[] mazeGenerationOptions = {"Random DFS", "Recursive Division", "Prim's", "Kruskal's"};
    private final String[] pathFindingOptions = {"Depth First Search"};

    private List<Node> path = new ArrayList<>();
    private List<List<Node>> paths;


    public Visualization() {

        JFrame jFrame = new JFrame();

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
        JLabel pathFindingOptionsLabel = new JLabel("Choose a Path Finding method");
        JComboBox pathFindingComboBox = new JComboBox(pathFindingOptions);

        JButton solveMazeBtn = new JButton("Solve Maze");
        generateMazeBtn = new JButton("Generate Maze");
        mainPanel.add(maze);


        generateMazeBtn.addActionListener(e -> {
            new Thread(() -> {
                    int selectedIndex = mazeOptionsComboBox.getSelectedIndex();
                    if (selectedIndex == 0) {
                        RandomizedDFS randomizedDFS = new RandomizedDFS(maze);
                        maze.resetMaze();
                        randomizedDFS.generateMaze();
                        startNode = maze.getMaze()[randomizedDFS.getR1()][randomizedDFS.getC1()];
                        endNode = maze.getMaze()[randomizedDFS.getR2()][randomizedDFS.getC2()];
//                        startNode = new Node(randomizedDFS.getR1(), randomizedDFS.getC1());
//                        endNode = new Node(randomizedDFS.getR2(), randomizedDFS.getC2());

//                        maze.update();

                    }
            }).start();
        });


            solveMazeBtn.addActionListener(e -> {
                new Thread(() -> {
                int selectedIndex = pathFindingComboBox.getSelectedIndex();
                if (selectedIndex == 0) {
                    DepthFirstSearch dfs = new DepthFirstSearch(maze, startNode, endNode);


//                paths = dfs.pathsFromStartToEnd();


//                if (dfs.hasPathTo(endNode)) {
//                    for (Node p : dfs.pathTo(endNode)) {
//                        path.add(p);
//                    }
//                }
//                path = dfs.getPath();

//                    maze.update();

                }
                }).start();
            });


        controllerPanel.add(mazeOptionsLabel);
        controllerPanel.add(mazeOptionsComboBox);
        controllerPanel.add(generateMazeBtn);
        controllerPanel.add(pathFindingOptionsLabel);
        controllerPanel.add(pathFindingComboBox);
        controllerPanel.add(solveMazeBtn);



        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(mainPanel);

        jFrame.add(controllerPanel, BorderLayout.WEST);
        jFrame.setSize(1201, 796);
        jFrame.setResizable(false);
        jFrame.setTitle("Pathfinding Visualizer");
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (paths != null) {
            List<Node> aPath = paths.get(paths.size() - 1);

            for (Node node : aPath) {
                g.setColor(Color.magenta);
                g.fillRect(node.getRow(), node.getCol(), 25, 25);
            }
        }
//        for (Node node : path) {
//            g.setColor(Color.magenta);
//            g.fillRect(node.getRow(), node.getCol(), 25, 25);
//        }
//        for (int row = 0; row < maze.getMaze().length; row++) {
//            for (int col = 0; col < maze.getMaze()[0].length; col++) {
//
//                if (maze.getMaze()[row][col].isStart() && !maze.getMaze()[row][col].isWall()) g.setColor(Color.green);
//                else if (maze.getMaze()[row][col].isEnd() && !maze.getMaze()[row][col].isWall()) g.setColor(Color.red);
//                else if (!maze.getMaze()[row][col].isWall())  g.setColor(Color.white);
//                else if (maze.getMaze()[row][col].isWall()) g.setColor(Color.black);
//
//
//                // Fill rect according to what each node is
//                g.fillRect(RECT_SIZE * col + START_X, RECT_SIZE * row + START_Y, RECT_SIZE, RECT_SIZE);
//
//                // Draw rectangle borders
//                g.setColor(Color.BLACK);
//                g.drawRect(RECT_SIZE * col + START_X, RECT_SIZE * row + START_Y, RECT_SIZE, RECT_SIZE);
//            }
//        }


    }



}
