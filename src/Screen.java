import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {

    public Screen() {
        setTitle("Basic GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); 

        setJMenuBar(menuPanel());
        add(statusPanel(), BorderLayout.SOUTH);
        setVisible(true);
    }

    private JMenuBar menuPanel() {
        JMenuBar menuBar = new JMenuBar(); // Using JMenuBar

        JMenu menuFiles = new JMenu("Files");
        JMenu menuSettings = new JMenu("Settings");
        JMenu menuHelp = new JMenu("Help");
        
        menuBar.add(menuFiles);
        menuBar.add(menuSettings);
        menuBar.add(menuHelp);
        
        JMenuItem newItem = new JMenuItem("New file"); // Using JMenuItem
        JMenuItem openItem = new JMenuItem("Open file");
        JMenuItem exitItem = new JMenuItem("Exit");
        
        menuFiles.add(newItem);
        menuFiles.add(openItem);
        menuFiles.addSeparator();
        menuFiles.add(exitItem);
         
        exitItem.addActionListener(e -> { // Using Listeners
            System.exit(0);
        });

        return menuBar;
    }

    private JPanel statusPanel() {
        JPanel statusPanel = new JPanel(); // Using JPanel
        statusPanel.setBorder(BorderFactory.createEtchedBorder());
        JLabel statusLabel = new JLabel("Status: Ready");
        statusPanel.add(statusLabel);

        return statusPanel;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Screen();
        });
    }
}