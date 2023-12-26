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

        g.drawImage(play_buttons.getSubimage(48,0,48,16),132,585 ,150,70,null);
        g.drawImage(play_buttons.getSubimage(48,0,48,16),432,585 ,150,70,null);
        g.drawImage(play_buttons.getSubimage(48,0,48,16),732,585 ,150,70,null);
        game.render(g);
        
//        g.setColor(new Color(255,100,150));
//        g.fillRect((300-15/2)-50-25, 600, ring1.width +100, ring1.height);
//        g.setColor(new Color(255,255,150));
//        g.fillRect((300-15/2)-25-25, 600- ring1.height, ring1.width +50, ring1.height);
//        g.setColor(new Color(255,100,25));
//        g.fillRect((300-15/2)-25, 600-2* ring1.height, ring1.width, ring1.height);

    }


}
