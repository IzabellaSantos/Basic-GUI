import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {

    private JTextArea textArea;
    private JLabel statusLabel;
    private FileMenu fileMenu;

    public Screen() {
        setTitle("Basic GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); 

        textArea = new JTextArea();
        statusLabel = new JLabel("Status: Ready");
        fileMenu = new FileMenu(this, textArea, statusLabel);

        setJMenuBar(menuPanel());
        add(createMainPanel(), BorderLayout.CENTER);
        add(statusPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JMenuBar menuPanel() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuFiles = new JMenu("Files");
        JMenu menuSettings = new JMenu("Settings");
        JMenu menuHelp = new JMenu("Help");
        
        menuBar.add(menuFiles);
        menuBar.add(menuSettings);
        menuBar.add(menuHelp);
        
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
        
        openItem.addActionListener(e -> fileMenu.openFile());
        closeItem.addActionListener(e -> fileMenu.closeFile());

        return menuBar;
    }
    
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(textArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        return mainPanel;
    }

    private JPanel statusPanel() {
        JPanel statusPanel = new JPanel();
        statusPanel.setBorder(BorderFactory.createEtchedBorder());
        statusPanel.add(statusLabel);
        return statusPanel;
    }
}