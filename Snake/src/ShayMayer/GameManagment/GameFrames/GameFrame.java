package ShayMayer.GameManagment.GameFrames;

import ShayMayer.GameManagment.SnakeGame;

import javax.swing.*;

public abstract class GameFrame {
    protected int width, height;

    public GameFrame(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public abstract void addListeners(SnakeGame game);

    public abstract void update();

    public abstract JPanel getRenderer();
}
