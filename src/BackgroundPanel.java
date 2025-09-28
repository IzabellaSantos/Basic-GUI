package def;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BackgroundPanel extends JPanel implements Runnable, AnimationController {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6260921200851725553L;
	private Thread animationThread;
    private volatile boolean running = false;

    private Color backgroundColor = Color.BLACK;
    private Color primaryColor = null;
    private Color secondaryColor = null;
    private int shapeType = 0;   // 0 = circles, 1 = squares, 2 = lines
    private int speed = 16;

    private final List<Bubble> bubbles = new ArrayList<>();
    private final Random random = new Random();

    public BackgroundPanel() {
        setDoubleBuffered(true);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        initBubbles();
    }

    private void initBubbles() {
        bubbles.clear();
        int width = Math.max(getWidth(), 1);
        int height = Math.max(getHeight(), 1);

        for (int i = 0; i < 20; i++) {
            createBubble(width, height);
        }
    }

    private void createBubble(int panelWidth, int panelHeight) {
        int size = 10 + random.nextInt(40);
        int safeWidth = Math.max(panelWidth / 5, 1);
        int x;
        if (random.nextBoolean()) {
            x = random.nextInt(safeWidth);
        } else {
            x = panelWidth - random.nextInt(safeWidth) - size;
        }
        int y = panelHeight + random.nextInt(Math.max(panelHeight, 1));
        int dy = -(1 + random.nextInt(3));
        Color color = generateRandomColor();
        bubbles.add(new Bubble(x, y, dy, size, color));
    }

    private Color generateRandomColor() {
        if (primaryColor != null && secondaryColor != null) {
            // Usa ambas as cores criando variações
            Color baseColor = random.nextBoolean() ? primaryColor : secondaryColor;
            
            int baseRed = baseColor.getRed();
            int baseGreen = baseColor.getGreen();
            int baseBlue = baseColor.getBlue();
        
            int variation = 60; // Menor variação quando temos cores definidas
        
            int newRed = Math.min(255, Math.max(0, baseRed + random.nextInt(2 * variation) - variation));
            int newGreen = Math.min(255, Math.max(0, baseGreen + random.nextInt(2 * variation) - variation));
            int newBlue = Math.min(255, Math.max(0, baseBlue + random.nextInt(2 * variation) - variation));
        
            return new Color(newRed, newGreen, newBlue, 180);
        }
        else if (primaryColor != null) {
            // Código atual para apenas primaryColor
            int baseRed = primaryColor.getRed();
            int baseGreen = primaryColor.getGreen();
            int baseBlue = primaryColor.getBlue();
        
            int variation = 80;
        
            int newRed = Math.min(255, Math.max(0, baseRed + random.nextInt(2 * variation) - variation));
            int newGreen = Math.min(255, Math.max(0, baseGreen + random.nextInt(2 * variation) - variation));
            int newBlue = Math.min(255, Math.max(0, baseBlue + random.nextInt(2 * variation) - variation));
        
            return new Color(newRed, newGreen, newBlue, 180);
        }
    
    // Cor padrão se nenhuma cor estiver definida
    return new Color(150 + random.nextInt(100),
                     150 + random.nextInt(100),
                     255, 180);
}

    // ---- Control methods ----
    @Override
    public synchronized void startAnimation() {
        if (animationThread != null && animationThread.isAlive()) return;
        running = true;
        animationThread = new Thread(this, "BackgroundAnimation");
        animationThread.setDaemon(true);
        animationThread.start();
    }

    @Override
    public synchronized void stopAnimation() {
        running = false;
        if (animationThread != null) {
            animationThread.interrupt();
            try {
                animationThread.join(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            animationThread = null;
        }
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = Math.max(5, speed);
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setShapeType(int type) {
        this.shapeType = type;
    }

    @Override
    public int getShapeType() {
        return shapeType;
    }

    @Override
    public void setBackgroundColor(Color color) {
        if (color != null) this.backgroundColor = color;
    }

    @Override
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public void setFixedColor(Color color) {
        this.primaryColor = color;
        for (Bubble b : bubbles) {
            b.color = generateRandomColor();
        }
    }

    @Override
    public Color getPrimaryColor() {
        return primaryColor != null ? primaryColor : Color.CYAN;
    }

    @Override
    public void setPrimaryColor(Color color) {
        setFixedColor(color);
    }

    @Override
    public Color getSecondaryColor() {
        return secondaryColor;
    }

    @Override
    public void setSecondaryColor(Color color) {
        if (color != null) this.secondaryColor = color;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    // ---- Animation loop ----
    @Override
    public void run() {
        while (running) {
            updateBubbles();
            repaint();
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void updateBubbles() {
        int panelWidth = Math.max(getWidth(), 1);
        int panelHeight = Math.max(getHeight(), 1);

        for (Bubble bubble : bubbles) {
            bubble.y += bubble.dy;

            if (bubble.y + bubble.size < 0) {
                int safeWidth = Math.max(panelWidth / 5, 1);
                bubble.x = (random.nextBoolean())
                        ? random.nextInt(safeWidth)
                        : panelWidth - random.nextInt(safeWidth) - bubble.size;

                bubble.y = panelHeight + random.nextInt(Math.max(panelHeight / 3, 1));
                bubble.size = 10 + random.nextInt(40);
                bubble.dy = -(1 + random.nextInt(3));
                bubble.color = generateRandomColor();
            }
        }
    }

    // ---- Painting ----
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(backgroundColor);
        g2.fillRect(0, 0, getWidth(), getHeight());

        for (Bubble bubble : bubbles) {
            g2.setColor(bubble.color);
            if (shapeType == 0) {
                g2.fillOval(bubble.x, bubble.y, bubble.size, bubble.size);
            } else if (shapeType == 1) {
                g2.fillRect(bubble.x, bubble.y, bubble.size, bubble.size);
            } else if (shapeType == 2) {
                g2.drawLine(bubble.x, bubble.y, bubble.x, bubble.y - bubble.size * 2);
            }
        }
        g2.setColor(new Color(0, 0, 0, 80));
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.dispose();
    }

    // ---- Inner class for bubble data ----
    private static class Bubble {
        int x, y, dy, size;
        Color color;

        Bubble(int x, int y, int dy, int size, Color color) {
            this.x = x;
            this.y = y;
            this.dy = dy;
            this.size = size;
            this.color = color;
        }
    }
}
