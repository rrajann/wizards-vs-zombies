package model;

import java.util.ArrayList;

public class Zombie extends Entity {

    public static final int ZOMBIE_SPEED = 5;
    public static final int ZOMBIE_X_SIZE = 6;
    public static final int ZOMBIE_Y_SIZE = 3;
    public static final int ZOMBIE_DAMAGE = 10;

    public Zombie(int posX, int posY) {
        super(posX, posY);
    }

    // EFFECTS: moves the zombie closer to the specified x and y coordinates of argument
    // MODIFIES: this
    // REQUIRES: x and y must both be within the game boundaries (ie: within 0 and game WIDTH/HEIGHT)
    public void approach(int x, int y) {
        if (posX > x) {
            this.posX -= ZOMBIE_SPEED;
            if (posY > y) {                         // if zombie is on bottom right
                this.posY -= ZOMBIE_SPEED;
            } else if (posY < y) {                  // if zombie is on top right
                this.posY += ZOMBIE_SPEED;
            }
        } else if (posX < x) {
            this.posX += ZOMBIE_SPEED;
            if (posY > y) {                         // if zombie is on bottom left
                this.posY -= ZOMBIE_SPEED;
            } else if (posY < y) {                  // if zombie is on top left
                this.posY += ZOMBIE_SPEED;
            }
        } else if (posY > y) {                      // if zombie is on same x coord but bottom
            this.posY -= ZOMBIE_SPEED;
        } else if (posY < y) {
            this.posY += ZOMBIE_SPEED;              // if zombie is on same x coord but top
        } else {                                    // zombie is in same coord
            this.posX = x;
            this.posY = y;
        }
    }
}
