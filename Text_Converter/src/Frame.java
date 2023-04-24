import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Frame extends JFrame implements ActionListener {

    private Converter converter;
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem open;
    private JMenuItem load;
    private JMenuItem save;
    private JMenuItem exit;
    private String words[][];
    private JFileChooser fileChooser = new JFileChooser(".");

    Frame(){

        menuBar = new JMenuBar();
        textArea = new JTextArea("\n\n\n\n\n\t\tThis Is A Text Area");
        fileMenu = new JMenu("File");
        open = new JMenuItem("Open");
        load = new JMenuItem("Load");
        save = new JMenuItem("Save");
        exit = new JMenuItem("Exit");

        load.addActionListener(this);
        save.addActionListener(this);
        exit.addActionListener(this);
        open.addActionListener(this);

        fileMenu.add(open);
        fileMenu.add(load);
        fileMenu.add(save);
        fileMenu.add(exit);
        menuBar.add(fileMenu);

        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setJMenuBar(menuBar);
        this.add(textArea);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==exit){
            System.exit(0);
        }
        
        if(e.getSource()==load){
            int response = fileChooser.showOpenDialog(null);
            if(response==JFileChooser.APPROVE_OPTION){
                converter = new Converter(fileChooser.getSelectedFile().getAbsolutePath());
                words = converter.convertFileToWord();
                JOptionPane.showMessageDialog(null, "File loaded and processed successfully", "File Loaded", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if(e.getSource()==save){
            int response = fileChooser.showSaveDialog(null);
            if(response==JFileChooser.APPROVE_OPTION){
                String savePath = fileChooser.getSelectedFile().getAbsolutePath();
                converter.saveWordToFile(savePath, words);
            }
        }

        if(e.getSource()==open){
            if(textArea.getText()!=null) textArea.setText("");
            int response = fileChooser.showOpenDialog(null);
            if(response==JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                if(file.isFile()){
                    try (Scanner scan = new Scanner(file)) {
                        while(scan.hasNextLine()){
                            textArea.append(scan.nextLine()+"\n");
                        }
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }
    
}
