package maze;

import pathfinding.Node;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MazeGeneration extends JPanel {

    private final Node[][] grid;

    public MazeGeneration(int row, int col) {
        grid = new Node[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    grid[i][j] = new Node(i,j);
                }
            }
    }

    public void chooseMaze(int selectedIndex) {
        if (selectedIndex == 0) {
            new DFSRandomized(grid, this);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (int i = 0; i < this.grid.length; i+=25) {
            for (int j = 0; j < this.grid[0].length; j+=25) {
                g2d.drawRect(i, j, 25, 25);
            }
        }
//        for (Node node : grid) {
//            g2d.drawRect(node.getRow(), node.getCol(), 25, 25);
//        }
    }
}
