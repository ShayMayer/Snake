package ShayMayer.GameManagment;

import ShayMayer.GameFrames.SnakeGameFrame;

import javax.swing.*;

public class SnakeGame extends JFrame {
    private static final int SCREEN_WIDTH = 1100;
    private static final int SCREEN_HEIGHT = 734;

    private static final int ROWS = 15;
    private static final int COLS = 15;

    private static final long FRAME_DELAY = 120;

    SnakeGameFrame snakeGameFrame;

    public SnakeGame() {
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.snakeGameFrame = new SnakeGameFrame(SCREEN_WIDTH, SCREEN_HEIGHT, ROWS, COLS, FRAME_DELAY);
        this.snakeGameFrame.addListeners(this);

        this.add(this.snakeGameFrame.getRenderer());

        this.setVisible(true);
    }

    public void update() {
        this.snakeGameFrame.update();
    }
}
