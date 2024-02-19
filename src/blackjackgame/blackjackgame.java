package blackjackgame;

import java.util.List;
import java.util.Scanner;

public class blackjackgame {
    private final GroupOfCards deck;
    private final Player player;
    private final Player opponent;

    public blackjackgame(String playerName) {
        deck = new GroupOfCards();
        initializeDeck();
        player = new Player(playerName);
        opponent = new Player("Opponent");
    }

    private void initializeDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String suit : suits) {
            for (String rank : ranks) {
                deck.addCard(rank + " of " + suit);
            }
        }

        deck.shuffle();
    }

    public void play() {
        dealInitialCards();
        playerTurn();
        if (!isBust(player.getHand().getCards())) {
            opponentTurn();
            declareWinner();
        } else {
            System.out.println(player.getName() + " busts. Opponent wins!");
        }
    }

    private void dealInitialCards() {
        player.addToHand(deck.removeCard());
        player.addToHand(deck.removeCard());
        opponent.addToHand(deck.removeCard());
        opponent.addToHand(deck.removeCard());

        System.out.println("Player's hand: " + player.getHand().getCards());
        System.out.println("Opponent's hand: " + opponent.getHand().getCards().get(0) + ", [Hidden]");
    }

    private void playerTurn() {
        Scanner scanner = new Scanner(System.in);
        OUTER:
        while (true) {
            System.out.print("Hit or Stand? (h/s): ");
            String choice = scanner.nextLine().trim().toLowerCase();
            switch (choice) {
                case "h" -> {
                    player.addToHand(deck.removeCard());
                    System.out.println("Player's hand: " + player.getHand().getCards());
                    if (isBust(player.getHand().getCards())) {
                        System.out.println(player.getName() + " busts.");
                        break OUTER;
                    }
                }
                case "s" -> {
                    break OUTER;
                }
                default -> System.out.println("Invalid choice. Please enter 'h' or 's'.");
            }
        }
    }

    private void opponentTurn() {
        System.out.println("Opponent's turn:");
        System.out.println("Opponent's hand: " + opponent.getHand().getCards());
        while (calculateHandValue(opponent.getHand().getCards()) < 17) {
            opponent.addToHand(deck.removeCard());
            System.out.println("Opponent hits: " + opponent.getHand().getCards());
            if (isBust(opponent.getHand().getCards())) {
                System.out.println("Opponent busts.");
                break;
            }
        }
    }

    private boolean isBust(List<String> hand) {
        return calculateHandValue(hand) > 21;
    }

    private int calculateHandValue(List<String> hand) {
        int sum = 0;
        for (String card : hand) {
            int value = getValue(card);
            sum += value;
        }
        return sum;
    }

    private int getValue(String card) {
        String rank = card.split(" ")[0];
        return switch (rank) {
            case "Jack", "Queen", "King" -> 10;
            case "Ace" -> 11;
            default -> Integer.parseInt(rank);
        };
    }

    private void declareWinner() {
        int playerValue = calculateHandValue(player.getHand().getCards());
        int opponentValue = calculateHandValue(opponent.getHand().getCards());

        if (playerValue > opponentValue || opponentValue > 21) {
            System.out.println(player.getName() + " wins!");
        } else if (playerValue < opponentValue) {
            System.out.println("Opponent wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }
}
