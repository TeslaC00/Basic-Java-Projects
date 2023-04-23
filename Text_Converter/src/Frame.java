import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener{

    private JButton button;
    private Converter converter;

    Frame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        button = new JButton("Select File");
        button.addActionListener(this);
        this.add(button);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button){
            JFileChooser fileChooser = new JFileChooser(".");
            int response = fileChooser.showOpenDialog(fileChooser);
            if(response==JFileChooser.APPROVE_OPTION){
                converter = new Converter(fileChooser.getSelectedFile().getAbsolutePath());
                converter.convertFileToWord();
            }
        }
    }
    
}
