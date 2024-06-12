package ShayMayer.Entities.Snake;

import ShayMayer.Entities.Food;
import ShayMayer.Entities.Piece;
import ShayMayer.LogicUtils.Direction;

import java.awt.*;
import java.util.ArrayList;

public class Snake {
    private static int INITIAL_BODY_SIZE = 3;

    private int rows, cols;

    private ArrayList<Piece> body;
    private SnakeHead head;
    private Direction curDirection;

    public Snake(int rows, int cols, Direction defaultDirection) {
        this.body = new ArrayList<Piece>();

        this.rows = rows;
        this.cols = cols;
        this.head = new SnakeHead(this.cols / 2, this.rows / 2);

        this.body.add(this.head);
        for(int i = 1; i < INITIAL_BODY_SIZE; i++)
            this.body.add(new Piece(this.body.get(i - 1).getX(), this.body.get(i - 1).getY() + 1, Color.GREEN));

        this.curDirection = defaultDirection;
    }

    public void move(Direction direction, ArrayList<Food> food) {
        if(Direction.oppositeDirection(this.curDirection, direction))
            direction = curDirection;

        int newHeadX = this.head.getX(), newHeadY = this.head.getY();
        switch(direction) {
            case LEFT: newHeadX -= 1; break;
            case RIGHT: newHeadX += 1; break;
            case UP: newHeadY -= 1; break;
            case DOWN: newHeadY += 1; break;
            default: break;
        }

        if(newHeadX == -1) newHeadX = cols - 1;
        else if(newHeadX == cols) newHeadX = 0;
        else if(newHeadY == -1) newHeadY = rows - 1;
        else if(newHeadY == rows) newHeadY = 0;

        for(Food f : food)
            if(f.equals(newHeadX, newHeadY)){
                this.head.setColor(this.body.get(this.body.size() - 1).getColor());
                this.head = new SnakeHead(newHeadX, newHeadY);
                this.body.add(0, this.head);
                return;
            }

        for(int i = this.body.size() - 1; i > 0; i--) {
            this.body.get(i).setX(this.body.get(i - 1).getX());
            this.body.get(i).setY(this.body.get(i - 1).getY());
        }

        this.head.set(newHeadX, newHeadY);

        this.curDirection = direction;
    }

    public Direction getDirection() {
        return this.curDirection;
    }

    public boolean inSnake(int x, int y) {
        for(ShayMayer.Entities.Piece p : this.body)
            if(p.getX() == x && p.getY() == y)
                return true;
        return false;
    }

    public boolean inSnake(ShayMayer.Entities.Piece... pieces) {
        for (ShayMayer.Entities.Piece p : pieces)
            if (this.inSnake(p.getX(), p.getY()))
                return true;
        return false;
    }

    public boolean selfFed() {
        for(int i = 0; i < this.body.size(); i++)
            for(int j = i + 1; j < this.body.size(); j++)
                if(this.body.get(i).equals(this.body.get(j)))
                    return true;
        return false;
    }

    public int size() {
        return this.body.size();
    }

    public ArrayList<Piece> getSnake() {
        return this.body;
    }
}