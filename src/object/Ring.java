/*
 Name: 13
 Member names & IU code:
 Trần Thanh Hiếu _ ITCSIU21179
 Trần Vũ Khánh Hưng _ ITCSIU21182
 Ngô Í Khang _ ITCSIU21192
 Hồ Anh Dũng _ ITCSIU21172
 Purpose: Properties of the ring (act as Node of doubly linked list)
*/
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
//    private static int count;
    public int order = 0;


    Ring next;
    Ring pre;
    public Ring(int r, int g, int b){ //constructor
        this.r = r;
        this.g = g;
        this.b = b;
        color = new Color(r,g,b);

//        count++;
//        order = count;
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

    public void setOrder(int order){
        this.order = order;
    }

}
