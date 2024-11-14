package ShayMayer.GameManagment;

import ShayMayer.Entities.Piece;
import ShayMayer.Entities.Snake;
import ShayMayer.Input.InGameInputHandler;
import ShayMayer.LogicUtils.Direction;
import ShayMayer.LogicUtils.Score;

import java.util.ArrayList;
import java.util.Random;

public class SnakeBoardLogic {
    private Snake snake;
    private ArrayList<Piece> food;

    private Score score;

    private InGameInputHandler inputHandler;

    private int rows, cols;

    public SnakeBoardLogic(int rows, int cols, InGameInputHandler inputHandler, Direction defaultDirection) {
        this.rows = rows;
        this.cols = cols;

        this.score = new Score();

        this.inputHandler = inputHandler;

        this.snake = new Snake(this.rows, this.cols, defaultDirection);

        this.food = new ArrayList<Piece>();
        this.generateFood();
        this.generateFood();
    }

    private void generateFood() {
        if(this.snake.size() + this.food.size() == this.cols * this.rows)
            return;

        Random rnd = new Random();
        int foodX, foodY;
        do {
            foodX = rnd.nextInt(this.cols);
            foodY = rnd.nextInt(this.rows);
        } while (this.snake.inSnake(foodX, foodY) || Piece.piecesContain(this.food, foodX, foodY));
        this.food.add(new Piece(foodX, foodY));
    }

    public void update() {
        if(this.isGameOver()) return;

        this.handleInput();
        this.handleFood();
    }

    private void handleInput() {
        this.snake.move(this.inputHandler.getNextDirection(), this.food);
        this.inputHandler.setSnakeCurDirection(this.snake.getDirection());
    }

    private void handleFood() {
        Piece toRemove = null;
        for (Piece piece : food)
            if (this.snake.inSnake(piece))
                toRemove = piece;

        if(toRemove != null) {
            this.score.update();
            this.food.remove(toRemove);
            this.generateFood();
        }
    }

    public boolean isGameOver() { return ((this.snake.size() == this.cols * this.rows) || this.snake.selfFed()); }

    public ArrayList<Piece> getSnake() { return this.snake.getSnake(); }
    public ArrayList<Piece> getFood() { return this.food; }
    public Score getScore() { return this.score; }
}
