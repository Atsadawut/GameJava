
package game;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GOver extends JFrame{
    private int g = 0;
    public GOver(){
        super("GAME OVER");
        
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());    
        JPanel j = new JPanel();
        j.setLayout(null);    
        JLabel jb = new JLabel("GAME OVER");
        jb.setBounds(110,25,100,100);
        j.add(jb);
        getContentPane().add(j);        
        setVisible(true);
    } 
}