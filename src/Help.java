package def;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Help {

    private JFrame parentFrame;
    //private JTextArea textArea;
    
    
	public Help(JFrame parentFrame, JTextArea textArea, JLabel statusLabel) {
		super();
		this.parentFrame = parentFrame;
		//this.textArea = textArea;
	}
	
	public void showHelp() {
	    JDialog helpDialog = new JDialog(parentFrame, "Help", true);
	    helpDialog.setLayout(new BorderLayout());
	    helpDialog.setSize(400, 350);
	    helpDialog.setLocationRelativeTo(parentFrame);
	    
	    // Header Panel
	    JPanel headerPanel = new JPanel();
	    headerPanel.setBackground(new Color(46, 134, 171));
	    headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
	    
	    JLabel titleLabel = new JLabel("Help!");
	    titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
	    titleLabel.setForeground(Color.WHITE);
	    headerPanel.add(titleLabel);
	    
	    // OK Button
	    JPanel buttonPanel = new JPanel();
	    JButton okButton = new JButton("OK");
	    okButton.addActionListener(e -> helpDialog.dispose());
	    buttonPanel.add(okButton);
	    
	    helpDialog.add(headerPanel, BorderLayout.NORTH);
	    
	    helpDialog.setVisible(true);
	}
    
	public void showAbout() {
		
	    JDialog aboutDialog = new JDialog(parentFrame, "About", true);
	    aboutDialog.setLayout(new BorderLayout());
	    aboutDialog.setSize(400, 350);
	    aboutDialog.setLocationRelativeTo(parentFrame);
	    
	    // Header Panel
	    JPanel headerPanel = new JPanel();
	    headerPanel.setBackground(new Color(46, 134, 171));
	    headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
	    
	    JLabel titleLabel = new JLabel("Basic GUI with Threads v1.0");
	    titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
	    titleLabel.setForeground(Color.WHITE);
	    headerPanel.add(titleLabel);
	    
	    // Content (Authors display)
	    JPanel contentPanel = new JPanel();
	    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
	    contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	    
	    JLabel authorsLabel = new JLabel("Developed by:");
	    authorsLabel.setFont(new Font("Arial", Font.BOLD, 18));
	    authorsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    contentPanel.add(authorsLabel);
	    contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
	    
	    String[] authors = {
	        "Antonio Carlos Rosendo Da Silva - RA: 174258",
	        "Gabriel Aleksandro De Paula - RA: 198327", 
	        "Gabriel Colombo - RA: 283993",
	        "Izabella Julia Dos Santos - RA: 169048",
	        "Leonardo Bonfá Schroeder - RA: 289156",
	        "Raissa Toassa Martinelli - RA: 184404"
	    };
	    
	    for (String author : authors) {
	        JLabel authorLabel = new JLabel(author);
	        authorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
	        authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	        contentPanel.add(authorLabel);
	    }
	    
	    // OK Button
	    JPanel buttonPanel = new JPanel();
	    JButton okButton = new JButton("OK");
	    okButton.addActionListener(e -> aboutDialog.dispose());
	    buttonPanel.add(okButton);
	    
	    
	    // Config JDialog
	    aboutDialog.add(headerPanel, BorderLayout.NORTH);
	    aboutDialog.add(contentPanel, BorderLayout.CENTER);
	    aboutDialog.add(buttonPanel, BorderLayout.SOUTH);
	    
	    aboutDialog.setVisible(true);
	}
}
