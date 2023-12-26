package main;

import javax.swing.JFrame;
import java.awt.*;

public class GameWindow  {

    public GameWindow( GamePanel gamePanel){
        JFrame jframe = new JFrame();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setBackground(Color.black);
        jframe.add(gamePanel);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);
        jframe.setVisible(true);
    }
}
