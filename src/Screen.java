
import javax.swing.*;

import def.BackgroundPanel;
import def.FileMenu;
import def.Help;
import def.SettingsMenu;

import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Screen extends JFrame {
	private static final long serialVersionUID = 4705267793229623217L;
	private JTextArea textArea;
    private JLabel statusLabel, statusTime;
    private BackgroundPanel backgroundPanel;
    private FileMenu fileMenu;
    private SettingsMenu settingsMenu;
    
    public Screen() {
        setTitle("Basic GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout());

        backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new BorderLayout());

        createComponents();
        
        setJMenuBar(createMenuBar());
        add(backgroundPanel, BorderLayout.CENTER);
        add(createStatusPanel(), BorderLayout.SOUTH);

        setVisible(true);
        startBackgroundAnimation();
    }

    private void createComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);
        
        textArea = new JTextArea();
        textArea.setOpaque(false);
        textArea.setForeground(Color.WHITE);
        textArea.setCaretColor(Color.WHITE);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        backgroundPanel.add(mainPanel, BorderLayout.CENTER);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuFiles = new JMenu("Files");
        JMenu menuSettings = new JMenu("Settings");
        JMenu menuHelp = new JMenu("Help");
        
        menuBar.add(menuFiles);
        menuBar.add(menuSettings);
        menuBar.add(menuHelp);
        
        // File Menu
        JMenuItem openItem = new JMenuItem("Open file");
        JMenuItem closeItem = new JMenuItem("Close file");
        JMenuItem exitItem = new JMenuItem("Exit");
        
        menuFiles.add(openItem);
        menuFiles.add(closeItem);
        menuFiles.addSeparator();
        menuFiles.add(exitItem);
         
        if (statusLabel == null) {
        	statusLabel = new JLabel("Loading...");
		}
        
        fileMenu = new FileMenu(this, textArea, statusLabel);
        
        exitItem.addActionListener(e -> System.exit(0));
        openItem.addActionListener(e -> fileMenu.openFile());
        closeItem.addActionListener(e -> fileMenu.closeFile());
        
        // Help Menu
        JMenuItem helpItem = new JMenuItem("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        
        menuHelp.add(helpItem);
        menuHelp.add(aboutItem);
        
        Help help = new Help(this);
        helpItem.addActionListener(e -> help.showHelp());
        aboutItem.addActionListener(e -> help.showAbout());

        JMenuItem defaultsItem = new JMenuItem("Defaults");
        JMenuItem colorsItem = new JMenuItem("Colors");
        JMenuItem speedItem = new JMenuItem("Speed");

        menuSettings.add(defaultsItem);
        menuSettings.add(colorsItem);
        menuSettings.add(speedItem);

        settingsMenu = new SettingsMenu(this, backgroundPanel);
        
        defaultsItem.addActionListener(e -> settingsMenu.setDefaults());
        colorsItem.addActionListener(e -> settingsMenu.changeColors());
        speedItem.addActionListener(e -> settingsMenu.adjustSpeed());
        
        return menuBar;
    }

    private JPanel createStatusPanel() {
        JPanel statusPanel = new JPanel();
        statusPanel.setBorder(BorderFactory.createEtchedBorder());
        statusPanel.setOpaque(true);

        statusTime = new JLabel("hh:mm:ss a");
        statusPanel.add(statusTime);

        statusLabel = new JLabel("Status: Ready");
        statusPanel.add(statusLabel);
        
        Timer timer = new Timer(1000, e -> {
            String horaAtual = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a"));
            statusTime.setText(horaAtual);
        });
        timer.start();
        
        return statusPanel;
    }

    private void startBackgroundAnimation() {
        Timer timer = new Timer(500, e -> {
            backgroundPanel.startAnimation();
            System.out.println("Background animation started!");
        });
        timer.setRepeats(false);
        timer.start();
    }
}