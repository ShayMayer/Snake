package ShayMayer.LogicUtils;


public class Score {
    private static final int SCORE_TO_ADD = 10;

    private int score;

    public Score() {
        this.score = 0;
    }

    public void update() {
        this.score += SCORE_TO_ADD;
    }

    public int getScore() { return this.score; }
}
