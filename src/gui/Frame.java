package gui;

import javax.swing.*;
import java.awt.*;

public class Frame{
    private JPanel panel;
    private JPanel controllerPanel;

    public Frame() {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        panel = new JPanel();
        controllerPanel = new JPanel();
        panel.setLayout(new GridLayout());
        panel.setBackground(Color.white);
        controllerPanel.setLayout(new FlowLayout());
        controllerPanel.setPreferredSize(new Dimension(200, frame.getHeight()));
        controllerPanel.setBackground(Color.lightGray);

        JButton visualize = new JButton("Visualize");
        JButton generateMaze = new JButton("Generate Maze");


        controllerPanel.add(visualize);
        controllerPanel.add(generateMaze);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(controllerPanel, BorderLayout.WEST);
        frame.setSize(1000, 800);
        frame.setResizable(false);
        frame.setTitle("Pathfinding Visualizer");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}
