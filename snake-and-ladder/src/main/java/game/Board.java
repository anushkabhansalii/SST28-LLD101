package game;

import java.util.*;

public class Board {
    private final int size;              // n
    private final int lastCell;          // n^2
    private final List<Cell> cells;      // 1-based index (we'll ignore 0)
    private final List<Snake> snakes;
    private final List<Ladder> ladders;
    private final DifficultyLevel difficulty;
    private final Random random = new Random();

    public Board(int size, DifficultyLevel difficulty) {
        if (size < 2) {
            throw new IllegalArgumentException("Board size must be >= 2");
        }
        this.size = size;
        this.lastCell = size * size;
        this.difficulty = difficulty;
        this.cells = new ArrayList<>(lastCell + 1);
        // index 0 unused
        cells.add(null);
        for (int i = 1; i <= lastCell; i++) {
            cells.add(new Cell(i));
        }
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
    }

    public int getSize() {
        return size;
    }

    public int getLastCell() {
        return lastCell;
    }

    public int getNextPosition(int currentPosition) {
        if (currentPosition < 1 || currentPosition > lastCell) {
            return currentPosition;
        }
        Cell cell = cells.get(currentPosition);
        return cell.getNextPosition();
    }

    public void generateSnakesAndLadders(int count) {
        // we need 'count' snakes and 'count' ladders, no cycles
        Set<Integer> occupied = new HashSet<>();

        // helper to avoid cycles: we never allow a ladder end to be snake head and vice versa
        int attempts = 0;
        while (snakes.size() < count && attempts < count * 20) {
            attempts++;
            int head = randomBetween(2, lastCell - 1);
            int tail = randomBetween(1, head - 1);

            if (occupied.contains(head) || occupied.contains(tail)) continue;
            // avoid close to 1 to keep game playable
            Snake snake = new Snake(head, tail);
            snakes.add(snake);
            occupied.add(head);
            cells.get(head).setSnake(snake);
        }

        attempts = 0;
        while (ladders.size() < count && attempts < count * 20) {
            attempts++;
            int start = randomBetween(2, lastCell - 2);
            int end = randomBetween(start + 1, lastCell - 1);

            if (occupied.contains(start) || occupied.contains(end)) continue;
            Ladder ladder = new Ladder(start, end);
            ladders.add(ladder);
            occupied.add(start);
            cells.get(start).setLadder(ladder);
        }

        // Difficulty tweak:
        // EASY: try to keep average ladder gain higher than snake loss.
        // HARD: opposite. Here we only slightly bias positions.
        if (difficulty == DifficultyLevel.EASY) {
            // already somewhat easy by random; could tune further
        } else {
            // HARD: optionally add one extra snake if not too many
            if (snakes.size() < count + 1) {
                // best-effort extra snake
                attempts = 0;
                while (attempts++ < count * 10) {
                    int head = randomBetween(lastCell / 2, lastCell - 1);
                    int tail = randomBetween(1, head - 1);
                    if (occupied.contains(head) || occupied.contains(tail)) continue;
                    Snake snake = new Snake(head, tail);
                    snakes.add(snake);
                    occupied.add(head);
                    cells.get(head).setSnake(snake);
                    break;
                }
            }
        }
    }

    private int randomBetween(int min, int max) {
        if (max <= min) return min;
        return random.nextInt(max - min + 1) + min;
    }
}
