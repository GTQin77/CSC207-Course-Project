package entity;

public class EmptyTimeblock implements Timeblock {
    private final int duration;

    EmptyTimeblock() {
        this.duration = 1;
    }

    @Override
    public int getDuration() {
        return duration;
    }
}
