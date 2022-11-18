package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

// a panel for the menu, the initial and intermediate panel
public class MenuPanel extends JPanel {

    private static final int WIDTH = WizardsVsZombies.WIDTH;
    private static final int HEIGHT = WizardsVsZombies.HEIGHT;

    private JButton startGame;
    private JButton loadGame;
    private JButton quit;
    private WizardsVsZombies game;
    private ImageIcon picture;
    private JLabel actualPicture;

    // EFFECTS: instantiates a new menu panel
    public MenuPanel(WizardsVsZombies game) {
        Dimension panel = new Dimension(WizardsVsZombies.WIDTH, WizardsVsZombies.HEIGHT);
        setPreferredSize(panel);
        setBackground(Color.black);
        startGame();
        loadGame();
        quitGame();
        setVisible(true);
        picture = new ImageIcon("data/tobs.jpg");
        actualPicture = new JLabel();
        actualPicture.setIcon(picture);
        add(actualPicture);

        this.game = game;
    }

    // MODIFIES: this, WizardsVsZombies
    // EFFECTS: creates a new button that starts the game
    private void startGame() {
        startGame = new JButton("Start new game");
        //startGame.setBounds(WIDTH / 2, 0, 100, 20);
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setLoad(false);
                game.setScreen(WizardsVsZombies.Screen.GAME);
                game.initGame();
                System.out.println("started");
            }
        });
        this.add(startGame);
    }

    // MODIFIES: this, WizardsVsZombies
    // EFFECTS: creates a new button that loads the previously saved game
    private void loadGame() {
        loadGame = new JButton("Load previous game");
        //startGame.setBounds(WIDTH / 2, HEIGHT / 2 + 150, 100, 20);
        loadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setLoad(true);
                game.setScreen(WizardsVsZombies.Screen.GAME);
                game.initGame();
            }
        });
        this.add(loadGame);
    }

    // MODIFIES: this, WizardsVsZombies
    // EFFECTS: creates a new button that quits the game and exits the thread loop
    private void quitGame() {
        quit = new JButton("Quit");
        //startGame.setBounds(WIDTH / 2, HEIGHT / 2 + 300, 100, 20);
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setVisible(false);
                game.dispose();
                game.setPlay(false);
            }
        });
        this.add(quit);
    }

}