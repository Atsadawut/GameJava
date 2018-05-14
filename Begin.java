
package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Begin extends JFrame{
    private int statePlay = 0;
    public Begin(){
        super("Space War"); 
        while(statePlay == 0){
       
            setSize(400,200);
            
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            getContentPane().setLayout(new BorderLayout());
            
            JPanel x = new JPanel();
         
            JButton play = new JButton("Start");
            play.addActionListener(new ActionListener(){
            
                public void actionPerformed(ActionEvent ae1) {
                    statePlay = 1;
                }
            });
            
            x.add(play);
          
            getContentPane().add(x, BorderLayout.PAGE_END);
                
            setVisible(true);
        }
        
    }   
}