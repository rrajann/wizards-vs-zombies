package model;

public class Blast extends Entity {

    public static final int BLAST_SPEED = 10;
    public static final int BLAST_X_SIZE = 3;
    public static final int BLAST_Y_SIZE = 3;

    private boolean horizontal;
    private boolean direction;

    // EFFECT: creates a blast with given position and moving right
    public Blast(int posX, int posY, boolean h, boolean d) {
        super(posX, posY);
        horizontal = h; // true means going horizontally (left/right)
        direction = d; // true means going in the positive direction (right/down)
    }

    // EFFECT: return weather or not the blast is moving horizontally
    public boolean isHorizontal() {
        return horizontal;
    }

    // EFFECT: return weather or not the blast is moving in the positive direction
    public boolean isDirection() {
        return direction;
    }

    // EFFECTS: moves the blast by i;ts speed
    // MODIFIES: this
    public void moveBlast() {
        if (horizontal) {
            if (direction) {
                posX += BLAST_SPEED;
            } else {
                posX -= BLAST_SPEED;
            }
        } else {
            if (direction) {
                posY += BLAST_SPEED;
            } else {
                posY -= BLAST_SPEED;
            }
        }
    }


}
