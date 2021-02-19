package gui;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{

    Grid grid = new Grid();

    public Frame() {

        this.setTitle("Pathfinding Visualizer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(650, 650);
        this.add(grid);
        this.setVisible(true);

    }

    public static void main (String[] args) {
        new Frame();
    }


}
