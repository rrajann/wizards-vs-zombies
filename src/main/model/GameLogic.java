package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonWriter;
import persistence.Writable;
import ui.WizardsVsZombies;

import java.util.ArrayList;
import java.util.List;

// Represents the interaction of all entities and list of entities within the game
public class GameLogic implements Writable {

    private List<Blast> blasts;
    private List<Zombie> zombies;
    private Wizard wizard;

    // EFFECT: creates a collection of entities, including one wizard and a list of wizards and blasts
    public GameLogic() {
        wizard = new Wizard(WizardsVsZombies.WIDTH / 2, WizardsVsZombies.HEIGHT / 2);
        blasts = new ArrayList<>();
        zombies = new ArrayList<>();
    }

    // EFFECT: returns the list of blasts in the game
    public List<Blast> getBlasts() {
        return blasts;
    }

    // EFFECT: returns the list of zombies in the game
    public List<Zombie> getZombies() {
        return zombies;
    }

    // EFFECT: returns the wizard in the game
    public Wizard getWizard() {
        return wizard;
    }

    // MODIFIES: this
    // EFFECT: changes the attributes of the wizard to these parameters
    public void setWizard(int x, int y, int dx, int dy, int health, int time, boolean moving) {
        wizard.setPosX(x);
        wizard.setPosY(y);
        wizard.setDx(dx);
        wizard.setDy(dy);
        wizard.setHealth(health);
        wizard.setTime(time);
        wizard.setMoving(moving);
    }

    // MODIFIES: this
    // EFFECT: sets lists of blasts to this parameter
    public void setBlasts(List<Blast> blasts) {
        this.blasts = blasts;
    }

    // MODIFIES: this
    // EFFECT: sets lists of zombies to this parameter
    public void setZombies(List<Zombie> zombies) {
        this.zombies = zombies;
    }

    // EFFECT: advances the state of blasts by one frame
    // MODIFIES: this
    public void nextBlasts() {
        for (Blast blast : blasts) {
            blast.moveBlast();
        }
    }

    // EFFECT: advances the state of zombie by one frame
    // MODIFIES: this
    public void nextZombies() {
        for (Zombie zombie : zombies) {
            zombie.approach(wizard.getPosX(), wizard.getPosY());
        }
    }

    // EFFECT: add a zombie to the list of zombies in a designated position
    // MODIFIES: this
    // REQUIRES: position must be within game dimensions
    public void addZombie(Zombie zombie) {
        zombies.add(zombie);
    }

    // EFFECT: add a blast to the list of blasts in a designated position
    // MODIFIES: this
    // REQUIRES: position must be within game dimensions
    public void addBlast(Blast blast) {
        blasts.add(blast);
    }

    // EFFECT: checks for all zombie in zombies and deletes them if they got hit by a blast
    //         removes the blasts that hit the zombies, and returns true if any zombies got hit
    //         and by extension returns true if blasts hit zombie
    // MODIFIES: this
    public boolean deleteZombies() {
        List<Zombie> deadZombies = new ArrayList<>();
        List<Blast> deletedBlasts = new ArrayList<>();

        if (blasts.isEmpty() || zombies.isEmpty()) {
            return false;
        }
        for (int zombieIndex = 0, blastIndex = 0; zombieIndex < zombies.size(); blastIndex++) {
            Zombie zombie = zombies.get(zombieIndex);
            Blast blast = blasts.get(blastIndex);

            if (zombie.collision(blast)) {
                deadZombies.add(zombie);
                deletedBlasts.add(blast);
            }
            if (blastIndex == blasts.size() - 1) {
                zombieIndex++;
                blastIndex = -1;
            }
        }

        if (zombies.removeAll(deadZombies)) {
            blasts.removeAll(deletedBlasts);
            return true;
        }
        return false;
    }

    // EFFECT: adds a blast to the list of blasts depending on the wizards position
    // MODIFIES: this
    public void basicAttack() {

        if (wizard.getDX() > 0) {
            Blast blastRight = new Blast(wizard.getPosX(), wizard.getPosY(), true, true);
            this.addBlast(blastRight);
        } else if (wizard.getDX() < 0) {
            Blast blastLeft = new Blast(wizard.getPosX(), wizard.getPosY(), true, false);
            this.addBlast(blastLeft);
        } else if (wizard.getDY() > 0) {
            Blast blastDown = new Blast(wizard.getPosX(), wizard.getPosY(), false, true);
            this.addBlast(blastDown);
        } else {
            Blast blastUp = new Blast(wizard.getPosX(), wizard.getPosY(), false, false);
            this.addBlast(blastUp);
        }
    }

    // EFFECT: if the wizard has gotten hit by any of the zombies, decrease its health
    // MODIFIES: this
    public boolean wizardDamaged() {

        boolean gotHit = false;

        for (Zombie zombie : zombies) {
            if (wizard.collision(zombie)) {
                wizard.setHealth(wizard.getHealth() - Zombie.ZOMBIE_DAMAGE);
                gotHit = true;
            }
        }
        return gotHit;
    }

    // MODIFIES: this
    // EFFECT: sets the game to its original state, with wizard at center (full health) and no blasts/zombies
    public void resetGame() {
        zombies.removeAll(zombies);
        blasts.removeAll(blasts);
        wizard.setPosX(WizardsVsZombies.WIDTH / 2);
        wizard.setPosY(WizardsVsZombies.HEIGHT / 2);
        wizard.setHealth(100);
    }

    // EFFECTS: converts game state into JSON syntax
    @Override
    public JSONObject toJson() {
        JSONObject gameState = new JSONObject();
        JSONArray blastsStatus = new JSONArray();
        JSONArray zombiesStatus = new JSONArray();

        for (Blast b : blasts) {
            blastsStatus.put(b.toJson());
        }

        for (Zombie z : zombies) {
            zombiesStatus.put(z.toJson());
        }

        gameState.put("Wizard", wizard.toJson());
        gameState.put("Blasts", blastsStatus);
        gameState.put("Zombies", zombiesStatus);

        return gameState;
    }
}


