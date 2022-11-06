/*
 * Display class for Snake game
 * @author Ethan Campbell
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class SnakeDisplay extends JPanel implements ActionListener{
    
    private int win_wid = 800;
    private int win_hei = 550;   
    private int cell_Size = 25;
    private int speed = 75;
    private int game_units = (win_wid * win_hei)/2;
    int x[] = new int[game_units];
    int y[] = new int[game_units];
    int bodyParts = 1;
    int applesEaten;
    int appleX;
    int appleY;
    Timer timer;
    char direction = 'R';
    boolean running = false;
    Random rand;
            
    private SnakeGame game;
    
    public SnakeDisplay(){
    
        rand = new Random();
        this.setPreferredSize(new Dimension(win_wid, win_hei));
        this.addKeyListener(new MyKeyAdapter());
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        startGame();
    }
    
    public void startGame(){
    
        newApple();
        running = true;
        timer = new Timer(speed,this);
        timer.start();
    }
    
    public void newApple(){
    
        appleX = rand.nextInt((int)(win_wid/cell_Size))*cell_Size;
        appleY = rand.nextInt((int)(win_hei/cell_Size))*cell_Size;
    }
    
    public void move(){
    
        for(int i = bodyParts; i > 0; i--){
        
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        
        switch(direction){
        
            case 'U':
                y[0] = y[0] - cell_Size;
                break;
                
            case 'R':
                x[0] = x[0] + cell_Size;
                break;
                
            case 'D':
                y[0] = y[0] + cell_Size;
                break;
                
            case 'L':
                x[0] = x[0] - cell_Size;
                break;
        }
    }
    
    public void paintComponent(Graphics g){
    
        super.paintComponent(g);
        drawBackground(g);
    }
    
    public void drawBackground(Graphics g) {

        if (running) {

            g.setColor(Color.RED);
            g.fillOval(appleX, appleY, cell_Size, cell_Size);
            for (int i = 0; i < bodyParts; i++) {

                if (i == 0) {

                    g.setColor(Color.GREEN);
                    g.fillRect(x[i], y[i], cell_Size, cell_Size);
                } else {

                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], cell_Size, cell_Size);
                }
            }
             g.setColor(Color.RED);
             g.setFont(new Font("Ink Free", Font.BOLD, 40));
             FontMetrics metrics = getFontMetrics(g.getFont());
             g.drawString("Score: " + applesEaten, (win_wid - metrics.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());
        }
        
        else{
        
            gameOver(g);
        }
    }
    
    public void checkApple(){
    
        if((x[0] == appleX) && (y[0] == appleY)){
        
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }
    
    public void checkCollisions(){
    
        for(int i = bodyParts; i > 0; i--){
        
            if((x[0] == x[i] && (y[0] == y[i]))){
            
                running = false;
            }
        }
        
        if(x[0] < 0){
        
            running = false;
        }
        
        if(x[0] > win_wid){
        
            running = false;
        }
        
        if(y[0] < 0){
        
            running = false;
        }
         
        if(y[0] > win_hei){
        
            running = false;
        }
        
        if(!running){
        
            timer.stop();
        }
    }
    
    public void gameOver(Graphics g){
    
         g.setColor(Color.RED);
         g.setFont(new Font("Ink Free", Font.BOLD, 40));
         FontMetrics metrics1 = getFontMetrics(g.getFont());
         g.drawString("Score: " + applesEaten, (win_wid - metrics1.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());
        
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over!", (win_wid - metrics2.stringWidth("Game Over!"))/2, win_hei/2);
    }
    
    public void actionPerformed(ActionEvent e){
    
        if(running){
        
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }
    
    public class MyKeyAdapter extends KeyAdapter{
    
        public void keyPressed(KeyEvent e){
        
            switch(e.getKeyCode()){
            
                case KeyEvent.VK_LEFT:
                    if(direction != 'R'){
                    
                        direction = 'L';
                    }
                    break;
                
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L'){
                    
                        direction = 'R';
                    }
                    break;
                
                case KeyEvent.VK_UP:
                    if(direction != 'D'){
                    
                        direction = 'U';
                    }
                    break;
                
                case KeyEvent.VK_DOWN:
                    if(direction != 'U'){
                    
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}
