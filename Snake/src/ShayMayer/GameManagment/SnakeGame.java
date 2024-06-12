package ShayMayer.GameManagment;

import ShayMayer.GameManagment.GameFrames.SnakeInGameFrame;

import javax.swing.*;

public class SnakeGame extends JFrame {
    private static final long FRAME_DELAY = 120;
    private static final int SCREEN_WIDTH = 1100;
    private static final int SCREEN_HEIGHT = 734;

    SnakeInGameFrame snakeInGameFrame;

    public SnakeGame() {
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.snakeInGameFrame = new SnakeInGameFrame(SCREEN_WIDTH, SCREEN_HEIGHT, FRAME_DELAY);
        this.snakeInGameFrame.addListeners(this);

        this.add(this.snakeInGameFrame.getRenderer());

        this.setVisible(true);
    }

    public void update() {
        this.snakeInGameFrame.update();
    }
}
