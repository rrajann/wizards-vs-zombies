package ui;

import model.GameLogic;
import model.Zombie;
import model.Blast;
import model.Wizard;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WizardsVsZombies extends Thread {

    // Game board:
    public static final int HEIGHT = 100;
    public static final int WIDTH = 100;
    public static final int INTERVAL = 1000;

    private List<Blast> blasts;
    private List<Zombie> zombies;
    private Wizard wizard;
    private GameLogic game;
    private Random rand;
    private boolean play;
    private boolean paused;
    private Scanner input;

    // EFFECTS: creates a new game of Wizards vs Zombies
    public WizardsVsZombies() {
        game = new GameLogic();
        wizard = game.getWizard();
        zombies = game.getZombies();
        blasts = game.getBlasts();
        paused = false;
        input = new Scanner(System.in);
    }

    // EFFECTS: places a zombie in a random coordinate
    // MODIFIES: this
    public Zombie generateRandomZombie() {
        rand = new Random();

        int randomX = rand.nextInt(WIDTH);
        int randomY = rand.nextInt(HEIGHT);

        Zombie randomZombie = new Zombie(randomX, randomY);

        return randomZombie;
    }

    // EFFECTS: moves wizard based on player input
    // MODIFIES: this
    public void playerInput() {
        String key = null;
        play = true;
        displayMenu();

        while (wizard.getHealth() > 0 && play) {
            key = input.next();

            controls(key);

            if (key.equals("b")) {
                play = false;
            }
        }
    }

    // EFFECTS: creates a game loop that runs until the user pauses or the wizards health reaches 0
    // MODIFIES: this
    @Override
    public void run() {

        while (!paused && wizard.getHealth() > 0) {
            Random random = new Random();
            int lottery = random.nextInt(6);

            if (lottery == 5) {
                game.addZombie(generateRandomZombie());
            }

            update();
            showGameStatus();
            try {
                WizardsVsZombies.sleep(INTERVAL);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // EFFECTS: changes the position of the wizard / throws a blast depending on player input
    // MODIFIES: this
    public void controls(String key) {

        if (key.equals("w")) {
            wizard.moveUp();
            System.out.println("Wizard moved up.");
        } else if (key.equals("a")) {
            wizard.moveLeft();
            System.out.println("Wizard moved left.");
        } else if (key.equals("s")) {
            wizard.moveDown();
            System.out.println("Wizard moved down.");
        } else if (key.equals("d")) {
            wizard.moveRight();
            System.out.println("Wizard moved right.");
        } else if (key.equals("k")) {
            game.basicAttack();
            System.out.println("Wizard threw a blast!");
        } else {
            System.out.println("Not a valid input.");
        }
    }

    // EFFECTS: displays the instructions to the user
    public void displayMenu() {
        System.out.println("Welcome to Wizards vs Zombies, here are the controls:");
        System.out.println("Press W to move up");
        System.out.println("Press A to move left");
        System.out.println("Press S to move down");
        System.out.println("Press D to move right");
        System.out.println("Press K to shoot a blast");
        System.out.println("Press B to quit game");
    }

    // EFFECTS: updates the game state to the next frame
    // MODIFIES: this
    public void update() {
        if (game.wizardDamaged()) {
            System.out.println("Wizard got damaged!");
        }
        if (game.deleteZombies()) {
            System.out.println("Zombie hit and down!");
        }
        game.nextZombies();
        game.nextBlasts();
    }

    // EFFECTS: prints the current state of the game
    public void showGameStatus() {
        System.out.println("Health: " + wizard.getHealth());
        System.out.println("Position: (" + wizard.getPosX() + ", " + wizard.getPosY() + ")");

        System.out.println("Num of blasts: " + blasts.size());

        int i = 1;

        for (Zombie zombie : zombies) {
            System.out.println("Zombie" + i + " Position: (" + zombie.getPosX() + ", " + zombie.getPosY() + ")");
            i++;
        }

        for (Blast blast : blasts) {
            System.out.println("Blast" + i + " Position: (" + blast.getPosX() + ", " + blast.getPosY() + ")");
            i++;
        }
    }

}