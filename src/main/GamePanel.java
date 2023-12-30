/*
 Name: 13
 Member names & IU code:
 Trần Thanh Hiếu _ ITCSIU21179
 Trần Vũ Khánh Hưng _ ITCSIU21182
 Ngô Í Khang _ ITCSIU21192
 Hồ Anh Dũng _ ITCSIU21172
 Purpose: Load and draw everything inside the play zone
*/
package main;

import inputs.MouseInputs;
import object.LoadSave;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;



public class GamePanel extends JPanel {


    private BufferedImage background = LoadSave.getImg(LoadSave.Background);
    private BufferedImage play_buttons = LoadSave.getImg(LoadSave.Blank_buttons);
    private Game game;
    private MouseInputs mouseInputs;
    public GamePanel(Game game){
        this.game = game;
        this.mouseInputs = new MouseInputs(this);
        addMouseListener(mouseInputs);
        setPanelSize();
    }




    private void setPanelSize() {
        Dimension size = new Dimension(1000,700);
        setPreferredSize(size);

    }
    public Game getGame(){
        return game;
    }


    public void paintComponent(Graphics g){
        super.paintComponents(g);
        g.drawImage(background,0,0,1000,700,null);
        if(!game.win) {
            g.drawImage(play_buttons.getSubimage(48, 0, 48, 16), 132, 585, 150, 70, null);
            g.drawImage(play_buttons.getSubimage(48, 0, 48, 16), 432, 585, 150, 70, null);
            g.drawImage(play_buttons.getSubimage(48, 0, 48, 16), 732, 585, 150, 70, null);
            g.drawImage(play_buttons.getSubimage(48, 0, 48, 16), 825, 50, 150, 70, null);
            g.drawImage(play_buttons.getSubimage(48, 0, 48, 16), 700, 50, 150, 70, null);
            g.drawImage(play_buttons.getSubimage(48, 0, 48, 16), 0, 50, 150, 70, null);
            g.drawImage(play_buttons.getSubimage(48, 0, 48, 16), 160, 50, 150, 70, null);
            g.drawImage(play_buttons.getSubimage(48, 0, 48, 16), 320, 50, 150, 70, null);
            g.setColor(new Color(113, 113, 113));
            g.setFont(new Font("TimesRoman", Font.BOLD, 30));
            g.drawString("Solve", 850, 100);
            g.drawString("Reset", 725, 100);
            g.setColor(new Color(0));
            g.setFont(new Font("TimesRoman", Font.BOLD, 25));
            g.drawString("3 Rings", 25, 100);
            g.drawString("4 Rings", 185, 100);
            g.drawString("5 Rings", 345, 100);

            game.render(g);

        }
        else{
            g.setColor(Color.green);
            g.setFont(new Font("TimesRoman", Font.BOLD, 50));
            g.drawString("You won", 400, 350);
            g.drawImage(play_buttons.getSubimage(48, 0, 48, 16), 432, 400, 150, 70, null);
            g.setColor(new Color(113, 113, 113));
            g.setFont(new Font("TimesRoman", Font.BOLD, 30));
            g.drawString("Retry", 460, 450);

        }


    }


}
