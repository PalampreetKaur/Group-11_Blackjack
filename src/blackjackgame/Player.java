package blackjackgame;

public class Player {
    private final String name;
    private final GroupOfCards hand;

    public Player(String name) {
        this.name = name;
        this.hand = new GroupOfCards();
    }

    public String getName() {
        return name;
    }

    public void addToHand(String card) {
        hand.addCard(card);
    }

    public GroupOfCards getHand() {
        return hand;
    }
}
