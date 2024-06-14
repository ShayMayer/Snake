package ShayMayer.GameManagment;

import ShayMayer.Entities.Piece;
import ShayMayer.LogicUtils.Score;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SnakeBoardRenderer extends JPanel {
    private static final int GRAPHICS_ARC_SIZE = 24;

    private static final Color BODY_COLOR = Color.GREEN;
    private static final Color HEAD_COLOR = Color.BLUE;
    private static final Color FOOD_COLOR = Color.RED;

    private ArrayList<Piece> snake;
    private ArrayList<Piece> food;
    private Score score;

    private int originX, originY;
    private int cols, rows;
    private int tileWidth, tileHeight;

    public SnakeBoardRenderer(int originX, int originY, int width, int height, int rows, int cols) {
        this.originX = originX;
        this.originY = originY;
        this.rows = rows;
        this.cols = cols;
        this.tileWidth = width / this.cols;
        this.tileHeight = height / this.rows;
    }

    public void setParts(ArrayList<Piece> snake, ArrayList<Piece> food, Score score) {
        this.snake = snake;
        this.food = food;
        this.score = score;
    }

    private void paintSnake(Graphics g) {
        g.setColor(BODY_COLOR);
        for(int i = this.snake.size() - 1; i > 0; i--) {
            Piece p = this.snake.get(i);
            g.fillRoundRect(this.originX + p.getX() * this.tileWidth, this.originY + p.getY() * this.tileHeight, this.tileWidth, this.tileHeight, GRAPHICS_ARC_SIZE, GRAPHICS_ARC_SIZE);
        }

        g.setColor(HEAD_COLOR);
        Piece head = this.snake.get(0);
        g.fillRoundRect(this.originX + head.getX() * this.tileWidth, this.originY + head.getY() * this.tileHeight, this.tileWidth, this.tileHeight, GRAPHICS_ARC_SIZE, GRAPHICS_ARC_SIZE);
    }

    private void paintFood(Graphics g) {
        g.setColor(FOOD_COLOR);
        for(Piece p : this.food)
            g.fillRoundRect(this.originX + p.getX() * this.tileWidth, this.originY + p.getY() * this.tileHeight, this.tileWidth, this.tileHeight, GRAPHICS_ARC_SIZE, GRAPHICS_ARC_SIZE);
    }

    public void paintLines(Graphics g) {
        g.setColor(Color.black);
        for(int i = 0; i < this.rows + 1; i++)
            g.drawLine(originX, originY + i * this.tileHeight, originX + cols * tileWidth, originY + i * tileHeight);

        for(int i = 0; i < this.cols + 1; i++)
            g.drawLine(originX + i * tileWidth, originY, originX + i * tileWidth, originY + rows * tileHeight);
    }

    public void paintScore(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.PLAIN, (int)(tileWidth * 0.8)));
        g.drawString("Score: " + this.score.getScore(), originX, originY - tileHeight / 2);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(this.snake == null || this.food == null) return;

        paintSnake(g);
        paintFood(g);
        paintLines(g);
        paintScore(g);
    }
}
