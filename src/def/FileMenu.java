package def;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter; 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileMenu {

    private JFrame parentFrame;
    private JTextArea textArea;
    private JLabel statusLabel;

    public FileMenu(JFrame parentFrame, JTextArea textArea, JLabel statusLabel) {
        this.parentFrame = parentFrame;
        this.textArea = textArea;
        this.statusLabel = statusLabel;
    }

    public void openFile() {
        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(parentFrame);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                textArea.read(reader, null);
                statusLabel.setText("Status: " + selectedFile.getName() + " opened successfully.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(parentFrame, "Error reading file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                statusLabel.setText("Status: Error opening file.");
            }
        }
    }
    
    public void closeFile() {
        textArea.setText("");
        statusLabel.setText("Status: File closed.");
    }
}