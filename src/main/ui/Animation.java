package ui;

import model.Entity;
import model.Wizard;

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
    private BufferedImage idle;
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

    private Image idleImage;
    private Image moveLeft1;
    private Image moveLeft2;
    private Image moveLeft3;
    private Image moveLeft4;
    private Image moveRight1;
    private Image moveRight2;
    private Image moveRight3;
    private Image moveRight4;
    private Image moveUp1;
    private Image moveUp2;
    private Image moveUp3;
    private Image moveUp4;
    private Image moveDown1;
    private Image moveDown2;
    private Image moveDown3;
    private Image moveDown4;

    private static final int livingX = Entity.LIVING_X;
    private static final int livingY = Entity.LIVING_Y;

    private int count;

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
            idle = ImageIO.read(new File("data/wizardAnimation/idle.png"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        idleImage = idle.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
        imageSetUp("left", "wizardAnimation", left1, left2, left3, left4, moveLeft1, moveLeft2,
                moveLeft3,  moveLeft4, wizardLeftFrames);
        count = game.getCount();
        imageSetUp("right", "wizardAnimation", right1, right2, right3, right4, moveRight1, moveRight2,
                moveRight3,  moveRight4, wizardRightFrames);
        imageSetUp("up", "wizardAnimation", up1, up2, up3, up4, moveUp1, moveUp2,
                moveUp3,  moveUp4, wizardUpFrames);
        imageSetUp("down", "wizardAnimation", down1, down2, down3, down4, moveDown1, moveDown2,
                moveDown3,  moveDown4, wizardDownFrames);
        count = game.getCount();
        drawWizard(g);
    }

    // MODIFIES: this
    // REQUIRES: "direction" string must be one of "left", "right", "up", "down", and file must be alligned with the
    //           right file name
    // EFFECTS: abstract method for setting up images and putting them under one direction frame
    private void imageSetUp(String direction, String file, BufferedImage one, BufferedImage two, BufferedImage three,
                            BufferedImage four, Image aniOne, Image aniTwo, Image aniThree, Image aniFour,
                            List<Image> frames) {
        try {
            one = ImageIO.read(new File("data/" + file + "/" + direction + "1.png"));
            two = ImageIO.read(new File("data/" + file + "/" + direction + "2.png"));
            three = ImageIO.read(new File("data/" + file + "/" + direction + "3.png"));
            four = ImageIO.read(new File("data/" + file + "/" + direction + "4.png"));
        } catch (Exception e) {
            throw new RuntimeException();
        }

        aniOne = one.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
        aniTwo = two.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
        aniThree = three.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
        aniFour = four.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

        frames.add(aniOne);
        frames.add(aniTwo);
        frames.add(aniThree);
        frames.add(aniFour);
    }

    // MODIFIES: this
    // EFFECTS: draws wizard
    private void drawWizard(Graphics g) {
        Wizard wizard = game.getGameLogic().getWizard();
        int point = count % 4;
        System.out.println(game.getCount());

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

        //System.out.println(point);
        //g.fillRect(wizard.getPosX() - (livingX / 2), wizard.getPosY() - (livingY / 2), livingX, livingY);
    }

}
