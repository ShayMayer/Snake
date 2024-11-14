package ShayMayer.Input;

import ShayMayer.LogicUtils.Direction;

import java.awt.event.KeyEvent;

public class InGameInputHandler extends InputHandler {
    private Direction nextDirection;
    private Direction snakeCurDirection;

    public InGameInputHandler(Direction defaultDirection) {
        this.nextDirection = defaultDirection;
        this.snakeCurDirection = defaultDirection;
    }

    public void setSnakeCurDirection(Direction snakeCurDirection) {
        this.snakeCurDirection = snakeCurDirection;
    }

    public Direction getNextDirection() { return this.nextDirection; }

    private boolean validDirection(Direction dir) {
        return (dir != this.snakeCurDirection && !Direction.isOppositeDirection(dir, this.snakeCurDirection));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A: case KeyEvent.VK_LEFT: if(validDirection(Direction.LEFT)) this.nextDirection = Direction.LEFT; break;
            case KeyEvent.VK_D: case KeyEvent.VK_RIGHT: if(validDirection(Direction.RIGHT)) this.nextDirection = Direction.RIGHT; break;
            case KeyEvent.VK_W: case KeyEvent.VK_UP :if(validDirection(Direction.UP)) this.nextDirection = Direction.UP; break;
            case KeyEvent.VK_S: case KeyEvent.VK_DOWN:if(validDirection(Direction.DOWN)) this.nextDirection = Direction.DOWN; break;
            default: break;
        }
    }
}
