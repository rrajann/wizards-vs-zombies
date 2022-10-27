package ui;

import model.GameLogic;
import model.Zombie;
import model.Blast;
import model.Wizard;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WizardsVsZombies extends Thread {

    // Game board:
    public static final int HEIGHT = 100;
    public static final int WIDTH = 100;
    public static final int INTERVAL = 1000;
    public static final String FILE = "./data/savedStated.json";

    private List<Blast> blasts;
    private List<Zombie> zombies;
    private Wizard wizard;
    private GameLogic game;
    private Random rand;
    private boolean play;
    private boolean paused;
    private Scanner input;
    private JsonWriter saver;
    private JsonReader loader;
    private boolean load;

    // EFFECTS: creates a new game of Wizards vs Zombies
    public WizardsVsZombies() {
        game = new GameLogic();
        play = true;
        input = new Scanner(System.in);
        saver = new JsonWriter(FILE);
        loader = new JsonReader(FILE);
    }

    // MODIFIES: this
    // EFFECTS: sets save to true or false;
    public void setLoad(boolean bool) {
        load = bool;
    }

    // MODIFIES: this
    // EFFECTS: loads the previously saved game state
    public void loadGame() {
        try {
            game = loader.read();
            System.out.println("Loaded previous game");
        } catch (IOException e) {
            System.out.println("Unable to read file" + " " + FILE);
        }
    }

    // EFFECTS: saves current game state into JSON file
    public void saveGame() {
        try {
            saver.open();
            saver.writeOn(game);
            System.out.println("Saved to " + FILE);
        } catch (FileNotFoundException e) {
            System.out.println("File " + FILE + " does not exist");
        }
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

        while (game.getWizard().getHealth() > 0 && play) {
            key = input.next();

            controls(key);
            showGameStatus();
        }
    }

    // EFFECTS: creates a game loop that runs until the wizards health reaches 0
    // MODIFIES: this
    @Override
    public void run() {

        while (play && game.getWizard().getHealth() > 0) {
            Random random = new Random();
            int lottery = random.nextInt(6);

            if (lottery == 5) {
                game.addZombie(generateRandomZombie());
            }

            showGameStatus();
            update();

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
            game.getWizard().moveUp();
            System.out.println("Wizard moved up.");
        } else if (key.equals("a")) {
            game.getWizard().moveLeft();
            System.out.println("Wizard moved left.");
        } else if (key.equals("s")) {
            game.getWizard().moveDown();
            System.out.println("Wizard moved down.");
        } else if (key.equals("d")) {
            game.getWizard().moveRight();
            System.out.println("Wizard moved right.");
        } else if (key.equals("k")) {
            game.basicAttack();
            System.out.println("Wizard threw a blast!");
        } else if (key.equals("b")) {
            saveGame();
            play = false;
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
        System.out.println("Press C to load previous game");
        System.out.println("Press any button other than C to start a new game");
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
        Wizard wizard = game.getWizard();

        System.out.println("Health: " + game.getWizard().getHealth());
        System.out.println("Position: (" + wizard.getPosX() + ", " + wizard.getPosY() + ")");

        System.out.println("Num of blasts: " + game.getBlasts().size());

        int i = 1;

        for (Zombie zombie : game.getZombies()) {
            System.out.println("Zombie" + i + " Position: (" + zombie.getPosX() + ", " + zombie.getPosY() + ")");
            i++;
        }

        for (Blast blast : game.getBlasts()) {
            System.out.println("Blast" + i + " Position: (" + blast.getPosX() + ", " + blast.getPosY() + ")");
            i++;
        }
    }
}