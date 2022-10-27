package ui;

import java.util.Scanner;

public class Main {

    // EFFECTS: instantiates the WizardsVsZombies game loop and allows for user input
    public static void main(String[] args) {

        WizardsVsZombies game = new WizardsVsZombies();
        Scanner scanner = new Scanner(System.in);

        game.displayMenu();

        String input = scanner.next();

        if (input.equals("c")) {
            game.loadGame();
        }

        game.start();
        game.playerInput();
    }
}
