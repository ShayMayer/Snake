package ShayMayer.GameManagment.GameFrames;

import ShayMayer.Logic.SnakeBoardLogic;
import ShayMayer.LogicUtils.Direction;
import ShayMayer.Renderers.SnakeBoardRenderer;
import ShayMayer.GameManagment.SnakeGame;
import ShayMayer.Input.GameOverInputHandler;
import ShayMayer.Input.InGameInputHandler;
import ShayMayer.LogicUtils.StopWatch;

public class SnakeGameFrame {
    private static final Direction defaultDirection = Direction.UP;

    private int width, height;
    private int rows, cols;

    private SnakeBoardLogic logic;
    private SnakeBoardRenderer renderer;

    private GameOverInputHandler gameOverInputHandler;
    private InGameInputHandler inGameInputHandler;

    private StopWatch stopWatch;

    private long frameDelay;

    public SnakeGameFrame(int width, int height, int rows, int cols, long frameDelay) {
        this.width = width;
        this.height = height;
        this.rows = rows;
        this.cols = cols;

        this.renderer = new SnakeBoardRenderer((int)(this.width * 0.1), (int)(this.height * 0.1), (int)(this.width * 0.8), (int)(this.height * 0.8), this.rows, this.cols);

        this.initializeListeners();

        this.logic = new SnakeBoardLogic(rows, cols, this.inGameInputHandler, defaultDirection);
        this.renderer.setParts(this.logic.getSnake(), this.logic.getFood(), this.logic.getScore());

        this.frameDelay = frameDelay;

        setStopWatch();
    }

    public void initializeListeners() {
        this.inGameInputHandler = new InGameInputHandler(defaultDirection);
        this.gameOverInputHandler = new GameOverInputHandler();
    }

    public SnakeBoardRenderer getRenderer() {
        return renderer;
    }

    public void addListeners(SnakeGame game) {
        game.addKeyListener(this.inGameInputHandler);
        game.addKeyListener(this.gameOverInputHandler);
    }

    private void setStopWatch() {
        this.stopWatch = new StopWatch(this.frameDelay);
    }

    public void update() {
        if(stopWatch.timePassed()) {
            this.updateLogic();
            this.updateGraphics();

            this.stopWatch.reset();
        }
    }

    private void updateLogic() {
        this.logic.update();

        if (this.logic.isGameOver())
            if (this.gameOverInputHandler.toRestart()) {
                this.logic = new SnakeBoardLogic(this.rows, this.cols, this.inGameInputHandler, defaultDirection);
                this.renderer.setParts(this.logic.getSnake(), this.logic.getFood(), this.logic.getScore());
                this.gameOverInputHandler.reset();
            }
    }

    private void updateGraphics() {
        this.renderer.repaint();
    }
}
