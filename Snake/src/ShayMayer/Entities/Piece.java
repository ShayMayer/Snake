package ShayMayer.Entities;

import java.util.ArrayList;

public class Piece {
    protected int x, y;

    public Piece(int x, int y) {
        this.x = x;
        this.y = y;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(int x, int y) {
        return (this.x == x && this.y == y);
    }

    public static boolean piecesContain(ArrayList<Piece> pieces, int x, int y) {
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
