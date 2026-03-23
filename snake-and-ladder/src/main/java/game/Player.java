package game;

public class Player {
    private final int id;
    private final String name;
    private int position;      // 0 = off board
    private boolean active;    // false when player has already won

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.position = 0;
        this.active = true;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
