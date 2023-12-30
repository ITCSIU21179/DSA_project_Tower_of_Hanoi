/*
 Name: 13
 Member names & IU code:
 Trần Thanh Hiếu _ ITCSIU21179
 Trần Vũ Khánh Hưng _ ITCSIU21182
 Ngô Í Khang _ ITCSIU21192
 Hồ Anh Dũng _ ITCSIU21172
 Purpose: Load non_moving image to the gamePanel
*/
package object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {
    public static final String Background = "background.jpg";
    public static final String Blank_buttons = "Blank_buttons.png";

    public static BufferedImage getImg(String filename) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + filename);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return img;

    }
}
