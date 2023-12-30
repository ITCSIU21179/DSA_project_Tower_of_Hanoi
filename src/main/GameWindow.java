/*
 Name: 13
 Member names & IU code:
 Trần Thanh Hiếu _ ITCSIU21179
 Trần Vũ Khánh Hưng _ ITCSIU21182
 Ngô Í Khang _ ITCSIU21192
 Hồ Anh Dũng _ ITCSIU21172
 Purpose: Create the window around the play zone
*/
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
