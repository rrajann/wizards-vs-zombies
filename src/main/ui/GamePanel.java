package ui;

import model.*;

import javax.swing.*;
import java.awt.*;

import java.awt.Graphics;
import java.util.List;

public class GamePanel extends JPanel {

    private WizardsVsZombies game;
    private JLabel background;
    private JLabel instructions;
    private final int livingX = Entity.LIVING_X;
    private final int livingY = Entity.LIVING_Y;
    private final int blastX = Entity.BLAST_X_SIZE;
    private final int blastY = Entity.BLAST_Y_SIZE;

    public GamePanel(WizardsVsZombies game) {
        Dimension panel = new Dimension(WizardsVsZombies.WIDTH, WizardsVsZombies.HEIGHT);
        setPreferredSize(panel);
        setBackground(Color.lightGray);
        instructions = new JLabel("WASD to move, K to shoot blast, B to save and quit, P to start a new game");
        instructions.setBounds(0, 0, 10, 10);

        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGame(g);
        repaint();
    }

    private void drawGame(Graphics g) {
        drawWizard(g);
        drawBlasts(g);
        drawZombies(g);
        drawHealth(g);
    }

    private void drawHealth(Graphics g) {
        Wizard wizard = game.getGameLogic().getWizard();

        g.drawString(toString(), wizard.getPosX() - (livingX * 2), wizard.getPosY() + livingY);
    }

    @Override
    public String toString() {
        return "Health: " + game.getGameLogic().getWizard().getHealth();
    }

    private void drawWizard(Graphics g) {
        g.setColor(Color.blue);
        Wizard wizard = game.getGameLogic().getWizard();

        g.fillRect(wizard.getPosX() - (livingX / 2), wizard.getPosY() - (livingY / 2), livingX, livingY);
    }

    private void drawZombies(Graphics g) {
        g.setColor(new Color(41, 75, 22));
        List<Zombie> zombies = game.getGameLogic().getZombies();

        for (Zombie z : zombies) {
            g.fillRect(z.getPosX() - (livingX / 2), z.getPosY() - (livingY / 2), livingX, livingY);
        }
    }

    private void drawBlasts(Graphics g) {
        g.setColor(new Color(0, 235, 255));
        List<Blast> blasts = game.getGameLogic().getBlasts();

        for (Blast b : blasts) {
            g.fillRect(b.getPosX() - (blastX / 2), b.getPosY() - (blastY / 2), blastX, blastY);
        }
    }

}
