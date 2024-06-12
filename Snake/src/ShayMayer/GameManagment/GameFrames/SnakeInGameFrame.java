package ShayMayer.GameManagment.GameFrames;

import ShayMayer.Logic.SnakeBoardLogic;
import ShayMayer.Renderers.SnakeBoardRenderer;
import ShayMayer.GameManagment.SnakeGame;
import ShayMayer.Input.GameOverInputHandler;
import ShayMayer.Input.InGameInputHandler;
import ShayMayer.LogicUtils.StopWatch;

public class SnakeInGameFrame extends GameFrame {
    private static final int COLS = 15;
    private static final int ROWS = 15;

    private SnakeBoardLogic logic;
    private SnakeBoardRenderer renderer;

    private GameOverInputHandler gameOverInputHandler;
    private InGameInputHandler inGameInputHandler;

    private StopWatch stopWatch;

    private long frameDelay;

    public SnakeInGameFrame(int width, int height, long frameDelay) {
        super(width, height);

        this.renderer = new SnakeBoardRenderer((int)(this.width * 0.1), (int)(this.height * 0.1), (int)(this.width * 0.8), (int)(this.height * 0.8), ROWS, COLS);

        this.initializeListeners();

        this.logic = new SnakeBoardLogic(ROWS, COLS, this.inGameInputHandler);
        this.renderer.setParts(this.logic.getSnake(), this.logic.getFood(), this.logic.getScore());

        this.frameDelay = frameDelay;

        setStopWatch();
    }

    public void initializeListeners() {
        this.inGameInputHandler = new InGameInputHandler();
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
                this.logic = new SnakeBoardLogic(ROWS, COLS, this.inGameInputHandler);
                this.renderer.setParts(this.logic.getSnake(), this.logic.getFood(), this.logic.getScore());
                this.gameOverInputHandler.reset();
            }
    }

    private void updateGraphics() {
        this.renderer.repaint();
    }
}
