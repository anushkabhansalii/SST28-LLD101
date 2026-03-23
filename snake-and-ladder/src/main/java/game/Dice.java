package game;

import java.util.Random;

public class Dice {
    private final int sides;
    private final Random random = new Random();

    public Dice(int sides) {
        this.sides = sides;
    }

    public int roll() {
        // 1..sides
        return random.nextInt(sides) + 1;
    }
}
