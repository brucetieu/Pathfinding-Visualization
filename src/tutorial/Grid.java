package tutorial;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Grid {

    public static void main(String[] args) {

        ImageIcon image = new ImageIcon("code-flat.png");
        Border border = BorderFactory.createLineBorder(Color.green, 3);
        JLabel label = new JLabel();
        label.setText("Bro"); // set text of label
        label.setIcon(image);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP); // Set text to TOP,CENTER,BOTTOM of icon
        label.setForeground(Color.blue);
        label.setBorder(border);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER); // set horizontal position of icon+text within label

//        label.setBounds(100, 0, 250, 250); // (0,0) is top left, set position within frame and dim



        JFrame frame = new JFrame();
        frame.setTitle("Pathfinding Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
//        frame.setLayout(null);
        frame.setVisible(true);
        frame.add(label);
        frame.pack(); // resize the frame to fit all the components that you have
    }

}
