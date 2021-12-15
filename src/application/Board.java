package application;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;


@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener, KeyListener
{
    private Timer timer;
    private Player player;
    private int highScore;
    
    public static final int WIDTH = 600;
    public static final int HEIGHT = 550;

    public Board()
    {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black); 
        
        player = new Player();
        
        timer = new Timer(120, this);
        timer.start();
        
        JButton restart = new JButton("Restart");
        
        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App.restart();
            }
        });
        
        add(restart);
 
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        player.drawApple(g);
        player.draw(g);
            
        g.setColor(new Color(0, 0, 255));
        g.fillRect(0, 0, WIDTH, 50);
        player.drawScore(g);
        
        if(!player.game)
        {
            g.setColor(new Color(255, 255, 0));
            g.drawString("Game Over", 300, 250);
        }
    } 

    @Override
    public void actionPerformed(ActionEvent e)
    {
       if(player.game)
       {
          player.move();
          player.checkCollision();
          player.appleCheck();
          player.bounds(); 
       } 
       
       checkHighScore();
       repaint();
       
    }

    public void checkHighScore()
    {
        if(player.score > App.getScore())
        {
            App.setScore(player.score);
        }
    }
    
    public Player getPlayer()
    {
        return player;
    }
    
    @Override
    public void keyTyped(KeyEvent e)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        player.keyPressed(e); 
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        // TODO Auto-generated method stub
        
    }
}
