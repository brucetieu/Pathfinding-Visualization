package gui;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    //Get the screen size
    Toolkit toolkit;
    Dimension screenSize;

    public Frame() {
        toolkit = Toolkit.getDefaultToolkit();
        screenSize = toolkit.getScreenSize();

        this.setTitle("Pathfinding Visualizer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 700);
        this.setVisible(true);

        //Calculate the frame location
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;

        //Set the new frame location
        this.setLocation(x, y);

    }

    public static void main(String[] args) {
        new Frame();
    }

}
