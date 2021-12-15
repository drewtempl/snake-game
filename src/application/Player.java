package application;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Player
{
    public Point pos;
    public Point applePos;
    
    private Polygon player;
    public char direction;
    public int score;
    private String scoreStr;
    private String highScoreStr;
    private int size;
    public boolean game;
    
    private final int MOVE_DIST = 4;   
    public final int PLAYER_SIZE = 20;
    private final int APPLE_SIZE = 15;
    
    public Point[] body;
    
    public Player()
    {
        score = 0;
        size = 3;
        direction = 'u';
        game = true;
        
        body = new Point[100];
        body[0] = new Point(300, 250);
        
        for(int i = 1; i < size; i++)
        {
            body[i] = new Point(300, 250 - i * PLAYER_SIZE);
        }
       
        applePos = new Point();
        spawnApple();
    }
    
    public void draw(Graphics g)
    {
        g.setColor(new Color(0, 255, 0));
        
        for (int i = 0; i < size; i++)
        {
            g.fillOval(body[i].x, body[i].y, PLAYER_SIZE, PLAYER_SIZE);
        }
        
    }
    
    public void drawScore(Graphics g)
    {
        scoreStr = "Score: " + score;
        highScoreStr = "High Score: " + App.getScore();
        
        g.setColor(new Color(255, 255, 255));

        g.drawString(scoreStr, 25, 25);
        g.drawString(highScoreStr, 450, 25);
    }
    
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_UP) {
            direction = 'u';
        }
        if (key == KeyEvent.VK_RIGHT) {
            direction = 'r';
        }
        if (key == KeyEvent.VK_DOWN) {
            direction = 'd';
        }
        if (key == KeyEvent.VK_LEFT) {
            direction = 'l';
        }
    }
    
    public void move()
    {
        for(int i = size - 1; i > 0; i--)
        {
            body[i].x = body[i-1].x;
            body[i].y = body[i-1].y;
        }
        
        if(direction == 'u')
            body[0].y -= PLAYER_SIZE;
        
        else if(direction == 'd')
            body[0].y += PLAYER_SIZE;
        
        else if(direction == 'r')
            body[0].x += PLAYER_SIZE;
        
        else if(direction == 'l')
            body[0].x -= PLAYER_SIZE;
    }
    
    public void bounds()
    {
        if(body[0].x < 0 - PLAYER_SIZE)
            body[0].x = Board.WIDTH;
        
        else if(body[0].x > Board.WIDTH)
            body[0].x = 0 - PLAYER_SIZE;
        
        if(body[0].y < 50 - PLAYER_SIZE)
            body[0].y = Board.HEIGHT;
            
        else if(body[0].y > Board.HEIGHT)
            body[0].y = 50 - PLAYER_SIZE;
    }
   
    public void spawnApple()
    {
        Random rand = new Random();
        
        do
        {
            applePos.x = rand.nextInt(Board.WIDTH - APPLE_SIZE);
            applePos.y = rand.nextInt(Board.HEIGHT - 50 - APPLE_SIZE) + 50;
            
        } while(applePos == pos);  
    }
    
    public void drawApple(Graphics g)
    {
        g.setColor(new Color(255, 0, 0));
        g.fillOval(applePos.x, applePos.y, APPLE_SIZE, APPLE_SIZE);
    }
    
    public void appleCheck() 
    {
        if(body[0].x >= applePos.x - PLAYER_SIZE && body[0].x <= applePos.x + APPLE_SIZE &&
            body[0].y >= applePos.y - PLAYER_SIZE && body[0].y <= applePos.y + APPLE_SIZE)
        {
            addSize();
            spawnApple();
            score += 10;            
        }
        
    }
    
    public void checkCollision()
    {
        for (int i = size - 1; i > 0; i--)
        {
            if (size > 4 && body[0].x == body[i].x && body[0].y == body[i].y)
                game = false;
        }
    }
    
    public void addSize()
    {
        Point last = new Point(body[size - 1].x, body[size - 1].y);
        body[size] = last;
        size++;
    }
    
    public Point getPos()
    {
        return pos;
    }
}
