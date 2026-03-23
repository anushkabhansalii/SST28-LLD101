package game;

import java.util.*;

public class Game {
    private final Board board;
    private final Dice dice;
    private final Queue<Player> players;
    private final DifficultyLevel difficulty;
    private final Scanner scanner = new Scanner(System.in);

    public Game(int size, int playerCount, DifficultyLevel difficulty) {
        this.difficulty = difficulty;
        this.board = new Board(size, difficulty);
        // n snakes and n ladders, where n = size (as per requirement)
        board.generateSnakesAndLadders(size);
        this.dice = new Dice(6);
        this.players = new ArrayDeque<>();

        for (int i = 1; i <= playerCount; i++) {
            players.add(new Player(i, "Player " + i));
        }
    }

    public void start() {
        System.out.println("Starting Snake & Ladder with board size " +
                board.getSize() + "x" + board.getSize() +
                " and difficulty " + difficulty);

        int activePlayers = players.size();
        while (activePlayers >= 2) {
            Player current = players.poll();
            if (!current.isActive()) {
                // already finished
                players.add(current);
                continue;
            }

            System.out.println("\nTurn: " + current.getName() +
                               " (position: " + current.getPosition() + ")");
            System.out.print("Press Enter to roll dice...");
            scanner.nextLine();

            int roll = dice.roll();
            System.out.println(current.getName() + " rolled: " + roll);

            int oldPos = current.getPosition();
            int tentative = oldPos + roll;

            if (tentative > board.getLastCell()) {
                System.out.println("Cannot move beyond last cell (" +
                        board.getLastCell() + "). Staying at " + oldPos);
            } else {
                int finalPos = board.getNextPosition(tentative);
                // messages for snake / ladder
                if (finalPos < tentative) {
                    System.out.println("Oops! Snake from " + tentative +
                            " down to " + finalPos);
                } else if (finalPos > tentative) {
                    System.out.println("Yay! Ladder from " + tentative +
                            " up to " + finalPos);
                }
                current.setPosition(finalPos);
                System.out.println(current.getName() +
                        " moved to " + finalPos);

                if (finalPos == board.getLastCell()) {
                    System.out.println(current.getName() + " has reached the last cell and WON!");
                    current.setActive(false);
                    activePlayers--;
                }
            }

            players.add(current);
        }

        System.out.println("\nGame over. Less than 2 players remaining in the race.");
    }

    // Simple launcher with console input
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter board size n (board is n x n): ");
        int n = scanner.nextInt();

        System.out.print("Enter number of players: ");
        int x = scanner.nextInt();

        System.out.print("Enter difficulty level (easy/hard): ");
        String diffStr = scanner.next().trim().toLowerCase();
        DifficultyLevel level = diffStr.equals("hard") ?
                DifficultyLevel.HARD : DifficultyLevel.EASY;

        Game game = new Game(n, x, level);
        game.start();
    }
}
