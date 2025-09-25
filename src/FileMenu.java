package def;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
    
    public void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(parentFrame);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                textArea.write(writer);
                statusLabel.setText("Status: File saved successfully.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(parentFrame, "Error saving file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                statusLabel.setText("Status: Error saving file.");
            }
        }
    }
    
    public void closeFile() {
        textArea.setText("");
        statusLabel.setText("Status: File closed.");
    }
}