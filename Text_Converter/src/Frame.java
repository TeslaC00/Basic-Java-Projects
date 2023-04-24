import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
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
    private JMenu color;
    private JMenuItem fontColor;
    private JMenuItem bgColor;
    private String words[][];
    private JFileChooser fileChooser;
    private JScrollPane scrollPane;
    private JComboBox<String> fontBox;
    private Font defaultFont;

    Frame(){

        //---------<<menu-bar>>----------//
        menuBar = new JMenuBar();
        fileChooser = new JFileChooser(".");
        textArea = new JTextArea("\n\t\tThis Is A Text Area");
        fileMenu = new JMenu("File");
        color = new JMenu("Color");
        open = new JMenuItem("Open");
        load = new JMenuItem("Load");
        save = new JMenuItem("Save");
        exit = new JMenuItem("Exit");
        fontColor = new JMenuItem("Font Color");
        bgColor = new JMenuItem("Text Area Color");

        load.addActionListener(this);
        save.addActionListener(this);
        exit.addActionListener(this);
        open.addActionListener(this);
        fontColor.addActionListener(this);
        bgColor.addActionListener(this);

        color.add(fontColor);
        color.add(bgColor);
        fileMenu.add(open);
        fileMenu.add(load);
        fileMenu.add(save);
        fileMenu.add(exit);
        menuBar.add(fileMenu);
        menuBar.add(color);
        //---------<<menu-bar>>----------//

        defaultFont = new Font("Ink Free", Font.PLAIN, 20);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setFont(defaultFont);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(Color.cyan);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(670, 610));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontBox = new JComboBox<String>(fonts);
        fontBox.setSelectedItem("Ink Free");
        fontBox.addActionListener(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(720, 720);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.setLayout(new FlowLayout());
        this.setJMenuBar(menuBar);
        this.add(fontBox);
        this.add(scrollPane);
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
                if(converter.saveWordToFile(savePath, words)){
                    JOptionPane.showMessageDialog(null, "File saved successfully", "File Saved", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null, "File didn't saved", "File Save Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if(e.getSource()==open){
            int response = fileChooser.showOpenDialog(null);
            if(response==JFileChooser.APPROVE_OPTION){
                if(textArea.getText()!=null) textArea.setText("");
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

        if(e.getSource()==fontBox){
            textArea.setFont(new Font(fontBox.getSelectedItem().toString(),Font.PLAIN,20));
        }

        if(e.getSource()==fontColor){
            Color fontC = JColorChooser.showDialog(null, "Font Color Chooser", Color.black);
            textArea.setForeground(fontC);
        }

        if(e.getSource()==bgColor){
            Color bgC = JColorChooser.showDialog(null, "Text Area Color Chooser", Color.cyan);
            textArea.setBackground(bgC);
        }
    }
    
}
