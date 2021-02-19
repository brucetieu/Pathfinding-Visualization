package tutorial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyFrame extends JFrame implements ActionListener{

    private JButton button;
    private JLabel label;
    public MyFrame() {

        button = new JButton();
        button.setBounds(200, 100, 250, 50);
        button.addActionListener(this);
        button.setText("I'm a button");
        button.setFocusable(false);

        label = new JLabel();
        label.setText("Appear magically");
        label.setBounds(150, 250, 150, 150);
        label.setVisible(false);

        this.setTitle("Pathfinding Visualizer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(420, 420);
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(255,255,255));
        this.add(button);
        this.add(label);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button) {
            label.setVisible(true);
            System.out.println("hello");
        }
    }
}
