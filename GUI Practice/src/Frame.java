import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Frame extends JFrame{

    JButton button;
    
    Frame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setTitle("GUI Window");
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.CYAN);
        this.setVisible(true);

        button = new JButton("Button");
        button.setBounds(200,200,100,100);
        button.setOpaque(true);
        button.setFocusable(false);
        button.setBackground(Color.GREEN);
        button.setForeground(Color.BLUE);
        button.setFont(new Font("Ink Free", Font.ITALIC, 20));

        this.add(button);
    }
}
