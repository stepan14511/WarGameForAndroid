package com.example.stepa.war;

import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deck;

    Deck(){
        deck = new ArrayList<>();
    }

    public void addCard(Card card){
        deck.add(card);
    }

    public Card getTopCard(){
        Card topCard = deck.get(0);
        deck.remove(0);
        return topCard;
    }

    public int getSizeOfTheDeck(){
        return deck.size();
    }
}
