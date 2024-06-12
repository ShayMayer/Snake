package ShayMayer;

import ShayMayer.GameManagment.SnakeGame;

public class Main {

    public static void main(String[] args) {
        SnakeGame snake = new SnakeGame();
        while(true)
            snake.update();
    }
}
