package def;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsMenu {

    private JFrame parentFrame;
    private AnimationController animationController;

    public SettingsMenu(JFrame parentFrame, AnimationController animationController) {
        this.parentFrame = parentFrame;
        this.animationController = animationController;
    }

    public void setDefaults() {
        JDialog defaultsDialog = new JDialog(parentFrame, "Animation Patterns", true);
        defaultsDialog.setLayout(new BorderLayout());
        defaultsDialog.setSize(350, 280);
        defaultsDialog.setLocationRelativeTo(parentFrame);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(46, 134, 171));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Select Animation Pattern");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        ButtonGroup patternGroup = new ButtonGroup();
        
        int currentType = animationController.getShapeType();
        JRadioButton circlesPattern = new JRadioButton("Moving Circles", currentType == 0);
        JRadioButton squaresPattern = new JRadioButton("Floating Squares", currentType == 1);
        JRadioButton linesPattern = new JRadioButton("Dynamic Lines", currentType == 2);

        patternGroup.add(circlesPattern);
        patternGroup.add(squaresPattern);
        patternGroup.add(linesPattern);

        contentPanel.add(circlesPattern);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(squaresPattern);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(linesPattern);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton applyButton = new JButton("Apply");
        JButton cancelButton = new JButton("Cancel");

        applyButton.addActionListener(e -> {
            if (circlesPattern.isSelected()) {
                animationController.setShapeType(0); // circles
            } else if (squaresPattern.isSelected()) {
                animationController.setShapeType(1); // squares
            } else if (linesPattern.isSelected()) {
                animationController.setShapeType(2); // lines
            }
            defaultsDialog.dispose();
        });

        cancelButton.addActionListener(e -> defaultsDialog.dispose());

        buttonPanel.add(applyButton);
        buttonPanel.add(cancelButton);

        defaultsDialog.add(headerPanel, BorderLayout.NORTH);
        defaultsDialog.add(contentPanel, BorderLayout.CENTER);
        defaultsDialog.add(buttonPanel, BorderLayout.SOUTH);

        defaultsDialog.setVisible(true);
    }

    public void changeColors() {
        JDialog colorsDialog = new JDialog(parentFrame, "Animation Colors", true);
        colorsDialog.setLayout(new BorderLayout());
        colorsDialog.setSize(400, 320);
        colorsDialog.setLocationRelativeTo(parentFrame);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(46, 134, 171));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Customize Animation Colors");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // Content Panel
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();

        // Primary Color
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        contentPanel.add(new JLabel("Primary Color:"), gbc);

        gbc.gridx = 1;
        JButton primaryColorButton = new JButton("    ");
        primaryColorButton.setBackground(animationController.getPrimaryColor());
        primaryColorButton.setPreferredSize(new Dimension(50, 30));
        contentPanel.add(primaryColorButton, gbc);

        // Secondary Color
        gbc.gridx = 0; gbc.gridy = 1;
        contentPanel.add(new JLabel("Secondary Color:"), gbc);

        gbc.gridx = 1;
        JButton secondaryColorButton = new JButton("    ");
        secondaryColorButton.setBackground(animationController.getSecondaryColor() != null ? 
                                         animationController.getSecondaryColor() : Color.GRAY);
        secondaryColorButton.setPreferredSize(new Dimension(50, 30));
        contentPanel.add(secondaryColorButton, gbc);

        // Background Color
        gbc.gridx = 0; gbc.gridy = 2;
        contentPanel.add(new JLabel("Background Color:"), gbc);

        gbc.gridx = 1;
        JButton backgroundColorButton = new JButton("    ");
        backgroundColorButton.setBackground(animationController.getBackgroundColor());
        backgroundColorButton.setPreferredSize(new Dimension(50, 30));
        contentPanel.add(backgroundColorButton, gbc);

        // Color Scheme Presets
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 5, 5, 5);
        contentPanel.add(new JLabel("Color Presets:"), gbc);

        gbc.gridy = 4;
        gbc.insets = new Insets(5, 5, 5, 5);
        JPanel presetPanel = new JPanel(new FlowLayout());
        JButton bluePreset = new JButton("Ocean Blue");
        JButton redPreset = new JButton("Sunset Red");
        JButton greenPreset = new JButton("Forest Green");
        JButton purplePreset = new JButton("Purple Night");

        presetPanel.add(bluePreset);
        presetPanel.add(redPreset);
        presetPanel.add(greenPreset);
        presetPanel.add(purplePreset);
        contentPanel.add(presetPanel, gbc);

        // Color Picker Action Listeners
        primaryColorButton.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(colorsDialog, "Choose Primary Color",
                    animationController.getPrimaryColor());
            if (newColor != null) {
                primaryColorButton.setBackground(newColor);
            }
        });

        secondaryColorButton.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(colorsDialog, "Choose Secondary Color",
                    animationController.getSecondaryColor() != null ? 
                    animationController.getSecondaryColor() : Color.GRAY);
            if (newColor != null) {
                secondaryColorButton.setBackground(newColor);
            }
        });

        backgroundColorButton.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(colorsDialog, "Choose Background Color",
                    animationController.getBackgroundColor());
            if (newColor != null) {
                backgroundColorButton.setBackground(newColor);
            }
        });

        // Preset Action Listeners
        bluePreset.addActionListener(e -> {
            primaryColorButton.setBackground(new Color(30, 144, 255));    // DodgerBlue
            secondaryColorButton.setBackground(new Color(0, 191, 255));   // DeepSkyBlue
            backgroundColorButton.setBackground(new Color(25, 25, 112));  // MidnightBlue
        });

        redPreset.addActionListener(e -> {
            primaryColorButton.setBackground(new Color(255, 99, 71));     // Tomato
            secondaryColorButton.setBackground(new Color(255, 140, 0));   // DarkOrange
            backgroundColorButton.setBackground(new Color(139, 0, 0));    // DarkRed
        });

        greenPreset.addActionListener(e -> {
            primaryColorButton.setBackground(new Color(34, 139, 34));     // ForestGreen
            secondaryColorButton.setBackground(new Color(144, 238, 144)); // LightGreen
            backgroundColorButton.setBackground(new Color(0, 100, 0));    // DarkGreen
        });

        purplePreset.addActionListener(e -> {
            primaryColorButton.setBackground(new Color(138, 43, 226));    // BlueViolet
            secondaryColorButton.setBackground(new Color(186, 85, 211));  // MediumOrchid
            backgroundColorButton.setBackground(new Color(75, 0, 130));   // Indigo
        });

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton applyButton = new JButton("Apply");
        JButton cancelButton = new JButton("Cancel");

        applyButton.addActionListener(e -> {
            animationController.setPrimaryColor(primaryColorButton.getBackground());
            animationController.setSecondaryColor(secondaryColorButton.getBackground());
            animationController.setBackgroundColor(backgroundColorButton.getBackground());
            colorsDialog.dispose();
        });

        cancelButton.addActionListener(e -> colorsDialog.dispose());

        buttonPanel.add(applyButton);
        buttonPanel.add(cancelButton);

        colorsDialog.add(headerPanel, BorderLayout.NORTH);
        colorsDialog.add(contentPanel, BorderLayout.CENTER);
        colorsDialog.add(buttonPanel, BorderLayout.SOUTH);

        colorsDialog.setVisible(true);
    }

    public void adjustSpeed() {
        JDialog speedDialog = new JDialog(parentFrame, "Animation Speed", true);
        speedDialog.setLayout(new BorderLayout());
        speedDialog.setSize(350, 250);
        speedDialog.setLocationRelativeTo(parentFrame);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(46, 134, 171));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Adjust Animation Speed");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel speedLabel = new JLabel("Speed Level:");
        speedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        int currentSpeed = animationController.getSpeed();
        int sliderValue = Math.max(1, Math.min(10, 11 - (currentSpeed / 10)));
        
        JSlider speedSlider = new JSlider(1, 10, sliderValue);
        speedSlider.setMajorTickSpacing(1);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel currentSpeedLabel = new JLabel("Current Speed: " + sliderValue);
        currentSpeedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        speedSlider.addChangeListener(e -> {
            currentSpeedLabel.setText("Current Speed: " + speedSlider.getValue());
        });

        contentPanel.add(speedLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(speedSlider);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(currentSpeedLabel);

        // Additional Speed Options
        contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel optionsPanel = new JPanel(new FlowLayout());
        JButton pauseButton = new JButton("Pause Animation");
        JButton resumeButton = new JButton("Resume Animation");

        pauseButton.addActionListener(e -> {
            animationController.stopAnimation();
            pauseButton.setEnabled(false);
            resumeButton.setEnabled(true);
        });

        resumeButton.addActionListener(e -> {
            animationController.startAnimation();
            resumeButton.setEnabled(false);
            pauseButton.setEnabled(true);
        });

        resumeButton.setEnabled(!animationController.isRunning());
        pauseButton.setEnabled(animationController.isRunning());

        optionsPanel.add(pauseButton);
        optionsPanel.add(resumeButton);
        contentPanel.add(optionsPanel);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton applyButton = new JButton("Apply");
        JButton cancelButton = new JButton("Cancel");

        applyButton.addActionListener(e -> {
            int newSpeed = 100 - (speedSlider.getValue() * 10);
            animationController.setSpeed(newSpeed);
            speedDialog.dispose();
        });

        cancelButton.addActionListener(e -> speedDialog.dispose());

        buttonPanel.add(applyButton);
        buttonPanel.add(cancelButton);

        speedDialog.add(headerPanel, BorderLayout.NORTH);
        speedDialog.add(contentPanel, BorderLayout.CENTER);
        speedDialog.add(buttonPanel, BorderLayout.SOUTH);

        speedDialog.setVisible(true);
    }
}