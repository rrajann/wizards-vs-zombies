package ui;

import model.Entity;
import model.Wizard;
import model.Zombie;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Animation extends GamePanel {

    private static final int WIDTH = 75;
    private static final int HEIGHT = 75;

    // IMAGES:

    // Wizard:
    private Image idleImage;
    private BufferedImage left1;
    private BufferedImage left2;
    private BufferedImage left3;
    private BufferedImage left4;
    private BufferedImage right1;
    private BufferedImage right2;
    private BufferedImage right3;
    private BufferedImage right4;
    private BufferedImage up1;
    private BufferedImage up2;
    private BufferedImage up3;
    private BufferedImage up4;
    private BufferedImage down1;
    private BufferedImage down2;
    private BufferedImage down3;
    private BufferedImage down4;
    // Zombie:
    private BufferedImage left1z;
    private BufferedImage left2z;
    private BufferedImage left3z;
    private BufferedImage left4z;
    private BufferedImage right1z;
    private BufferedImage right2z;
    private BufferedImage right3z;
    private BufferedImage right4z;
    private BufferedImage up1z;
    private BufferedImage up2z;
    private BufferedImage up3z;
    private BufferedImage up4z;
    private BufferedImage down1z;
    private BufferedImage down2z;
    private BufferedImage down3z;
    private BufferedImage down4z;

    private static final int livingX = Entity.LIVING_X;
    private static final int livingY = Entity.LIVING_Y;

    private int count;

    private List<Image> zombieLeftFrames = new ArrayList<>();
    private List<Image> zombieRightFrames = new ArrayList<>();
    private List<Image> zombieUpFrames = new ArrayList<>();
    private List<Image> zombieDownFrames = new ArrayList<>();

    private List<Image> wizardLeftFrames = new ArrayList<>();
    private List<Image> wizardRightFrames = new ArrayList<>();
    private List<Image> wizardUpFrames = new ArrayList<>();
    private List<Image> wizardDownFrames = new ArrayList<>();

    private WizardsVsZombies game;
    private GamePanel panel;

    public Animation(Graphics g, WizardsVsZombies game) {
        super(game);
        this.game = game;
        try {
            idleImage = ImageIO.read(new File("data/wizardAnimation/idle.png"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        idleImage = idleImage.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
        count = game.getCount();
        wizardFramesSetUp();
        zombieFramesSetUp();
        drawWizard(g);
        drawZombie(g);
    }

    // MODIFIES: this
    // REQUIRES: valid parameters for imageSetUp
    // EFFECTS: sets up four directional frames for wizard
    private void wizardFramesSetUp() {
        imageSetUp("left", "wizardAnimation", left1, left2, left3, left4, wizardLeftFrames);
        imageSetUp("right", "wizardAnimation", right1, right2, right3, right4, wizardRightFrames);
        imageSetUp("up", "wizardAnimation", up1, up2, up3, up4, wizardUpFrames);
        imageSetUp("down", "wizardAnimation", down1, down2, down3, down4, wizardDownFrames);
    }

    // MODIFIES: this
    // REQUIRES: valid parameters for imageSetUp
    // EFFECTS: sets up four directional frames for zombie
    private void zombieFramesSetUp() {
        imageSetUp("left", "zombieAnimation", left1z, left2z, left3z, left4z, zombieLeftFrames);
        imageSetUp("right", "zombieAnimation", right1z, right2z, right3z, right4z, zombieRightFrames);
        imageSetUp("up", "zombieAnimation", up1z, up2z, up3z, up4z, zombieUpFrames);
        imageSetUp("down", "zombieAnimation", down1z, down2z, down3z, down4z, zombieDownFrames);
    }

    // MODIFIES: this
    // REQUIRES: "direction" string must be one of "left", "right", "up", "down", and file must be alligned with the
    //           right file name
    // EFFECTS: abstract method for setting up images and putting them under one direction frame
    private void imageSetUp(String direction, String file, Image one, Image two, Image three,
                            Image four, List<Image> frames) {
        try {
            one = ImageIO.read(new File("data/" + file + "/" + direction + "1.png"));
            two = ImageIO.read(new File("data/" + file + "/" + direction + "2.png"));
            three = ImageIO.read(new File("data/" + file + "/" + direction + "3.png"));
            four = ImageIO.read(new File("data/" + file + "/" + direction + "4.png"));
        } catch (Exception e) {
            throw new RuntimeException();
        }

        one = one.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
        two = two.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
        three = three.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
        four = four.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

        frames.add(one);
        frames.add(two);
        frames.add(three);
        frames.add(four);
    }

    // MODIFIES: this
    // EFFECTS: draws wizard
    private void drawWizard(Graphics g) {
        Wizard wizard = game.getGameLogic().getWizard();
        int point = count % 4;

        // try to make animation slower

        if (wizard.getDX() > 0) {
            g.drawImage(wizardRightFrames.get(point), wizard.getPosX() - (WIDTH / 2),
                    wizard.getPosY() - (HEIGHT / 2), this);
        } else if (wizard.getDX() < 0) {
            g.drawImage(wizardLeftFrames.get(point), wizard.getPosX() - (WIDTH / 2),
                    wizard.getPosY() - (HEIGHT / 2), this);
        } else if (wizard.getDY() > 0) {
            g.drawImage(wizardDownFrames.get(point), wizard.getPosX() - (WIDTH / 2),
                    wizard.getPosY() - (HEIGHT / 2), this);
        } else if (wizard.getDY() < 0) {
            g.drawImage(wizardUpFrames.get(point), wizard.getPosX() - (WIDTH / 2),
                    wizard.getPosY() - (HEIGHT / 2), this);
        } else {
            g.drawImage(idleImage, wizard.getPosX() - (WIDTH / 2),
                    wizard.getPosY() - (HEIGHT / 2), this);
        }

    }

    // MODIFIES: this
    // EFFECTS: draws zombie
    private void drawZombie(Graphics g) {
        List<Zombie> zombies = game.getGameLogic().getZombies();
        int point = count % 4;

        // try to make animation slower

        for (int i = 0; i < zombies.size(); i++) {

            if (zombies.get(i).getLastDirection() == Entity.Direction.RIGHT) {
                g.drawImage(zombieRightFrames.get(point), zombies.get(i).getPosX() - (WIDTH / 2),
                        zombies.get(i).getPosY() - (HEIGHT / 2), this);
            } else if (zombies.get(i).getLastDirection() == Entity.Direction.LEFT) {
                g.drawImage(zombieLeftFrames.get(point), zombies.get(i).getPosX() - (WIDTH / 2),
                        zombies.get(i).getPosY() - (HEIGHT / 2), this);
            } else if (zombies.get(i).getLastDirection() == Entity.Direction.DOWN) {
                g.drawImage(zombieDownFrames.get(point), zombies.get(i).getPosX() - (WIDTH / 2),
                        zombies.get(i).getPosY() - (HEIGHT / 2), this);
            } else if (zombies.get(i).getLastDirection() == Entity.Direction.UP) {
                g.drawImage(zombieUpFrames.get(point), zombies.get(i).getPosX() - (WIDTH / 2),
                        zombies.get(i).getPosY() - (HEIGHT / 2), this);
            }
        }
    }
}

