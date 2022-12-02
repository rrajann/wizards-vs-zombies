package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WizardsVsZombies extends JFrame implements Runnable {

    // Game board:
    public static final int HEIGHT = 1000;
    public static final int WIDTH = 1000;
    public static final int INTERVAL = 35; // change back to 17 after Phase 2 demo
    public static final String FILE = "./data/savedStated.json";

    private GameLogic game;
    private Random rand;
    private boolean play;
    private JsonWriter saver;
    private JsonReader loader;
    private boolean load;
    private GamePanel gamePanel;
    private MenuPanel menuPanel;
    private Screen screen;
    private int count;

    // EFFECTS: creates a new game of Wizards vs Zombies
    public WizardsVsZombies() {
        super("Wizards Vs Zombies");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                play = false;
                setVisible(false);
                dispose();
            }
        });

        game = new GameLogic();
        play = true;
        load = false;
        count = 0;

        saver = new JsonWriter(FILE);
        loader = new JsonReader(FILE);
        screen = Screen.MENU;
        addKeyListener(new KeyInput());

        menuPanel = new MenuPanel(this);
        this.add(menuPanel);

        setVisible(true);
    }



    // EFFECTS: returns the count
    public int getCount() {
        return count;
    }

    // EFFECTS: returns the game logic
    public GameLogic getGameLogic() {
        return game;
    }

    public void setPlay(boolean bool) {
        play = bool;
    }

    // EFFECTS: sets screen state
    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    // MODIFIES: this
    // EFFECTS: sets save to true or false;
    public void setLoad(boolean bool) {
        load = bool;
    }

    // MODIFIES: this
    // EFFECTS: loads the previously saved game state
    public void loadGame() {
        if (this.load) {
            try {
                game = loader.read();
                System.out.println("Loaded previous game");
            } catch (IOException e) {
                System.out.println("Unable to read file" + " " + FILE);
            }
        } else {
            game.resetGame();
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

    // EFFECTS: creates a game loop that runs until the wizards health reaches 0
    // MODIFIES: this
    @Override
    public void run() {

        while (play) {

            if (screen == Screen.GAME && game.getWizard().getHealth() > 0) {

                this.requestFocusInWindow();
                Random random = new Random();
                int lottery = random.nextInt(50);

                if (lottery == 5) {
                    game.addZombie(generateRandomZombie());
                }

                update();
                if (count++ % 3 == 0) {
                    count++;
                }

            }

            try {
                Thread.sleep(INTERVAL);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        printLog(EventLog.getInstance());
    }


    // MODIFIES: this
    // EFFECTS: exits the menu panel and initialises the game
    public void initGame() {
        remove(menuPanel);
        gamePanel = new GamePanel(this);
        this.add(gamePanel);
        validate();
        repaint();
        this.requestFocusInWindow();
        loadGame();
    }

    // MODIFIES: this
    // EFFECTS: exits the game panel and initialises the game
    public void initMenu() {
        remove(gamePanel);
        setScreen(Screen.MENU);
        menuPanel = new MenuPanel(this);
        this.add(menuPanel);
        validate();
        repaint();
    }

    // EFFECTS: updates the game state to the next frame
    // MODIFIES: this
    public void update() {

        Wizard wizard = game.getWizard();

        game.wizardDamaged();
        game.deleteZombies();
        game.nextZombies();
        game.nextBlasts();
        wizard.move();
        game.blastBoundary();
    }


    // EFFECTS: prints the event log
    public void printLog(EventLog el) {

        for (Event e : el) {
            System.out.println(e);
        }
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

    // MODIFIES: this
    // EFFECTS: moves the wizard's position and shoots blasts corresponding to the designated button pressed
    public void keyInput(KeyEvent e) {
        int key = e.getKeyCode();
        int speed = Wizard.SPEED;

        if (key == KeyEvent.VK_W) {
            game.getWizard().setDy(-speed);
        }
        if (key == KeyEvent.VK_A) {
            game.getWizard().setDx(-speed);
        }
        if (key == KeyEvent.VK_S) {
            game.getWizard().setDy(speed);
        }
        if (key == KeyEvent.VK_D) {
            game.getWizard().setDx(speed);
        }
        if (key == KeyEvent.VK_K) {
            game.basicAttack();
        }
        if (key == KeyEvent.VK_B) {
            saveGame();
            initMenu();
        }
    }

    // A key adapter that records current key event
    private class KeyInput implements KeyListener {

        private KeyInput() {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            keyInput(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_W) {
                game.getWizard().setDy(0);
            }
            if (key == KeyEvent.VK_A) {
                game.getWizard().setDx(0);
            }
            if (key == KeyEvent.VK_S) {
                game.getWizard().setDy(0);
            }
            if (key == KeyEvent.VK_D) {
                game.getWizard().setDx(0);
            }
        }
    }

    public enum Screen {
        MENU, GAME
    }
}