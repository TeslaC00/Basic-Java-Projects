import java.awt.Color;
import javax.swing.JFrame;

public class Frame extends JFrame{
    
    Frame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setTitle("GUI Window");
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.BLACK);
        this.setVisible(true);
    }
}
