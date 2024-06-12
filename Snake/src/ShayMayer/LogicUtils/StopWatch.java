package ShayMayer.LogicUtils;

public class StopWatch {
    private long timeToPassInMills;
    private long last;

    public StopWatch(long timeToPassInMills) {
        this.timeToPassInMills = timeToPassInMills;
        this.last = System.currentTimeMillis() - timeToPassInMills;
    }

    public boolean timePassed() {
        return (System.currentTimeMillis() - this.last) >= this.timeToPassInMills;
    }

    public void reset() {
        this.last = System.currentTimeMillis();
    }
}
