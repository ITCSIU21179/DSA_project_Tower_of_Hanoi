package main;
import object.Pole;
import object.Ring;

import java.awt.*;
import java.util.Random;

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
    private int nRings;
    private Ring ring1;
    private Ring ring2;
    private Ring ring3;
    private boolean Click1 = false;
    private Pole selectedPole = null;
    private Random rand = new Random();



    private Game(){
        nRings = 3;
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

        for(int i = 0; i< nRings; i++){
            Ring ring = new Ring(rand.nextInt(0,256), rand.nextInt(0,256),rand.nextInt(0,256));
            ring.setOrder(nRings-i);
            ring.setScale((nRings-i-1)*50);
            pole1.insertLast(ring);
        }
//        ring1 = new Ring(255,100,150);
//        ring2 = new Ring(255,255,150);
//        ring3 = new Ring(255,100,25);
//        ring2.setScale(50);
//        ring3.setScale(100);
//        pole1.insertLast(ring3);
//        pole1.insertLast(ring2);
//        pole1.insertLast(ring1);

//        pole2.insertLast(pole1.removeLast());
//        pole3.insertLast(pole1.removeLast());
//        pole3.insertLast(pole2.removeLast());
//        pole2.insertLast(pole1.removeLast());



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


//        ring1.render(g);
//        ring2.render(g);
//        ring3.render(g);
    }
    public void moveRing(Point point){
        if(point != null) {
            if ((point.x >= 153) && (point.x <= 248) && (point.y >= 600) && (point.y <= 649)) {
                selectPole(1);

            }
            if ((point.x >= 153 + 300) && (point.x <= 248 + 300) && (point.y >= 600) && (point.y <= 649)) {
                selectPole(2);

            }
            if ((point.x >= 153 + 600) && (point.x <= 248 + 600) && (point.y >= 600) && (point.y <= 649)) {
                selectPole(3);

            }
        }

    }

    public void selectPole(int i){
        switch(i){
            case 1:
                if(!Click1) {
                    pole1.setChoseColor();
                    setClick1_true();
                    move(pole1);
                    System.out.println("select Pole 1");
                }

                else {
//                        setClick2_true();
                    move(pole1);
                    setClick_false();
                    System.out.println("select Pole 1");
                }


                break;
            case 2:
                if(!Click1) {
                    pole2.setChoseColor();
                    setClick1_true();
                    move(pole2);
                    System.out.println("select Pole 2");
                }

                else {
//                        setClick2_true();
                    move(pole2);
                    setClick_false();
                    System.out.println("select Pole 2");
                }

                break;
            case 3:
                if(!Click1) {
                    pole3.setChoseColor();
                    setClick1_true();
                    move(pole3);
                    System.out.println("select Pole 3");
                }

                else {
//                        setClick2_true();
                    move(pole3);
                    setClick_false();
                    System.out.println("select Pole 3");
                }

                break;
        }
    }
    private void setClick_false(){
        Click1 = false;
//        Click2 = false;
        pole1.setDefaultColor();
        pole2.setDefaultColor();
        pole3.setDefaultColor();
    }
    private void setClick1_true(){
        this.Click1 = true;
    }
//    private void setClick2_true(){
//        this.Click2 = true;
//    }

    private void move(Pole pole){
        if (selectedPole == null){
            selectedPole = pole;
        }
        else {
                if ((pole.isEmpty() && !selectedPole.isEmpty()) || (!selectedPole.isEmpty() && pole.getLast().order > selectedPole.getLast().order )) {
                    pole.insertLast(selectedPole.removeLast());
                    System.out.println("moved");
                    selectedPole = null;
                }else selectedPole = null;

        }

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
