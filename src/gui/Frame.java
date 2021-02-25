package gui;

import maze.MazeGeneration;
import pathfinding.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame {
    private JPanel panel;
    private JPanel controllerPanel;
    private final String[] mazeGenerationOptions = {"Random", "Prim's", "Kruskal's"};
    private JComboBox mazeGenerationOptionList;
    private JButton generateMazeBtn;

    public Frame() {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        panel = new JPanel();
        controllerPanel = new JPanel();
        panel.setLayout(new GridLayout());
        panel.setBackground(Color.white);
        controllerPanel.setLayout(new FlowLayout());
        controllerPanel.setPreferredSize(new Dimension(250, frame.getHeight()));
        controllerPanel.setBackground(Color.lightGray);

        JButton visualizeBtn = new JButton("Visualize");
        JLabel mazeLabel = new JLabel("Choose a maze creation method");
        mazeGenerationOptionList = new JComboBox(mazeGenerationOptions);
        generateMazeBtn = new JButton("Generate Maze");



        MazeGeneration maze = new MazeGeneration(1200, 800);
        panel.add(maze);

        generateMazeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = mazeGenerationOptionList.getSelectedIndex();
                System.out.println(selectedIndex);
                maze.chooseMaze(selectedIndex);
            }
        });

        controllerPanel.add(visualizeBtn);
        controllerPanel.add(mazeLabel);
        controllerPanel.add(mazeGenerationOptionList);
        controllerPanel.add(generateMazeBtn);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(controllerPanel, BorderLayout.WEST);
        frame.setSize(1200, 800);
        frame.setResizable(false);
        frame.setTitle("Pathfinding Visualizer");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }



}
