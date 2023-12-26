package object;

import java.awt.*;

public class Ring {
    public int x;
    public int y;
    public int width = 90;
    public int height = 40;
    public int scale =0;
    public int r,g,b;
    private final Color color;
    private static int count;
    public int order = 0;


    Ring next;
    Ring pre;
    public Ring(int r, int g, int b){ //constructor
        this.r = r;
        this.g = g;
        this.b = b;
        color = new Color(r,g,b);

        count++;
        order = count;
    }
    public void render(Graphics g){
        g.setColor(color);
        g.fillRect(x,y,width+scale,height);
    }

    public void updatePosition(int x, int y){
        this.x = x;
        this.y = y;

    }
    public void setScale(int scale){
        this.scale = scale;
    }
//    public void displayProperties(){ System.out.printf("RingX: (r,g,b): (%d,%d,%d), x = %d, y= %d\n", r,g,b,x,y);}


}
