package gui;


import pathfinding.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;


public class Grid extends JPanel implements MouseMotionListener {


    private List<Rectangle> rects = new ArrayList<>();
    private List<Node> nodes;

    public Grid() {
        addMouseMotionListener(this);
        nodes = new ArrayList<>();
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
                Rectangle rect = new Rectangle(x, y, 30, 30);
                rects.add(rect);
            }
        }

        g2d.setColor(Color.GRAY);
        for (int i = 0; i < rects.size(); i++) {
            g2d.draw(rects.get(i));
        }

        if (nodes != null) {
            g2d.setColor(Color.BLACK);
            for (int i = 0; i < nodes.size(); i++) {
                g2d.fill(nodes.get(i).getRect());
            }
        }
        g2d.dispose();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        nodes.add(new Node(x, y, 30, 30));
        repaint();

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
