package gui;

import javax.swing.*;
import java.awt.*;

public class Grid extends JPanel {


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        for ( int x = 0; x <= width; x += 30 ) {
            for ( int y = 0; y <= height; y += 30 ) {
                g.drawRect(x, y, 30, 30);
            }
        }
    }
}
