package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class App
{
    private static JFrame window;
    private static Board board;
    private static JPanel buttonPanel;
    private static int highScore;
    
    
    private static void initUI()
    {
        window = new JFrame("Test App");
        window.setSize(500, 300);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        window.getContentPane().setBackground(Color.white);

        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setFocusable(true);
       
        initButtons();
        board = new Board();
        window.addKeyListener(board);
        highScore = 0;
        
        window.setVisible(true);
    }
    

    private static void initButtons()
    {
        buttonPanel = new JPanel();
        
        JButton button1 = new JButton("Start");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newView(1);
            }
        });
        
        JButton button2 = new JButton("Info");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("info here");
            }
        
        });
        
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        
        buttonPanel.setBackground(Color.white);
        
        window.add(buttonPanel, BorderLayout.PAGE_END);
        
    }
    
    public static void newView(int i)
    {
        if(i == 1)
        { 
            window.remove(buttonPanel);
            window.add(board);
            
            window.setVisible(true);
            window.pack();
        }
        
        else if(i == 2)
        {
            window.remove(buttonPanel);
            window.removeKeyListener(board);
            window.add(board);
            
            window.setVisible(true);
            window.pack();
        }
        
    }
    
    public static void setScore(int x)
    {
        highScore = x;
    }
    
    public static int getScore()
    {
        return highScore;
    }
    
    public static void restart()
    {
        window.remove(board);
        board = new Board();
        
        window.addKeyListener(board);
        window.add(board);
        
        window.setVisible(true);
        window.pack();
    }
    
    public void test()
    {
        System.out.println("start");
    }
    
    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initUI();
            }
        });

    }

}
