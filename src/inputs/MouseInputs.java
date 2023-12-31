/*
 Name: 13
 Member names & IU code:
 Trần Thanh Hiếu _ ITCSIU21179
 Trần Vũ Khánh Hưng _ ITCSIU21182
 Ngô Í Khang _ ITCSIU21192
 Hồ Anh Dũng _ ITCSIU21172
 Purpose: Get inputs from mouse
*/
package inputs;
import main.GamePanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseInputs implements MouseListener{
    GamePanel gamePanel;
    public MouseInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point a = e.getPoint();
//        System.out.println(a.x +" " +a.y);
            gamePanel.getGame().moveRing(a);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
