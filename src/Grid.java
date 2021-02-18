import javax.swing.*;
import java.awt.*;

public class Grid {

    public static void main(String[] args) {

        JLabel label = new JLabel();
        label.setText("Bro"); // set text of label

        JFrame frame = new JFrame();
        frame.setTitle("Pathfinding Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(255,255,255));
        frame.add(label);
    }

}
