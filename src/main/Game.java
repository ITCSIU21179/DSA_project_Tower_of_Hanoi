/*
 Name: 13
 Member names & IU code:
 Trần Thanh Hiếu _ ITCSIU21179
 Trần Vũ Khánh Hưng _ ITCSIU21182
 Ngô Í Khang _ ITCSIU21192
 Hồ Anh Dũng _ ITCSIU21172
 Purpose: Construct and check every logic occurs (contain Singleton design pattern)
*/
package main;
import object.Pole;
import object.Ring;

import java.awt.*;
import java.util.Random;
import java.util.ArrayList;


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

    private boolean Click1 = false;
    private Pole selectedPole = null;
    private Random rand = new Random();
    private boolean move_able = true;
    private ArrayList<Integer> solve_tower;
    private static double last_solve;
    private static int step = 0;
    public boolean win = false;



    private Game(){
        nRings = 3;
        solve_tower = new ArrayList<>();
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();

    }
    //Singleton design pattern:
    public static void executeGame(){
        if (toh == null)
            toh = new Game();
    }

    private void initClasses() {
        pole1 = new Pole(186,180);
        pole2 = new Pole(486,180);
        pole3 = new Pole(786,180);
        init_Ring();
        doTowers(nRings,1,2,3);

    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        pole1.updateRing();
        pole2.updateRing();
        pole3.updateRing();
        setWin();
        if(!move_able) {
            RecursiveSolve();
        }

    }
    public void render(Graphics g){
        pole1.render(g);
        pole2.render(g);
        pole3.render(g);
    }
    private void setWin() {
        double now = System.currentTimeMillis();
        if(!pole3.isEmpty() && pole3.nRings == nRings && pole3.getLast().order ==1 && now - last_solve >= 500)
            win = true;
    }
    private void reset(){
        pole1.clearPole();
        pole2.clearPole();
        pole3.clearPole();
        selectedPole = null;
        solve_tower.clear();
        setClick_false();
        init_Ring();
        doTowers(nRings,1,2,3);
    }
    private void init_Ring() {
        for (int i = 0; i < nRings; i++) {
            Ring ring = new Ring(rand.nextInt(0, 256), rand.nextInt(0, 256), rand.nextInt(0, 256));
            ring.setOrder(nRings - i);
            ring.setScale((nRings - i - 1) * 50);
            pole1.insertLast(ring);
        }
    }
    public void moveRing(Point point) {
        if(point != null && move_able) {
            if ((point.x >= 153) && (point.x <= 248) && (point.y >= 600) && (point.y <= 649)) {
                selectPole(1);
            }
            if ((point.x >= 153 + 300) && (point.x <= 248 + 300) && (point.y >= 600) && (point.y <= 649)) {
                selectPole(2);
            }
            if ((point.x >= 153 + 600) && (point.x <= 248 + 600) && (point.y >= 600) && (point.y <= 649)) {
                selectPole(3);
            }
            if ((point.x >= 846) && (point.x <= 940) && (point.y >= 65) && (point.y <= 113)){
                move_able = false;
                reset();
                last_solve = System.currentTimeMillis();
            }
            if((point.x >= 721) && (point.x <= 816) && (point.y >= 65) && (point.y <= 113)){
                reset();

            }
            if((point.x >= 22) && (point.x <= 114) && (point.y >= 65) && (point.y <= 113)){
                nRings = 3;
                reset();

            }
            if((point.x >= 182) && (point.x <= 274) && (point.y >= 65) && (point.y <= 113)){
                nRings = 4;
                reset();

            }
            if((point.x >= 342) && (point.x <= 432) && (point.y >= 65) && (point.y <= 113)){
                nRings = 5;
                reset();

            }
            if(win &&(point.x >= 452) && (point.x <= 549) && (point.y >= 414) && (point.y <= 464)) {
                win = false;
                reset();

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
//                    System.out.println("from Pole 1");
                }

                else {
                    move(pole1);
                    setClick_false();
//                    System.out.println("to Pole 1");
                }


                break;
            case 2:
                if(!Click1) {
                    pole2.setChoseColor();
                    setClick1_true();
                    move(pole2);
//                    System.out.println("from Pole 2");
                }

                else {
                    move(pole2);
                    setClick_false();
//                    System.out.println("to Pole 2");
                }

                break;
            case 3:
                if(!Click1) {
                    pole3.setChoseColor();
                    setClick1_true();
                    move(pole3);
//                    System.out.println("from Pole 3");
                }

                else {
                    move(pole3);
                    setClick_false();
//                    System.out.println("to Pole 3");
                }

                break;
        }
    }
    private void setClick_false(){
        Click1 = false;
        pole1.setDefaultColor();
        pole2.setDefaultColor();
        pole3.setDefaultColor();
    }
    private void setClick1_true(){
        this.Click1 = true;
    }


    private void move(Pole pole){
        if (selectedPole == null){
            selectedPole = pole;
        }
        else {
                if ((pole.isEmpty() && !selectedPole.isEmpty()) || (!selectedPole.isEmpty() && pole.getLast().order > selectedPole.getLast().order )) {
                    pole.insertLast(selectedPole.removeLast());
                    selectedPole = null;
                }else selectedPole = null;

        }

    }
    //special thanks to l.n.v.Phuc for this idea!//
    private void doTowers(int order,int a, int b, int c){
        if(order ==1) {
            solve_tower.add(a);
            solve_tower.add(c);
        }
        else{
            doTowers(order-1, a, c, b);
            solve_tower.add(a);
            solve_tower.add(c);
            doTowers(order-1, b, a, c);
        }
    }
    private void RecursiveSolve() {

        double now = System.currentTimeMillis();
        if(now - last_solve >= 500){
            selectPole(solve_tower.get(step));
            step++;
            last_solve = now;
        }
        if(step == solve_tower.size()){
            step = 0;
            move_able = true;
        }

    }
    public Pole getPole3(){
        return pole3;
    }
    public int getnRings(){
        return nRings;
    }


    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / fps;
        double timePerUpdate = 1000000000.0 / ups;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates =0;

        double deltaU = 0;
        double deltaF = 0;

        while(true) {

            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime)/timePerUpdate;
            deltaF += (currentTime - previousTime)/timePerFrame;
            previousTime = currentTime;
            if(deltaU >=1){
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;

            }
        }
    }
}
