package ShayMayer.Logic;

import ShayMayer.Entities.Food;
import ShayMayer.Entities.Piece;
import ShayMayer.Entities.Snake.Snake;
import ShayMayer.Input.InGameInputHandler;
import ShayMayer.LogicUtils.Direction;
import ShayMayer.LogicUtils.Score;

import java.util.ArrayList;
import java.util.Random;

public class SnakeBoardLogic {
    private Snake snake;
    private ArrayList<Food> food;

    private Score score;

    private InGameInputHandler inputHandler;

    //private boolean gameOver = false;

    private int rows, cols;

    public SnakeBoardLogic(int rows, int cols, InGameInputHandler inputHandler, Direction defaultDirection) {
        this.rows = rows;
        this.cols = cols;

        this.score = new Score();

        this.inputHandler = inputHandler;

        this.snake = new Snake(this.rows, this.cols, defaultDirection);

        this.food = new ArrayList<Food>();
        this.generateFood();
        this.generateFood();
    }

    private void generateFood() {
        if(this.snake.size() == this.cols * this.rows - 1)
            return;

        Random rnd = new Random();
        int foodX, foodY;
        do {
            foodX = rnd.nextInt(this.cols);
            foodY = rnd.nextInt(this.rows);
        } while (this.snake.inSnake(foodX, foodY) || (this.food.size() > 0 && this.food.get(0).equals(foodX, foodY)));
        this.food.add(new Food(foodX, foodY));
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
        Food toRemove = null;
        for(int i = 0; i < food.size(); i++)
            if(this.snake.inSnake(food.get(i)))
                toRemove = food.get(i);

        if(toRemove != null) {
            this.score.update();
            this.food.remove(toRemove);
            this.generateFood();
        }
    }

    public boolean isGameOver() { return ((this.snake.size() == this.cols * this.rows) || this.snake.selfFed()); }

    public ArrayList<Piece> getSnake() { return this.snake.getSnake(); }
    public ArrayList<Food> getFood() { return this.food; }
    public Score getScore() { return this.score; }
}
