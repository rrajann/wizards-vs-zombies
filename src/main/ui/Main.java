package ui;

public class Main {

    // EFFECTS: instantiates the WizardsVsZombies game loop and allows for user input
    public static void main(String[] args) {

        WizardsVsZombies game = new WizardsVsZombies();

        game.start();
        game.playerInput();
    }
}
