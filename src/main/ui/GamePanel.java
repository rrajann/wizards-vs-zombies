package ui;

import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// represents the panel that draws each frame of the current game
public class GamePanel extends JPanel {

    protected WizardsVsZombies game;
    private Image background;
    private final int livingX = Entity.LIVING_X;
    private final int livingY = Entity.LIVING_Y;
    private final int blastX = Entity.BLAST_X_SIZE;
    private final int blastY = Entity.BLAST_Y_SIZE;

    private JButton shoot;

    // EFFECTS: instantiates a new game panel
    public GamePanel(WizardsVsZombies game) {
        Dimension panel = new Dimension(WizardsVsZombies.WIDTH, WizardsVsZombies.HEIGHT);
        setPreferredSize(panel);
        setBackground(Color.black);
        JLabel instructions = new JLabel("WASD to move, K to shoot blast, B to save and quit");
        this.add(instructions);
        instructions.setForeground(Color.white);
        instructions.setBounds(0, 0, 10, 10);
        shootBlast();

        try {
            background = ImageIO.read(new File("data/background.jpg"));
        } catch (Exception e) {
            throw new RuntimeException();
        }
        background = background.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

        this.game = game;
        setVisible(true);
    }

    // MODIFIES: WizardsVsZombies
    // EFFECTS: shoots a blast to the game
    private void shootBlast() {
        shoot = new JButton("Shoot blast");
        shoot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getGameLogic().basicAttack();
            }
        });
        add(shoot);
    }

    // MODIFIES: this
    // EFFECTS: draws all the current game state and repaints
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0,0, this);
        drawGame(g);
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: helper method for paintComponent (draws game state)
    private void drawGame(Graphics g) {
        new Animation(g, game);
        drawBlasts(g);
        drawHealth(g);
    }

    // MODIFIES: this
    // EFFECTS: displays the health of the wizard
    private void drawHealth(Graphics g) {
        Wizard wizard = game.getGameLogic().getWizard();

        g.drawString(toString(), wizard.getPosX() - (livingX * 2), wizard.getPosY() + 50);
    }

    // EFFECTS: toString override to show the wizard's health
    @Override
    public String toString() {
        return "Health: " + game.getGameLogic().getWizard().getHealth();
    }


    // MODIFIES: this
    // EFFECTS: draws blasts
    private void drawBlasts(Graphics g) {
        g.setColor(new Color(0, 235, 255));
        List<Blast> blasts = game.getGameLogic().getBlasts();

        for (Blast b : blasts) {
            g.fillRect(b.getPosX() - (blastX / 2), b.getPosY() - (blastY / 2), blastX, blastY);
        }
    }
}
