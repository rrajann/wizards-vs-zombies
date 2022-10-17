package ui;

import model.GameLogic;
import model.Zombie;
import model.Blast;
import model.Wizard;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WizardsVsZombies {

    // Game board:
    public static final int HEIGHT = 1000;
    public static final int WIDTH = 1000;
    public static final int INTERVAL = 10;

    private List<Blast> blasts;
    private List<Zombie> zombies;
    private Wizard wizard;
    private GameLogic game;
    private Scanner input;
    private Random rand;
    private boolean play;

    public WizardsVsZombies() {
        game = new GameLogic();
        wizard = game.getWizard();
        zombies = game.getZombies();
        blasts = game.getBlasts();
        input = new Scanner(System.in);
        runGame();
    }

    public Zombie generateRandomZombie() {
        rand = new Random();

        int randomX = rand.nextInt(WIDTH);
        int randomY = rand.nextInt(HEIGHT);

        Zombie randomZombie = new Zombie(randomX, randomY);

        return randomZombie;
    }

    public void runGame() {

        displayMenu();
        String key = null;
        play = true;


        while (wizard.getHealth() > 0 && play) {
            Random random = new Random();
            int lottery = random.nextInt(6);
            key = input.next();

            controls(key);
            update();
            showGameStatus();

            if (key.equals("b")) {
                play = false;
            }
            if (lottery == 5) {
                game.addZombie(generateRandomZombie());
            }
        }
    }

    public void displayMenu() {
        System.out.println("Welcome to Wizards vs Zombies, here are the controls:");
        System.out.println("Press W to move up");
        System.out.println("Press A to move left");
        System.out.println("Press S to move down");
        System.out.println("Press D to move right");
        System.out.println("Press K to shoot a blast");
        System.out.println("Press B to quit game");
    }

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

    public void showGameStatus() {
        System.out.println("Health: " + wizard.getHealth());
        System.out.println("Position: (" + wizard.getPosX() + ", " + wizard.getPosY() + ")");

        System.out.println("Num of blasts: " + blasts.size());

        int i = 1;

        for (Zombie zombie : zombies) {
            System.out.println("Zombie" + i + " Position: (" + zombie.getPosX() + ", " + zombie.getPosY() + ")");
            i++;
        }
    }

}
