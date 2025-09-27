package def;

import java.awt.Color;

public interface AnimationController {
    void startAnimation();
    void stopAnimation();
    void setSpeed(int speed);
    int getSpeed();

    void setShapeType(int type);
    int getShapeType();

    void setBackgroundColor(Color color);
    Color getBackgroundColor();

    void setFixedColor(Color color);
    Color getPrimaryColor();
    void setPrimaryColor(Color color);

    Color getSecondaryColor();    
    void setSecondaryColor(Color color);

    boolean isRunning();
}
