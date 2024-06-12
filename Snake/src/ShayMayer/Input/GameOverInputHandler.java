package ShayMayer.Input;

import java.awt.event.KeyEvent;

public class GameOverInputHandler extends InputHandler {
    private boolean toRestart;

    public GameOverInputHandler() {
        this.toRestart = false;
    }

    public boolean toRestart() {
        return this.toRestart;
    }

    public void reset() {
        this.toRestart = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.toRestart = (e.getKeyCode() == KeyEvent.VK_R);
    }
}
