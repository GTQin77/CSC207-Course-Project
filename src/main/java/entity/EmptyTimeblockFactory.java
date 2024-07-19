package entity;

public class EmptyTimeblockFactory implements TimeblockFactory {

    @Override
    public EmptyTimeblock create() {
        return new EmptyTimeblock();
    }
}
