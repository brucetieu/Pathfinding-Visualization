import javax.swing.*;
import java.awt.*;


public class MyFrame extends JFrame {
    public MyFrame() {

        this.setTitle("Pathfinding Visualizer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(420, 420);
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(255,255,255));
    }
}
