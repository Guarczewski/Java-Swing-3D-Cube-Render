package CFrame;

import javax.swing.*;
import java.awt.*;

public class CFrame extends JFrame {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    public CFrame(){
        setTitle("Unknown");
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        pack();
    }
}
