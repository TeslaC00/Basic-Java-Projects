import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener{

    private JButton button;
    private Converter converter;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem load;
    private JMenuItem save;

    Frame(){
        
        button = new JButton("Exit");
        button.addActionListener(this);
        button.setBounds(200, 200, 50, 50);
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        load = new JMenuItem("Load");
        save = new JMenuItem("Save");

        load.addActionListener(this);
        save.addActionListener(this);

        fileMenu.add(load);
        fileMenu.add(save);
        menuBar.add(fileMenu);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(500, 500);
        this.setJMenuBar(menuBar);
        this.add(button);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button){
            System.exit(0);
        }
        
        if(e.getSource()==load){
            JFileChooser fileChooser = new JFileChooser(".");
            int response = fileChooser.showOpenDialog(fileChooser);
            if(response==JFileChooser.APPROVE_OPTION){
                converter = new Converter(fileChooser.getSelectedFile().getAbsolutePath());
                converter.convertFileToWord();
            }
        }
    }
    
}
