package gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;


public class Grid extends JPanel implements MouseMotionListener {


    public Grid() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Get current width and size of component.
        int width = getWidth();
        int height = getHeight();

        // Creating 30 x 30 rects which span the size of the frame.
        for ( int x = 0; x <= width; x += 30 ) {
            for ( int y = 0; y <= height; y += 30 ) {
                g2d.drawRect(x, y, 30, 30);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
