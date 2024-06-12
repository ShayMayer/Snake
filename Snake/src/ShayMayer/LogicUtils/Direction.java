package ShayMayer.LogicUtils;

public enum Direction {
    LEFT, RIGHT, UP, DOWN;

    public static boolean oppositeDirection(Direction first, Direction second) {
        return ((first == LEFT && second == RIGHT) || (first == RIGHT && second == LEFT) ||
                (first == UP && second == DOWN) || (first == DOWN && second == UP));
    }
}
