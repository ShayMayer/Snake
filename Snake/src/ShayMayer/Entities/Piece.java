package ShayMayer.Entities;

import java.awt.*;
import java.util.ArrayList;

public class Piece {
    protected int x, y;
    private Color color;

    public Piece(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void set(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public boolean equals(int x, int y) {
        return (this.x == x && this.y == y);
    }

    public static boolean listContains(ArrayList<Piece> pieces, int x, int y) {
        for(Piece p : pieces)
            if(p.equals(x, y))
                return true;
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Piece &&
                this.x == ((Piece) other).x && this.y == ((Piece) other).y);
    }
}
