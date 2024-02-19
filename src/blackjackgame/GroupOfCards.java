package blackjackgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroupOfCards {
    private final List<String> cards;

    public GroupOfCards() {
        cards = new ArrayList<>();
    }

    public List<String> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void addCard(String card) {
        cards.add(card);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public String removeCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }
}
