package tutorial;

import javax.swing.*;
import java.awt.*;

public class Panels {

    public static void main(String[] args) {

        ImageIcon image = new ImageIcon("thumbs-up.png");
        JLabel label = new JLabel();
        label.setText("Hi");
        label.setIcon(image);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.RIGHT);
        label.setBounds(0,0, 75, 75);


        JPanel redPanel = new JPanel(); // a gui component that functions as a container to hold other components
        redPanel.setBackground(Color.red);
        redPanel.setBounds(0, 0, 250, 250);
        redPanel.setLayout(null);

        JPanel bluePanel = new JPanel(); // a gui component that functions as a container to hold other components
        bluePanel.setBackground(Color.blue);
        bluePanel.setBounds(250, 0, 250, 250);
        bluePanel.setLayout(null);

        JPanel greenPanel = new JPanel(); // a gui component that functions as a container to hold other components
        greenPanel.setBackground(Color.green);
        greenPanel.setBounds(0, 250, 500, 250);

        // A border layout lays out a container, arranging and resizing its components to fit in five regions: north, south, east, west, and center.
        greenPanel.setLayout(new BorderLayout());

        JFrame jFrame = new JFrame();

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(null);
        jFrame.setSize(750, 750);
        jFrame.setVisible(true);
        jFrame.add(redPanel);
        redPanel.add(label);
        jFrame.add(bluePanel);
        jFrame.add(greenPanel);

    }
}
