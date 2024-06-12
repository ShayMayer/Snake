package ShayMayer.LogicUtils;


public class Score {
    private static final int SCORE_FOR_EATING = 10;

    private int score;

    public Score() {
        this.score = 0;
    }

    public void update() {
        this.score += SCORE_FOR_EATING;
    }

    public int getScore() { return this.score; }
}
