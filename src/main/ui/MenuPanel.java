package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Screen;
import ui.WizardsVsZombies;

public class MenuPanel extends JPanel {

    private static final int WIDTH = WizardsVsZombies.WIDTH;
    private static final int HEIGHT = WizardsVsZombies.HEIGHT;

    private JButton startGame;
    private JButton loadGame;
    private JButton quit;
    private WizardsVsZombies game;

    public MenuPanel(WizardsVsZombies game) {
        Dimension panel = new Dimension(WizardsVsZombies.WIDTH, WizardsVsZombies.HEIGHT);
        setPreferredSize(panel);
        setBackground(Color.black);
        startGame();
        loadGame();
        setVisible(true);

        this.game = game;
    }

    private void startGame() {
        startGame = new JButton("Start new game");
        //startGame.setLocation(WIDTH / 2, HEIGHT / 2);
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setScreen(Screen.GAME);
                System.out.println("started");
            }
        });
        this.add(startGame);
    }

    private void loadGame() {
        loadGame = new JButton("Load previous game");
        //loadGame.setLocation(WIDTH / 2, HEIGHT / 2 + 100);
        loadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setLoad(true);
                game.setScreen(Screen.GAME);
            }
        });
        this.add(loadGame);
    }

    private void quitGame() {
        loadGame = new JButton("Quit");
        //loadGame.setLocation(WIDTH / 2, HEIGHT / 2 + 200);
        //startGame.addActionListener();
        this.add(quit);
    }

}