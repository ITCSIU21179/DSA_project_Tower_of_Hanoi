package object;

import java.awt.*;

public class Pole {
    Ring first;
    Ring last;
    public int nRings;
    public final int width = 30;
    public final int height = 400;
    public int x;
    public int y;
    private final int default_color = 50;
    public Color color;
    public Pole(int x, int y){
        nRings =0;
        this.x = x;
        this.y = y;
        color = new Color(default_color);
    }


    public void insertLast(Ring newRing){
        if(first == null)
            first = newRing;
        else {
            last.next = newRing;
            newRing.pre = last;
        }
        last = newRing;
        nRings++;
        updateRing();

    }
    public Ring removeLast(){
        Ring temp = last;
        if(first.next == null)
            first = null;
        else last.pre.next = null;
        last = last.pre;
        nRings--;
        return temp;

    }
    public void updateRing(){
        Ring current = first;
        int i = nRings;
        while(current != null){
            current.updatePosition(x-current.order*27,y+height - (nRings-i+1)*40);
            i--;
            current = current.next;
        }
    }
    public void render(Graphics g){
        g.setColor(color);
        g.fillRect(x,y,width,height);
        Ring current = first;
        while(current != null){
            current.render(g);
            current = current.next;
        }
    }
    public void setChoseColor(){
        this.color = new Color(100,20,25);
    }
    public void setDefaultColor(){
        this.color = new Color(default_color);
    }
    public Ring getLast(){
        return last;
    }
    public boolean isEmpty(){
        return nRings==0;
    }
    public void clearPole(){
        first = null;
        last = null;
        nRings = 0;
    }

}
