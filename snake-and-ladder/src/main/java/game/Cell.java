package game;

public class Cell {
    private final int index;   // 1..n^2
    private Snake snake;
    private Ladder ladder;

    public Cell(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public void setLadder(Ladder ladder) {
        this.ladder = ladder;
    }

    public Snake getSnake() {
        return snake;
    }

    public Ladder getLadder() {
        return ladder;
    }

    // apply snake/ladder if present
    public int getNextPosition() {
        if (snake != null) {
            return snake.getTail();
        }
        if (ladder != null) {
            return ladder.getEnd();
        }
        return index;
    }
}
