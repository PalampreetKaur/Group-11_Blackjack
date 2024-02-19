package blackjackgame;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter player name: ");
            String playerName = scanner.nextLine();

            blackjackgame blackjackGame = new blackjackgame(playerName);
            blackjackGame.play();
        }
    }
}
