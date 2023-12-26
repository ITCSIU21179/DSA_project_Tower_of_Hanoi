package main;
import object.Pole;
import object.Ring;

import java.awt.*;

public class Game implements Runnable{
    private static Game toh;
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int fps = 120;
    private final int ups = 200;
    private Pole pole1;
    private Pole pole2;
    private Pole pole3;
    private Ring ring1;
    private Ring ring2;
    private Ring ring3;
    private boolean Clicked = false;



    private Game(){
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();

    }

    private void initClasses() {
        pole1 = new Pole(186,180);
        pole2 = new Pole(486,180);
        pole3 = new Pole(786,180);
        ring1 = new Ring(255,100,150);
        ring2 = new Ring(255,255,150);
        ring3 = new Ring(255,100,25);
        ring2.setScale(50);
        ring3.setScale(100);
        pole1.insertLast(ring3);
        pole1.insertLast(ring2);
        pole1.insertLast(ring1);

        pole2.insertLast(pole1.removeLast());
        pole3.insertLast(pole1.removeLast());
        pole3.insertLast(pole2.removeLast());


    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public static void executeGame(){
        if (toh == null)
            toh = new Game();
    }
    public void update(){

    }
    public void render(Graphics g){
        pole1.render(g);
        pole2.render(g);
        pole3.render(g);
        ring1.render(g);
        ring2.render(g);
        ring3.render(g);
    }
    public void moveRing(Point point){
        if(point != null) {
            if ((point.x >= 153) && (point.x <= 248) && (point.y >= 600) && (point.y <= 649)) {
                selectPole(1);
//                setPoint(null);
            }
            if ((point.x >= 153 + 300) && (point.x <= 248 + 300) && (point.y >= 600) && (point.y <= 649)) {
                selectPole(2);
//                setPoint(null);
            }
            if ((point.x >= 153 + 600) && (point.x <= 248 + 600) && (point.y >= 600) && (point.y <= 649)) {
                selectPole(3);
//                setPoint(null);
            }
        }

    }

    public void selectPole(int i){
        switch(i){
            case 1:
                if(!Clicked) {
                    setClicked_true();
                    pole1.setChoseColor();
                }
                else setClicked_false();
                break;
            case 2:
                if(!Clicked) {
                    setClicked_true();
                    pole2.setChoseColor();
                }
                else setClicked_false();
                break;
            case 3:
                if(!Clicked) {
                    setClicked_true();
                    pole3.setChoseColor();
                }
                else setClicked_false();
                break;
        }
    }
    private void setClicked_false(){
        Clicked = false;
        pole1.setDefaultColor();
        pole2.setDefaultColor();
        pole3.setDefaultColor();
    }
    private void setClicked_true(){
        this.Clicked = true;
    }


    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / fps;
        double timePerUpdate = 1000000000.0 / ups;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates =0;
        long last = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while(true) {

            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime)/timePerUpdate;
            deltaF += (currentTime - previousTime)/timePerFrame;
            previousTime = currentTime;
//            if(deltaU >=1){
//                update();
//                updates++;
//                deltaU--;
//            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;

            }
//            ring1.displayProperties();
//            ring2.displayProperties();
//            ring3.displayProperties();

//            if(System.currentTimeMillis() - last >= 1000){
//                last = System.currentTimeMillis();
//                System.out.println("Fps = " + frames + "| Updates =" +updates);
//                frames =0;
//                updates =0;
//            }
        }
    }
}
