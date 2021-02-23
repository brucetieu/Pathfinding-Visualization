package maze;

import pathfinding.Node;

import javax.swing.*;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class DFSRandomized {

    private Node[][] grid;
    private JPanel panel;

    public DFSRandomized(Node[][] grid, JPanel panel) {
        this.grid = grid;
        this.panel = panel;

        Random random = new Random();

        int r = random.nextInt();
    }




}
