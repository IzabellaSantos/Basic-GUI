package def;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Help {

    private JFrame parentFrame;
    
    
	public Help(JFrame parentFrame) {
		super();
		this.parentFrame = parentFrame;
	}
	
	public void showHelp() {
		JDialog helpDialog = new JDialog(parentFrame, "Help", true);
		helpDialog.setLayout(new BorderLayout());
		helpDialog.setSize(520, 420);
		helpDialog.setLocationRelativeTo(parentFrame);

		// ---------- Header ----------
		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.setBackground(new Color(46, 134, 171));
		headerPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
		JLabel titleLabel = new JLabel("Help");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
		titleLabel.setForeground(Color.WHITE);
		headerPanel.add(titleLabel, BorderLayout.WEST);
		helpDialog.add(headerPanel, BorderLayout.NORTH);

		// ---------- Content ----------
		JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Try to load an optional image from the resources.
		ImageIcon icon = null;
		URL imgUrl = getClass().getResource("/def/help.png");
		if (imgUrl != null) {
			icon = new ImageIcon(imgUrl);
		}
		
		JLabel imageLabel = new JLabel();
		if (icon != null) {
		    Image scaledImage = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
		    icon = new ImageIcon(scaledImage);
			imageLabel.setIcon(icon);
			imageLabel.setHorizontalAlignment(JLabel.CENTER);
			contentPanel.add(imageLabel, BorderLayout.NORTH);
		}

		String welcomeText =
				"Welcome to Basic GUI with Threads!\n\n"
						+ "This application demonstrates:\n"
						+ "- Creation of a graphical user interface (GUI) with a structured menu system;\n"
						+ "- Opening and reading plain text files into the main view;\n"
						+ "- An animated background driven by a separate thread (demonstrating concurrency);\n"
						+ "- Configurable presets, color schemes and animation speed for the background;\n"
						+ "- Custom Help and About dialogs that include images, scrollable text and interactive buttons.\n\n"
						+ "Open Help → Help from the menu bar to learn more.";

		String filesText =
					"Files menu:\n\n"
							+ "- Open File: Opens a JFileChooser to pick a .txt file. The selected file is read and its contents "
							+ "are displayed in the main text area. Handle IOExceptions and show errors to the user.\n\n"
							+ "- Close File: Clears the main text area and resets the status bar. Disable this item when no file is open.\n\n"
							+ "- Exit: Gracefully terminates the application after confirming unsaved changes.\n\n";

		String settingsText =
				"Settings menu:\n\n"
						+ "- Presets: Choose predefined animation behaviors. Each preset sets parameters "
						+ "in the AnimationController.\n\n"
						+ "- Colors: Select a color palette using a palette chooser or JColorChooser. Apply the palette immediately to the background.\n\n"
						+ "- Speed: Change animation speed. The animation thread should read this value and adjust the delay.\n\n";

		// The text area and scroll pane
		JTextArea helpText = new JTextArea(welcomeText);
		helpText.setEditable(false);
		helpText.setLineWrap(true);
		helpText.setWrapStyleWord(true);
		helpText.setFont(new Font("Arial", Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane(helpText);
		scrollPane.setPreferredSize(new Dimension(480, 260));
    	contentPanel.add(scrollPane, BorderLayout.CENTER);

    	helpDialog.add(contentPanel, BorderLayout.CENTER);

    	// Buttons 
    	JPanel buttonPanel = new JPanel();
    	
    	JButton helpButton = new JButton("Help");
    	helpButton.setToolTipText("Show details about the Help menu");
    	helpButton.addActionListener(e -> {
    		helpText.setText(welcomeText);
    		helpText.setCaretPosition(0); // scroll to top
    	});

    	JButton filesButton = new JButton("Files");
    	filesButton.setToolTipText("Show details about the File menu");
    	filesButton.addActionListener(e -> {
    		helpText.setText(filesText);
    		helpText.setCaretPosition(0); // scroll to top
    	});

    	JButton settingsButton = new JButton("Settings");
    	settingsButton.setToolTipText("Show details about the Settings menu");
    	settingsButton.addActionListener(e -> {
    		helpText.setText(settingsText);
    		helpText.setCaretPosition(0);
    	});

    	JButton moreButton = new JButton("More Info");
    	moreButton.addActionListener(e -> {
    		javax.swing.JOptionPane.showMessageDialog(helpDialog,
                "For more information contact the authors.");
    	});

    	JButton okButton = new JButton("OK");
    	okButton.addActionListener(e -> helpDialog.dispose());

    	buttonPanel.add(helpButton);
    	buttonPanel.add(filesButton);
    	buttonPanel.add(settingsButton);
    	buttonPanel.add(moreButton);
    	buttonPanel.add(okButton);

    	helpDialog.add(buttonPanel, BorderLayout.SOUTH);

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
