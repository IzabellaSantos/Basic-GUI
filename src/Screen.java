package def;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Screen extends JFrame {

    private JTextArea textArea;
    private JLabel statusLabel;
    private AnimationController animationController;
    
    public Screen() {
        setTitle("Basic GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        animationController = new AnimationController();

        setJMenuBar(menuPanel());
        add(createMainPanel(), BorderLayout.CENTER);
        add(statusPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JMenuBar menuPanel() {
    	//Main Menu
        JMenuBar menuBar = new JMenuBar();

        JMenu menuFiles = new JMenu("Files");
        JMenu menuSettings = new JMenu("Settings");
        JMenu menuHelp = new JMenu("Help");
        
        menuBar.add(menuFiles);
        menuBar.add(menuSettings);
        menuBar.add(menuHelp);
        
        //File Menu
        JMenuItem openItem = new JMenuItem("Open file");
        JMenuItem closeItem = new JMenuItem("Close file");
        JMenuItem exitItem = new JMenuItem("Exit");
        
        menuFiles.add(openItem);
        menuFiles.add(closeItem);
        menuFiles.addSeparator();
        menuFiles.add(exitItem);
         
        exitItem.addActionListener(e -> {
            System.exit(0);
        });
        
        openItem.addActionListener(e -> openFile());
        
        closeItem.addActionListener(e -> {
            textArea.setText("");
            statusLabel.setText("Status: File closed.");
        });
        
        //Help Menu
        JMenuItem helpItem = new JMenuItem("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        
        menuHelp.add(helpItem);
        menuHelp.add(aboutItem);
        
        Help help = new Help(this, textArea, statusLabel);

        helpItem.addActionListener(e -> help.showHelp());
        aboutItem.addActionListener(e -> help.showAbout());

        JMenuItem defaultsItem = new JMenuItem("Defaults");
        JMenuItem colorsItem = new JMenuItem("Colors");
        JMenuItem speedItem = new JMenuItem("Speed");

        menuSettings.add(defaultsItem);
        menuSettings.add(colorsItem);
        menuSettings.add(speedItem);


        SettingsMenu settingsMenu = new SettingsMenu(this, getContentPane(), animationController);
        defaultsItem.addActionListener(e -> settingsMenu.setDefaults());
        colorsItem.addActionListener(e -> settingsMenu.changeColors());
        speedItem.addActionListener(e -> settingsMenu.adjustSpeed());
        return menuBar;

    }
    
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        return mainPanel;
    }

    private JPanel statusPanel() {
        JPanel statusPanel = new JPanel();
        statusPanel.setBorder(BorderFactory.createEtchedBorder());
        statusLabel = new JLabel("Status: Ready");
        statusPanel.add(statusLabel);
        return statusPanel;
    }
    
    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        JMenuItem saveItem = new JMenuItem("Save file");
        menuFiles.add(saveItem);
        FileMenu fileMenu = new FileMenu(this, textArea, statusLabel);
        saveItem.addActionListener(e -> fileMenu.saveFile());
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                textArea.read(reader, null);
                statusLabel.setText("Status: " + selectedFile.getName() + " opened successfully.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error reading file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                statusLabel.setText("Status: Error opening file.");
            }
        }
    }
}