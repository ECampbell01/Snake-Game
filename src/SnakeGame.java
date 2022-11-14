/*
 * Game class for Snake game
 * @author Ethan Campbell
 */

import javax.swing.*;

public class SnakeGame extends JFrame{
    
    SnakeGame(){

        this.add(new SnakeDisplay());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
