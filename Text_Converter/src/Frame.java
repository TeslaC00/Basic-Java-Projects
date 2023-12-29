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
import java.io.PrintWriter;
import java.util.Scanner;

public class Frame extends JFrame implements ActionListener {

    // All Global variables declared
    private Converter converter;
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu fileMenu, color;
    private JMenuItem open, load, save, exit, fontColor, bgColor;
    private String words[][];
    private JFileChooser fileChooser;
    private JScrollPane scrollPane;
    private JComboBox<String> fontBox;
    private Font defaultFont;

    // All constants defined
    final private int DEFAULT_FONT_SIZE = 20;
    final private int FRAME_WIDTH = 720;
    final private int FRAME_HEIGHT = 720;

    public Frame() {

        createMenuBar(); // creates a menu bar
        createFontBox(); // creates a fontbox
        createTextArea(); // creates a text area
        createUI(); // creates a basic UI and add all components
        initializeListeners(); // initialize all Listeners
        converter = new Converter();
    }

    private void createMenuBar() {

        // Initialize all menu variables
        fileChooser = new JFileChooser(".");
        fileMenu = new JMenu("File");
        color = new JMenu("Color");
        open = new JMenuItem("Open");
        load = new JMenuItem("Load");
        save = new JMenuItem("Save");
        exit = new JMenuItem("Exit");
        fontColor = new JMenuItem("Font Color");
        bgColor = new JMenuItem("Text Area Color");

        // Add all menu items to menu
        fileMenu.add(open);
        fileMenu.add(load);
        fileMenu.add(save);
        fileMenu.add(exit);
        color.add(fontColor);
        color.add(bgColor);

        // Add menu to menu bar
        menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(color);
    }

    private void createFontBox() {

        // Get all fonts
        String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontBox = new JComboBox<String>(fonts);
        fontBox.setSelectedItem("Ink Free");
    }

    private void createTextArea() {

        // Initialize all Text Area variables
        defaultFont = new Font("Ink Free", Font.PLAIN, DEFAULT_FONT_SIZE);
        textArea = new JTextArea("\n\t\tThis Is A Text Area");

        // Set textArea properties
        textArea.setEditable(true);
        textArea.setFont(defaultFont);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(Color.cyan);

        // Initalize scroll pane and add Text area to scroll pane
        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(670, 610));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    private void createUI() {

        // Set basic UI properties of this frame
        setTitle("Text Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.DARK_GRAY);
        setLayout(new FlowLayout());

        // Add components to this frame
        setJMenuBar(menuBar);
        add(fontBox);
        add(scrollPane);
        setVisible(true);
    }

    private void initializeListeners() {

        // Add action listener to all menu items
        load.addActionListener(this);
        save.addActionListener(this);
        exit.addActionListener(this);
        open.addActionListener(this);
        fontColor.addActionListener(this);
        bgColor.addActionListener(this);
        fontBox.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == exit) {
            System.exit(0);
        }

        else if (e.getSource() == load) {
            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                converter.setLoadFilePath(fileChooser.getSelectedFile().getAbsolutePath());
                words = converter.convertFileToWord();
                JOptionPane.showMessageDialog(null, "File loaded and processed successfully", "File Loaded",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "File didn't load", "File Load Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        else if (e.getSource() == save) {
            int response = fileChooser.showSaveDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                try (PrintWriter writer = new PrintWriter(fileChooser.getSelectedFile().getAbsoluteFile())) {
                    writer.println(textArea.getText());
                    if (writer.checkError()) {
                        JOptionPane.showMessageDialog(null, "File didn't saved error in format", "Format Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Text saved to file", "Save success",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (FileNotFoundException e1) {
                    JOptionPane.showMessageDialog(null, "File not found error", "File Address Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        else if (e.getSource() == open) {
            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                if (textArea.getText() != null)
                    textArea.setText("");
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                if (file.isFile()) {
                    try (Scanner scan = new Scanner(file)) {
                        while (scan.hasNextLine()) {
                            textArea.append(scan.nextLine() + "\n");
                        }
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }

        else if (e.getSource() == fontBox) {
            textArea.setFont(new Font(fontBox.getSelectedItem().toString(), Font.PLAIN, 20));
        }

        else if (e.getSource() == fontColor) {
            Color fontC = JColorChooser.showDialog(null, "Font Color Chooser", Color.black);
            textArea.setForeground(fontC);
        }

        else if (e.getSource() == bgColor) {
            Color bgC = JColorChooser.showDialog(null, "Text Area Color Chooser", Color.cyan);
            textArea.setBackground(bgC);
        }

        else {
            JOptionPane.showMessageDialog(null, "Unknown option selected please select valid options", "Unknown Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
