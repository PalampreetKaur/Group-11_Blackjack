package blackjackgame;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the name of player: ");
            String playerName = scanner.nextLine();

            blackjackgame blackjackGame = new blackjackgame(playerName);
            blackjackGame.play();
        }
    }
}
